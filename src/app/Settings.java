package app;

//Ideas: Additional Thread for Settings Saving

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Settings {

    public static transient File settingsFile;

    /**
     * Current application version of the settings object
     *
     * @see App#version
     */
    private final String version;
    private final List<String> recentFiles;
    /**
     * Determines whether or not additional steps are taken to reduce memory used by the program
     * <p>Default: False
     */
    private boolean aggressiveMemorySave;
    /**
     * What the file extension should be for files opened by the program
     * <p>Default: {@code .pace}
     */
    private String[] fileExtensions;
    private String paceDirectory;

    /**
     * Whether or not to automatically save the file whenever any changes to the Pace have been saved
     */
    private boolean aggressiveSave;

    /**
     * Creates a new {@code Settings} object and sets values to their defaults.
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
     * @return
     * @since 1.0.0
     */
    public boolean isAggressiveMemorySave() {
        return aggressiveMemorySave;
    }

    /**
     * @param value
     * @since 1.0.0
     */
    public void setAggressiveMemorySave(boolean value) {
        //Only updates / saves if there is a change
        if (aggressiveMemorySave != value) {
            aggressiveMemorySave = value;
            save();
        }
    }

    /**
     * @return
     * @since 1.0.0
     */
    public String[] getFileExtensions() {
        return fileExtensions;
    }

    /**
     * @param fileExtensions
     * @since 1.0.0
     */
    public void setFileExtensions(String[] fileExtensions) {
        this.fileExtensions = fileExtensions;
        save();
    }

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
