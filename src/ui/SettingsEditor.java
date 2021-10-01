package ui;

import app.App;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SettingsEditor extends Stage implements Updatable {

    SettingNode[] settingNodes;

    public SettingsEditor() {
        super();
        setTitle("Settings");

        setOnCloseRequest(e -> App.settingsEditor = null);

        if (App.settingsEditor != null) {
            App.settingsEditor.close();
        }
        App.settingsEditor = this;

        settingNodes = new SettingNode[] {
                new SettingNode("File Extensions",Category.FILES) {
                    @Override
                    public void update() {

                    }

                    @Override
                    public void initialize() {

                    }
                }
        };


        Scene scene = new Scene(new BorderPane());
        setScene(scene);
        show();
    }

    public void update() {
        for(SettingNode settingNode : settingNodes) {
            settingNode.update();
        }
    }

    private abstract class SettingNode implements Updatable {

        Category[] categories;
        String name;
        Node node;

        public SettingNode(String name, Category... categories) {
            this.name = name;
            this.categories = categories;
        }

        public abstract void update();
        public abstract void initialize();
    }

    enum Category {
        FILES("Files");

        String display;
        Category(String display) {
            this.display = display;
        }

        public String toString() {
            return display;
        }
    }
}
