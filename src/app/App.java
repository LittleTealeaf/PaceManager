package app;

import com.google.gson.stream.JsonReader;
import data.Division;
import data.Pace;
import data.Team;
import data.Time;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.DivisionView;
import ui.QuickImport;
import ui.TeamTable;
import ui.Updatable;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/*
Add additional thread to periodically save pace
Potentially add additional thread to have a "backup" of the pace
 */

//TODO figure out if removing the general application thing and just making stages and showing them would work better

/**
 * Application Class, includes the starting point of the program and additional universally-accessible references
 */
public class App extends Application {

    public static final String version = "1.0.0";
    public static final Settings settings = SystemResources.getSettings();
    public static Pace openedPace;
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

    private static Tab createTab(Node node, String name) {
        Tab tab = new Tab(name);
        tab.setClosable(false);
        tab.setContent(node);
        return tab;
    }

    public static void update() {
        openedPace.update();
        if (updateList != null) {
            for (Updatable updatable : updateList) {
                updatable.update();
            }
        }
    }

    /**
     * A debugging pace generator, creates a debug.json pace with 50 teams, of random divisions, start times, and end times
     *
     * @return Generated Pace
     * @since 1.0.0
     */
    private static Pace testPace() {
        Pace pace = new Pace();
        pace.setFile(new File(settings.getPaceDirectory() + "\\debug.json"));

        pace.addDivision(new Division("Hunt"));
        pace.addDivision(new Division("Western"));
        pace.addDivision(new Division("Pleasure"));
        pace.addDivision(new Division("Junior"));

        for (int i = 0; i < 50; i++) {
            Team team = new Team();
            if (Math.random() * 100 > 30) {
                team.setDivision(pace.getDivisions().get((int) (Math.random() * 4)));
            }
            if (Math.random() * 100 > 40) {
                //Random Start Time, start is fromm 7 am to 12 pm
                final long MILLISECONDS_PER_HOUR = 3600000;
                team.setStartTime(
                        new Time((long) (Math.random() * MILLISECONDS_PER_HOUR * 5) + MILLISECONDS_PER_HOUR * 7));
                if (Math.random() * 100 > 50) {
                    team.setEndTime(new Time(
                            (long) (Math.random() * MILLISECONDS_PER_HOUR * 3) + team.getStartTime().getValue()));
                }
            }
            team.setTeamNumber("A" + i);
            pace.getTeams().add(team);
        }

        pace.save();
        return pace;
    }

    private static Pace pace2021() {
        InputStream stream = SystemResources.getResourceStream("dev/pace2021.json");
        System.out.println(stream);
        return Pace.fromJson(new JsonReader(new InputStreamReader(stream)));
    }

    /**
     * Prompts the user to verify whether they want to delete an item
     *
     * @param name Display Name of the item the user may want to delete
     * @return {@code True} if the user decided to delete, {@code false} otherwise.
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
     * Application stage start point
     *
     * @see Application
     * @since 1.0.0
     */
    @Override
    public void start(Stage stage) {
        appStage = stage;
        stage.setMaximized(settings.isAppMaximized());
        stage.maximizedProperty().addListener(e -> settings.setAppMaximized(stage.isMaximized()));
        Launcher.open();
    }

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

        MenuItem exitApp = new MenuItem("Exit");
        exitApp.setOnAction(e -> {
            System.exit(0);
        });

        Menu menuTools = new Menu("Tools");

        MenuItem quickImport = new MenuItem("Quick Import");
        quickImport.setOnAction(e -> {
            new QuickImport();
        });

        menuBar.getMenus().addAll(menuFile, menuTools);
        menuFile.getItems().addAll(openPace, savePace, savePaceAs, closePace, exitApp);
        menuTools.getItems().addAll(quickImport);


        borderPane.setTop(menuBar);
        borderPane.setCenter(tabPane);


        return new Scene(borderPane);
    }


}
