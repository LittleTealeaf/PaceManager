package data;

public class Settings {

    private boolean aggressiveMemorySave;

    public Settings() {
        //Default Settings
        aggressiveMemorySave = false;

    }

    public boolean getAggressiveMemorySave() {
        return aggressiveMemorySave;
    }

    public void setAggressiveMemorySave(boolean value) {
        aggressiveMemorySave = value;
    }
}
