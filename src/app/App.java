package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.Launcher;

/*
Add additional thread to periodically save pace
Potentially add additional thread to have a "backup" of the pace
 */

public class App extends Application {

    public static final String version = "1.0.0";
    public static final Settings settings = new Settings(); //TODO get the base app directory and save settings

    private static Stage appStage;

    public static void main(String[] args) {
        System.out.println(Serialization.getWorkingDirectory());
        launch(args);
    }

    public static void show() {
        appStage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        appStage = stage;
        Launcher.open();
    }

}
