package ui;

import app.App;
import app.Settings;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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

        settingNodes = generateSettings();

        GridPane gridPane = new GridPane();

        for (int i = 0; i < settingNodes.length; i++) {
            SettingNode settingNode = settingNodes[i];
            gridPane.addRow(i, new Text(settingNode.name), settingNode.getNode());
        }


        Scene scene = new Scene(gridPane);
        setScene(scene);
        update();
        show();
    }

    private SettingNode[] generateSettings() {
        return new SettingNode[]{
                new SettingNode("File Extensions", Category.FILES) {

                    TextField field;

                    @Override
                    public void initialize() {
                        field = new TextField();
                        field.focusedProperty().addListener((e, o, n) -> {
                            if (!e.getValue().booleanValue()) {
                                parse();
                            }
                        });

                        field.setOnKeyPressed(e -> {
                            if (e.getCode() == KeyCode.ENTER) {
                                parse();
                            }
                        });
                    }

                    private void parse() {
                        String text = field.getText();
                        text = text.replace(" ", "");
                        String[] extensions = text.split(",");
                        App.settings.setFileExtensions(extensions);
                        update();
                    }

                    @Override
                    public void update() {
                        StringBuilder text = new StringBuilder();
                        String[] extensions = App.settings.getFileExtensions();
                        for (int i = 0; i < extensions.length; i++) {
                            text.append(extensions[i]);
                            if (i < extensions.length - 1) {
                                text.append(", ");
                            }
                        }
                        field.setText(text.toString());
                    }

                    @Override
                    public Node getNode() {
                        return field;
                    }

                },
                new SettingNode("Aggressive Save", Category.OPTIMIZATIONS, Category.FILES) {
                    CheckBox checkBox;

                    @Override
                    public void initialize() {
                        checkBox = new CheckBox();
                        checkBox.setOnAction(e -> App.settings.setAggressiveSave(checkBox.isSelected()));
                    }

                    @Override
                    public void update() {
                        checkBox.setSelected(App.settings.isAggressiveSave());
                    }

                    @Override
                    public Node getNode() {
                        return checkBox;
                    }
                },
                new SettingNode("Warn on Delete",Category.GENERAL) {

                    CheckBox checkBox;

                    @Override
                    public void initialize() {
                        checkBox = new CheckBox();
                        checkBox.setOnAction(e -> App.settings.setWarnOnDelete(checkBox.isSelected()));
                    }

                    public void update() {
                        checkBox.setSelected(App.settings.warnOnDelete());
                    }

                    public Node getNode() {
                        return checkBox;
                    }
                },
                new SettingNode("Exclude Outliers",Category.CALCULATIONS,Category.GENERAL) {
                    CheckBox checkBox;

                    @Override
                    public void initialize() {
                        checkBox = new CheckBox();
                        checkBox.setOnAction(e -> {
                            App.settings.setExcludeOutliers(checkBox.isSelected());
                            App.update();
                        });
                    }

                    public void update() {
                        checkBox.setSelected(App.settings.excludeOutliers());
                    }

                    public Node getNode() {
                        return checkBox;
                    }
                },
                new SettingNode("Aggressive Save Memory", Category.OPTIMIZATIONS,Category.FILES) {
                    CheckBox checkBox;

                    public void initialize() {
                        checkBox = new CheckBox();
                        checkBox.setOnAction(e -> App.settings.setExcludeOutliers(checkBox.isSelected()));
                    }

                    public void update() {
                        checkBox.setSelected(App.settings.excludeOutliers());
                    }

                    public Node getNode() {
                        return checkBox;
                    }
                }
        };
    }

    public void update() {
        for (SettingNode settingNode : settingNodes) {
            settingNode.update();
        }
    }

    private abstract class SettingNode implements Updatable {

        Category[] categories;
        String name;

        public SettingNode(String name, Category... categories) {
            this.name = name;
            this.categories = categories;
            initialize();
        }

        public abstract void initialize();
        public abstract Node getNode();
    }

    enum Category {
        GENERAL("General"),FILES("Files"), OPTIMIZATIONS("Optimizations"), CALCULATIONS("Calculations");

        String display;

        Category(String display) {
            this.display = display;
        }

        public String toString() {
            return display;
        }
    }
}
