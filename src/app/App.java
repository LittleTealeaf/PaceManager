package app;

import data.Division;
import data.Pace;
import data.Team;
import data.Time;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.Launcher;
import ui.TeamEditor;

import java.io.File;

/*
Add additional thread to periodically save pace
Potentially add additional thread to have a "backup" of the pace
 */

public class App extends Application {

    /**
     * Current PaceManager version of the code
     */
    public static final String version = "1.0.0";
    public static final Settings settings = SystemResources.getSettings();

    public static Pace openedPace;

    private static Stage appStage;

    public static void main(String[] args) {
        launch(args);

    }

    public static void open(String filePath) {
        if (filePath != null) {
            openedPace = Pace.fromFile(new File(filePath));
            openedPace.save();
        } else {
//            openedPace = new Pace();
            openedPace = testPace();
            openedPace.save();
        }
        appStage.show();
        new TeamEditor(openedPace.getTeams().get((int) (openedPace.getTeams().size() * Math.random())));
    }

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

    @Override
    public void start(Stage stage) throws Exception {
        appStage = stage;
        Launcher.open();
    }
}
