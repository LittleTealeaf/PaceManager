package app;

//Ideas: Additional Thread for Settings Saving

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all current settings of the program. Stores these values in a location dependent on the operating
 * system. Or at least that is what it's supposed to do.
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Settings {

    /**
     * File object referencing to where the Settings JSON is stored
     */
    public static transient File settingsFile;

    /**
     * Current application version of the settings object
     *
     * @see App#version
     * @since 1.0.0
     */
    private final String version;
    /**
     * List of recently opened Pace files
     * @since 1.0.0
     * @see ui.Launcher
     */
    private final List<String> recentFiles;
    /**
     * Determines whether additional steps are taken to reduce memory used by the program.
     * <p>Default: False
     * @since 1.0.0
     */
    private boolean aggressiveMemorySave;
    /**
     * What the file extension should be for files opened by the program. Extensions are in the format {@code .___}
     *
     * @since 1.0.0
     */
    private String[] fileExtensions;
    /**
     * Default directory to begin looking for pace files in
     * @see ui.Launcher
     * @since 1.0.0
     */
    private String paceDirectory;

    /**
     * Whether to automatically save the file whenever any changes to the Pace have been saved.
     * @since 1.0.0
     */
    private boolean aggressiveSave;

    /**
     * Creates a new {@code Settings} object and sets values to their defaults.
     * @since 1.0.0
     */
    public Settings() {
        aggressiveMemorySave = false;
        fileExtensions = new String[]{".pace", ".json"};
        version = App.version;
        aggressiveSave = true;
        paceDirectory = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar;
        //If doesn't work, revert to home

        recentFiles = new ArrayList<>();
    }

    /**
     * Saves the current configuration
     *
     * @since 1.0.0
     */
    public void save() {
        if (settingsFile.getParentFile().mkdirs()) {
            System.out.println("Created " + settingsFile.getParentFile().getPath());
        }
        try {
            FileWriter writer = new FileWriter(settingsFile);
            Serialization.getGson().toJson(this, Settings.class, writer);
            writer.close();
        } catch (Exception ignored) {}
    }

    /**
     * Returns whether to aggressively save memory (ram)
     * @return {@code true} if the setting is set to true, {@code false} if the setting is set to false
     * @since 1.0.0
     * @see #aggressiveMemorySave
     */
    public boolean isAggressiveMemorySave() {
        return aggressiveMemorySave;
    }

    /**
     * Sets whether the program should be concerned about aggressively saving values
     * <p>Will save settings to the settings file after the modification</p>
     * @param value {@code true} if the program should take extra steps to reduce memory, {@code false} if not
     * @since 1.0.0
     * @see #aggressiveMemorySave
     */
    public void setAggressiveMemorySave(boolean value) {
        //Only updates / saves if there is a change
        if (aggressiveMemorySave != value) {
            aggressiveMemorySave = value;
            save();
        }
    }

    /**
     * Gets the list of file extensions used when opening a pace file
     * @return String array of valid file extensions. Extensions are in the format {@code .___}.
     * @since 1.0.0
     * @see #fileExtensions
     */
    public String[] getFileExtensions() {
        return fileExtensions;
    }

    /**
     * Sets the list of file extensions.
     * <p>Will save settings to the settings file after the modification</p>
     * @param fileExtensions String array of valid file extensions. Extensions are in the format {@code .___}.
     * @since 1.0.0
     * @see #fileExtensions
     */
    public void setFileExtensions(String[] fileExtensions) {
        this.fileExtensions = fileExtensions;
        save();
    }

    //TODO finish comments
    public String getPaceDirectory() {
        return paceDirectory;
    }

    public void setPaceDirectory(String directory) {
        paceDirectory = directory;
    }

    public List<String> getRecentFiles() {
        return recentFiles;
    }

    public void addRecentFile(String path) {
        if (path != null) {
            //Checks if file is already in
            for (String recentPath : recentFiles) {
                if (recentPath.contentEquals(path)) {
                    return;
                }
            }
            recentFiles.add(path);
            save();
        }
    }

    public void cleanRecentFiles() {
        int size = recentFiles.size();
        for (int i = 0; i < size; i++) {
            if (recentFiles.get(i) == null || !new File(recentFiles.get(i)).exists()) {
                recentFiles.remove(i--);
                size--;
            }
        }
    }

    public boolean isAggressiveSave() {
        return aggressiveSave;
    }

    public void setAggressiveSave(boolean aggressiveSave) {
        this.aggressiveSave = aggressiveSave;
        save();
    }
}
