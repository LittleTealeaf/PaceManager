package ui;

import data.Settings;
import javafx.application.Application;
import javafx.stage.Stage;

/*
Add additional thread to periodically save pace
Potentially add additional thread to have a "backup" of the pace
 */

public class App extends Application {

    public static Settings settings = new Settings(); //TODO get the base app directory and save settings

    private static Stage appStage;

    public static void main(String[] args) {
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
