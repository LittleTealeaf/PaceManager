package app;

import com.google.gson.stream.JsonReader;
import data.Pace;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import settings.Settings;
import settings.SettingsEditor;
import ui.*;

import java.io.File;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
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

    /**
     * The current version of the Application
     */
    public static final String version = "1.0.0";
    /**
     * Application instance of settings
     */
    public static final Settings settings = Resources.getSettings();
    /**
     * Currently opened pace
     */
    public static Pace openedPace;
    /**
     * Currently opened main-application stage
     */
    private static Stage appStage;
    /**
     * List of all updatable objects
     * @see #update()
     */
    private static List<Updatable> updateList;

    /**
     * Application Launch Point
     *
     * @param args Launch Arguments
     *
     */
    public static void main(String[] args) {
        updateList = new LinkedList<>();
        launch(args);
    }

    /**
     * Opens a pace from a specified file path
     *
     * @param file Pace File to open
     *
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
     *
     */
    private static Tab createTab(Node node, String name) {
        Tab tab = new Tab(name);
        tab.setClosable(false);
        tab.setContent(node);
        return tab;
    }

    /**
     * Sends update notifications to all updatable objects
     * @see #updateList
     * @see Updatable
     *
     */
    public static void update() {
        openedPace.update();
        if (updateList != null) {
            for (Updatable updatable : updateList) {
                updatable.update();
            }
        }
    }


    /**
     * Development Method that imports the data from the 2021 hunter pace. Pulls from resources/dev/pace2021.json
     * @since 1.0.0-development
     * @return Pace with values from 2021
     */
    private static Pace pace2021() {
        return Pace.fromJson(new JsonReader(new InputStreamReader(Resources.getResource("/dev/pace2021.json"))));
    }

    /**
     * Prompts the user to verify whether they want to delete an item
     *
     * @param name Display Name of the item the user may want to delete
     * @return {@code True} if the user decided to delete, {@code false} otherwise.
     *
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
     *
     */
    private static Scene generateScene() {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();

        TeamTable teamTable = new TeamTable(() -> openedPace.getTeams());
        DivisionView divisionView = new DivisionView();
        WinnersView winnersView = new WinnersView();

        tabPane.getTabs().addAll(
                createTab(teamTable, "Teams"),
                createTab(divisionView, "Divisions"),
                createTab(winnersView,"Winners")
        );

        updateList.add(teamTable);
        updateList.add(divisionView);
        updateList.add(winnersView);

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");

        MenuItem openPace = new MenuItem("Open");
        openPace.setOnAction(e -> {
            File file = Resources.promptOpenPace();
            if (file != null) {
                open(file);
            }
        });

        MenuItem savePaceAs = new MenuItem("Save As");
        savePaceAs.setOnAction(e -> {
            File file = Resources.promptSavePace();
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
     *
     */
    public void start(Stage stage) {
        appStage = stage;
        stage.getIcons().add(Resources.APPLICATION_ICON);
        stage.setOnCloseRequest(e -> {
            SettingsEditor.closeRequest();
            updateList.clear();
            TeamEditor.closeAll();
        });
        stage.setMaximized(settings.isAppMaximized());
        stage.maximizedProperty().addListener(e -> settings.setAppMaximized(stage.isMaximized()));
        Launcher.open();
    }

    /**
     * Adds an updatable to list, executing {@link Updatable#update()} each time the application is sent an update
     * @see #update()
     * @param updatable Updatable object that implements the {@link Updatable} interface
     *
     */
    public static void addUpdatable(Updatable updatable) {
        updateList.add(updatable);
    }

    /**
     * Removes an updatable from the update list. The object will no longer be pinged any time the application
     * is updated
     * @param updatable Updatable object that implements the {@link Updatable} interface
     * @return {@code true} if this list contained the specified element
     *
     */
    public static boolean removeUpdatable(Updatable updatable) {
        return updateList.remove(updatable);
    }

}
