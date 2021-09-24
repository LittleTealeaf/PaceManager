package app;

import data.Division;
import data.Pace;
import data.Team;
import data.Time;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.Launcher;
import ui.TeamTable;

import java.io.File;

/*
Add additional thread to periodically save pace
Potentially add additional thread to have a "backup" of the pace
 */

/**
 * Application Class, includes the starting point of the program and additional universally-accessible references
 */
public class App extends Application {

    /**
     * Current PaceManager version of the code
     *
     * @since 1.0.0
     */
    public static final String version = "1.0.0";
    /**
     * Application wide settings object
     *
     * @since 1.0.0
     */
    public static final Settings settings = SystemResources.getSettings();

    /**
     * Currently opened pace
     *
     * @since 1.0.0
     */
    public static Pace openedPace;

    /**
     * JavaFX Main Application Stage
     *
     * @since 1.0.0
     */
    private static Stage appStage;

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
        } else {
//            openedPace = new Pace();
            openedPace = testPace();
        }
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(new TeamTable(openedPace));

        Scene scene = new Scene(borderPane);
        openedPace.save();
        appStage.show();
//        new TeamEditor(openedPace.getTeams().get((int) (openedPace.getTeams().size() * Math.random())));
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


    /**
     * Application stage start point
     *
     * @see Application
     * @since 1.0.0
     */
    @Override
    public void start(Stage stage) {
        appStage = stage;
        Launcher.open();
    }
}
