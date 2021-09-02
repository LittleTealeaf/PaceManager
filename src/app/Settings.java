package app;

//Ideas: Additional Thread for Settings Saving

import java.io.File;
import java.io.FileWriter;

public class Settings {

    public static transient File settingsFile;

    /**
     * Current application version of the settings object
     *
     * @see App#version
     */
    private final String version;
    /**
     * Determines whether or not additional steps are taken to reduce memory used by the program
     * <p>Default: False
     */
    private boolean aggressiveMemorySave;
    /**
     * What the file extension should be for files opened by the program
     * <p>Default: {@code .pace}
     */
    private String fileExtension;

    private String paceDirectory;

    /**
     * Creates a new {@code Settings} object and sets values to their defaults.
     */
    public Settings() {
        aggressiveMemorySave = false;
        fileExtension = ".pace";
        version = App.version;
        paceDirectory = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar;
    }

    /**
     * Saves the current configuration
     */
    public void save() {
        if(settingsFile.getParentFile().mkdirs()) {
            System.out.println("Created " + settingsFile.getParentFile().getPath());
        }
        try {
            FileWriter writer = new FileWriter(settingsFile);
            Serialization.getGson().toJson(this,Settings.class,writer);
            writer.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @since 1.0.0
     * @return
     */
    public boolean getAggressiveMemorySave() {
        return aggressiveMemorySave;
    }

    /**
     * @since 1.0.0
     * @param value
     */
    public void setAggressiveMemorySave(boolean value) {
        //Only updates / saves if there is a change
        if (aggressiveMemorySave != value) {
            aggressiveMemorySave = value;
            save();
        }
    }

    /**
     * @since 1.0.0
     * @return
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * @since 1.0.0
     * @param fileExtension
     */
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        save();
    }

    public void setPaceDirectory(String directory) {
        paceDirectory = directory;
    }

    public String getPaceDirectory() {
        return paceDirectory;
    }
}
