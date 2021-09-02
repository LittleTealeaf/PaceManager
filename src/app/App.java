package app;

import data.Pace;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.Launcher;

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
        settings.addRecentFile(filePath);
        if (filePath != null) {
            openedPace = Pace.fromFile(new File(filePath));
            openedPace.save();
        } else {
            openedPace = new Pace();
        }
        appStage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        appStage = stage;
        Launcher.open();
    }
}
