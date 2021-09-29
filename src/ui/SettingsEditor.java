package ui;

import app.App;
import javafx.stage.Stage;

public class SettingsEditor extends Stage implements Updatable {
    public SettingsEditor() {
        super();
        setTitle("Settings");

        setOnCloseRequest(e -> App.settingsEditor = null);

        if (App.settingsEditor != null) {
            App.settingsEditor.close();
        }
        App.settingsEditor = this;

        show();
    }

    public void update() {

    }
}
