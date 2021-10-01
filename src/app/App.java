package app;

import com.google.gson.stream.JsonReader;
import data.Pace;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.*;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Optional;

/*
Add additional thread to periodically save pace
Potentially add additional thread to have a "backup" of the pace
 */

//TODO figure out if removing the general application thing and just making stages and showing them would work better

/**
 * Application Class, includes the starting point of the program and additional universally-accessible references
 * @author Thomas Kwashnak
 * @since 1.0.0
 * @version 1.0.0
 */
public class App extends Application {

    public static final String version = "1.0.0";
    public static final Settings settings = SystemResources.getSettings();
    public static Pace openedPace;
    public static SettingsEditor settingsEditor;
    private static Stage appStage;
    private static Updatable[] updateList;

    /**
     * Application Launch Point
     *
     * @param args Launch Arguments
     * @since 1.0.0
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Opens a pace from a specified file path
     *
     * @param file Pace File to open
     * @since 1.0.0
     */
    public static void open(File file) {
        if (file != null && file.exists()) {
            openedPace = Pace.fromFile(file);
            appStage.setTitle(file.getName());
        } else {
            openedPace = pace2021();
        }

        appStage.setScene(generateScene());
        openedPace.save();

        appStage.show();
    }

    /**
     * <p>Creates a new tab element, configures it, and returns the tab</p>
     * <p><b>Modifications:</b>
     * <ul><li>Sets Closable to false</li><li>Sets the content to the provided node</li><li>Sets the title of the
     * tab to the provided name</li></ul></p>
     * @param node Initial content of the pane
     * @param name Initial name/title of the tab
     * @return Configured {@code Tab} object
     * @since 1.0.0
     */
    private static Tab createTab(Node node, String name) {
        Tab tab = new Tab(name);
        tab.setClosable(false);
        tab.setContent(node);
        return tab;
    }

    /**
     * Sends update notifications to all updatable objects
     * @since 1.0.0
     */
    public static void update() {
        openedPace.update();
        if (updateList != null) {
            for (Updatable updatable : updateList) {
                updatable.update();
            }
        }
        if(settingsEditor != null) {
            settingsEditor.update();
        }
    }


    /**
     * Development Method that imports the data from the 2021 hunter pace. Pulls from resources/dev/pace2021.json
     * @since 1.0.0-development
     * @return Pace with values from 2021
     */
    private static Pace pace2021() {
        return Pace.fromJson(new JsonReader(new InputStreamReader(SystemResources.getResource("/dev/pace2021.json"))));
    }

    /**
     * Prompts the user to verify whether they want to delete an item
     *
     * @param name Display Name of the item the user may want to delete
     * @return {@code True} if the user decided to delete, {@code false} otherwise.
     * @since 1.0.0
     */
    public static boolean warnDelete(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete " + name + "?");
        alert.setHeaderText("Are you sure you want to delete " + name + "?");
        alert.setContentText("This action is permanent and cannot be reversed");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * Generates the scene of the application
     * @return Scene with all the application elements within it
     * @since 1.0.0
     */
    private static Scene generateScene() {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();

        TeamTable teamTable = new TeamTable(() -> openedPace.getTeams());
        DivisionView divisionView = new DivisionView();

        tabPane.getTabs().addAll(
                createTab(teamTable, "Teams"),
                createTab(divisionView, "Divisions")
        );

        updateList = new Updatable[]{teamTable, divisionView};

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");

        MenuItem openPace = new MenuItem("Open");
        openPace.setOnAction(e -> {
            File file = SystemResources.promptOpenPace();
            if (file != null) {
                open(file);
            }
        });

        MenuItem savePaceAs = new MenuItem("Save As");
        savePaceAs.setOnAction(e -> {
            File file = SystemResources.promptSavePace();
            if (file != null) {
                openedPace.setFile(file);
                openedPace.save();
            }
        });

        MenuItem savePace = new MenuItem("Save");
        savePace.setOnAction(e -> {
            if (openedPace.getFile() != null) {
                openedPace.save();
            } else {
                savePaceAs.fire();
            }
        });

        MenuItem closePace = new MenuItem("Close Pace");
        closePace.setOnAction(e -> {
            Launcher.open();
            appStage.close();
        });

        MenuItem openSettings = new MenuItem("Settings");
        openSettings.setOnAction(e -> new SettingsEditor());

        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(e -> System.exit(0));

        Menu menuTools = new Menu("Tools");

        MenuItem quickImport = new MenuItem("Quick Import");
        quickImport.setOnAction(e -> new QuickImport());

        MenuItem newTeam = new MenuItem("New Team");
        newTeam.setOnAction(e -> new TeamEditor());

        menuBar.getMenus().addAll(menuFile, menuTools);
        menuFile.getItems().addAll(openPace, savePace, savePaceAs, closePace, openSettings, exitApp);
        menuTools.getItems().addAll(quickImport, newTeam);


        borderPane.setTop(menuBar);
        borderPane.setCenter(tabPane);


        return new Scene(borderPane);
    }

    /**
     * Application stage start point
     *
     * @see Application
     * @since 1.0.0
     */
    @Override
    public void start(Stage stage) {
        appStage = stage;
        stage.setOnCloseRequest(e -> {
            if (settingsEditor != null) {
                settingsEditor.close();
            }
        });
        stage.setMaximized(settings.isAppMaximized());
        stage.maximizedProperty().addListener(e -> settings.setAppMaximized(stage.isMaximized()));
        Launcher.open();
    }


}
