package app;

//Ideas: Additional Thread for Settings Saving

public class Settings {

    private String version;
    private boolean aggressiveMemorySave;
    private String fileExtension;

    public Settings() {
        aggressiveMemorySave = false;
        fileExtension = ".pace";
        version = App.version;
    }

    /**
     * Saves the current configuration
     */
    public void save() {

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
    }
}
