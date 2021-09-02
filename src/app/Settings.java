package app;

//Ideas: Additional Thread for Settings Saving

public class Settings {

    private boolean aggressiveMemorySave;

    public Settings() {
        //Default Settings
        aggressiveMemorySave = false;

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
}
