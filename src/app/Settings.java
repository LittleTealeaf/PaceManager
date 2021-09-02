package app;

//Ideas: Additional Thread for Settings Saving

public class Settings {

    private boolean aggressiveMemorySave;
    private String fileExtension;

    public Settings() {
        aggressiveMemorySave = false;
        fileExtension = ".pace";
    }

    /**
     * Saves the current configuration
     */
    public void save() {

    }

    public boolean getAggressiveMemorySave() {
        return aggressiveMemorySave;
    }

    public void setAggressiveMemorySave(boolean value) {
        //Only updates / saves if there is a change
        if (aggressiveMemorySave != value) {
            aggressiveMemorySave = value;
            save();
        }
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
