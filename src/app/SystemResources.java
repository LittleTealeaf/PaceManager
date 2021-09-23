package app;

import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;

public class SystemResources {

    /**
     * Provides a working directory for use of data, settings, or other files within the project.
     * <p>Uses {@link System#getProperty(String) System.getProperty("user.home")} to obtain the base directory, and then includes a
     * sub directory for paceManager. This folder is set as hidden on all systems except windows by naming the directory ".paceManager".
     * <p>
     * Future versions may move this folder to {@code %APPDATA%} on windows machines, or similarly change the location on other
     * platforms as well.
     *
     * @return String directory path of the working directory, such as {@code C:\Users\Tealeaf\.paceManager\}
     * @version 1.0.0
     * @see File#separatorChar
     * @since 1.0.0
     */
    public static String getWorkingDirectory() {
        return System.getProperty("user.home") + File.separatorChar + ".paceManager" + File.separatorChar;
    }

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
        settings.save();
        return settings;
    }
}
