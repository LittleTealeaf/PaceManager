package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Settings;
import data.Time;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.Launcher;

import java.io.File;

/*
Add additional thread to periodically save pace
Potentially add additional thread to have a "backup" of the pace
 */

public class App extends Application {

    public static Settings settings = new Settings(); //TODO get the base app directory and save settings

    private static Stage appStage;

    public static void main(String[] args) {
        System.out.println(getWorkingDirectory());
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

    /**
     * Provides the working directory for Application Files
     * @return File Path of the working directory
     */
    public static String getWorkingDirectory() {
        return System.getProperty("user.home") + File.separatorChar + ".paceManager" + File.separatorChar;
    }

    private static Gson builder;

    public static Gson getGson() {
        return builder != null ? builder : (builder = createGson());
    }

    private static Gson createGson() {
        return new GsonBuilder().excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT)
                                .registerTypeAdapter(Time.class, new Time.TimeSerializer())
                                .registerTypeAdapter(Time.class, new Time.TimeDeserializer()).create();
    }
}
