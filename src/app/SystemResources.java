package app;

import com.google.gson.stream.JsonReader;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

/**
 * Useful system resources used throughout the program, typically referencing to file locations and similar activities
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class SystemResources {

    public SystemResources() {}

    /**
     * Provides a working directory for use of data, settings, or other files within the project.
     * <p>Uses {@link System#getProperty(String) System.getProperty("user.home")} to obtain the base directory, and then includes a
     * subdirectory for paceManager. This folder is set as hidden on all systems except windows by naming the directory ".paceManager".
     * <p>
     * Future versions may move this folder to {@code %APPDATA%} on Windows machines, or similarly change the location on other
     * platforms as well.
     *
     * @return String directory path of the working directory, such as {@code C:\Users\Thomas\.paceManager\}
     * @see File#separatorChar
     * @since 1.0.0
     */
    public static String getWorkingDirectory() {
        return System.getProperty("user.home") + File.separatorChar + ".paceManager" + File.separatorChar;
    }

    /**
     * Attempts to get the settings file from the predicted location. If no file exists, or if there was an error in
     * parsing the Settings file, it will create a new settings object and save it, overwriting the damaged file if
     * it had exists
     *
     * @return Settings object with program settings
     * @see Settings
     * @since 1.0.0
     */
    public static Settings getSettings() {
        Settings.settingsFile = new File(getWorkingDirectory() + "config.json");
        Settings settings = new Settings();
        try {
            if (Settings.settingsFile.exists()) {
                JsonReader reader = new JsonReader(new FileReader(Settings.settingsFile));
                settings = Serialization.getGson().fromJson(reader, Settings.class);
                if (settings == null) {
                    settings = new Settings();
                }
            }
        } catch (Exception ignored) {}
        assert settings != null;
        settings.save();
        return settings;
    }

    public static File promptOpenPace() {
        FileChooser fileChooser = generatePacePrompt();
        fileChooser.setTitle("Open Pace");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            App.settings.setPaceDirectory(file.getParent());
        }
        return file == null || !file.exists() ? null : file;
    }

    public static File promptSavePace() {
        FileChooser fileChooser = generatePacePrompt();
        fileChooser.setTitle("Save Pace");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            App.settings.setPaceDirectory(file.getParent());
        }
        return file;
    }

    private static FileChooser generatePacePrompt() {
        FileChooser fileChooser = new FileChooser();
        File startingDirectory = new File(App.settings.getPaceDirectory());
        if (!startingDirectory.exists()) {
            App.settings.setPaceDirectory((startingDirectory = new File(System.getProperty("user.home"))).getPath());
        }
        fileChooser.setInitialDirectory(startingDirectory);
        for (String ext : App.settings.getFileExtensions()) {
            String display = ext.substring(1).toUpperCase() + " files (*" + ext + ")";
            String filter = "*" + ext;
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(display, filter));
        }
        return fileChooser;
    }

    public static InputStream getResourceStream(String resourcePath) {
        return SystemResources.class.getClassLoader().getResourceAsStream(resourcePath);
    }
}
