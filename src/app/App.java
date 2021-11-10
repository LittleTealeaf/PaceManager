package app;

import com.google.gson.stream.JsonReader;
import data.Pace;
import exceptions.ExceptionAlert;
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
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class App extends Application {

    /**
     * Whether the build is a development build, which opens up some changes such as the example pulling
     */
    public static final boolean DEV_BUILD = true;

    /**
     * The current development version of the application.
     */
    public static final String version = "1.0.0";
    /**
     * Registered instance of the Settings object. Initialized on launch.
     */
    public static final Settings settings = Resources.getSettings();
    /**
     * The currently opened Pace. Only one Pace is opened at a time. Used as a reference for any code reading or
     * modifying the pace's state.
     */
    public static Pace openedPace;
    /**
     * The currently opened stage displaying the main application. If the application is closed back to the launcher,
     * this value is overwritten.
     */
    private static Stage appStage;
    /**
     * List of updatable objects. Master list of all objects in the queue for updating. Each time the application
     * updates, the {@link Updatable#update()} method is called from each object in this list.
     *
     * @see #update()
     */
    private static List<Updatable> updateList;

    private static String[] launchArguments;


    /**
     * Application Launch Point
     *
     * @param args Launch Arguments
     */
    public static void main(String... args) {
        updateList = new LinkedList<>();
        launchArguments = args == null ? new String[0] : args;
        launch(args);
    }

    public static void openIgnoreException(File file) {
        try {
            open(file);
        } catch (Exception e) {
            new ExceptionAlert(e);
        }
    }

    /**
     * Attempts to open a pace from a specified file path. First checks that the file is not null and exists. If the
     * file is invalid, then will open a new pace file. Note that the new pace file will not have a correlating file,
     * and will not attempt to save to the attempted file location.
     *
     * @param file Pace File to attempt to open. File extension does not matter.
     */
    public static void open(File file) throws Exception {
        openedPace = file == null ? (DEV_BUILD ? pace2021() : Pace.newPace()) : Pace.fromFile(file);
        appStage.setScene(generateScene());
        openedPace.save();
        appStage.show();
    }

    /**
     * <p>Creates a new tab element, configures it, and returns the tab</p>
     * <p><b>Modifications:</b>
     * <ul><li>Sets Closable to false</li><li>Sets the content to the provided node</li><li>Sets the title of the
     * tab to the provided name</li></ul></p>
     *
     * @param node Content of the pane. Content will be displayed in the tab-pane when the tab is selected and opened
     * @param name Name/Title of the tab. Text is displayed on the tab itself.
     *
     * @return Configured {@code Tab} object
     */
    private static Tab createTab(Node node, String name) {
        Tab tab = new Tab(name);
        tab.setClosable(false);
        tab.setContent(node);
        return tab;
    }

    /**
     * Calls the {@link Updatable#update()} method from all objects listed in {@link #updateList}
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
     *
     * @return Pace with values from 2021
     *
     * @since 1.0.0-development
     */
    private static Pace pace2021() {
        try {
            return Pace.fromJson(new JsonReader(new InputStreamReader(Resources.getResource("/dev/pace2021.json"))));
        } catch (Exception exception) {
            new ExceptionAlert(exception);
            return null;
        }
    }

    /**
     * Prompts the user to verify whether they want to delete an item
     *
     * @param name Display Name of the item the user may want to delete
     *
     * @return {@code True} if the user decided to delete, {@code false} otherwise.<br>If {@link App#settings} is {@code null}
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
     *
     * @return Scene with all the application elements within it
     */
    private static Scene generateScene() {
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();

        TeamTable teamTable = new TeamTable(() -> openedPace.getTeams());
        DivisionView divisionView = new DivisionView();
        WinnersView winnersView = new WinnersView();

        tabPane.getTabs().addAll(createTab(teamTable, "Teams"), createTab(divisionView, "Divisions"), createTab(winnersView, "Winners"));

        updateList.add(teamTable);
        updateList.add(divisionView);
        updateList.add(winnersView);

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");

        MenuItem openPace = new MenuItem("Open");
        openPace.setOnAction(e -> {
            File file = Resources.promptOpenPace();
            if (file != null) {
                openIgnoreException(file);
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
     * Adds an updatable to list, executing {@link Updatable#update()} each time the application is sent an update
     *
     * @param updatable Updatable object that implements the {@link Updatable} interface
     *
     * @see #update()
     */
    public static void addUpdatable(Updatable updatable) {
        updateList.add(updatable);
    }

    /**
     * Removes an updatable from the update list. The object will no longer be pinged any time the application
     * is updated
     *
     * @param updatable Updatable object that implements the {@link Updatable} interface
     *
     * @return {@code true} if this list contained the specified element
     */
    public static boolean removeUpdatable(Updatable updatable) {
        return updateList.remove(updatable);
    }

    /**
     * Application stage start point
     *
     * @see Application
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

        if (launchArguments.length > 0) {
            openIgnoreException(new File(launchArguments[0]));
        } else {
            Launcher.open();
        }
    }
}
