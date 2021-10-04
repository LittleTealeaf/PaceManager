package ui;

import app.App;
import app.Settings;
import app.SystemResources;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

public class SettingsEditor extends Stage implements Updatable {

    private SettingNode[] settingNodes;
    private GridPane settingsPanel;

    public SettingsEditor() {
        super();
        setTitle("Settings");
        getIcons().add(App.appIcon);

        setOnCloseRequest(e -> App.settingsEditor = null);

        if (App.settingsEditor != null) {
            App.settingsEditor.close();
        }
        App.settingsEditor = this;

        BorderPane borderPane = new BorderPane();

        settingsPanel = new GridPane();
        settingsPanel.setPadding(new Insets(10));
        settingsPanel.setHgap(5);
        settingsPanel.setVgap(7);
        borderPane.setCenter(settingsPanel);

        settingNodes = generateSettings();

        ListView<Category> categoryPanel = new ListView<>();
        categoryPanel.getItems().addAll(Category.values());
        categoryPanel.getSelectionModel().selectedItemProperty().addListener((e,o,n) -> {
            populateSettings(n);
        });
        categoryPanel.getSelectionModel().select(0);
        borderPane.setLeft(categoryPanel);

        Scene scene = new Scene(borderPane);
        setScene(scene);
        update();
        show();
    }

    private void populateSettings(Category category) {
        settingsPanel.getChildren().clear();
        int index = 0;
        final Font font = new Font(13);
        for(SettingNode settingNode : settingNodes) {
            if (settingNode.isCategory(category)) {
                Text label = new Text(settingNode.name);
                label.setFont(font);
                settingsPanel.addRow(index++,label,settingNode.getNode());
            }
        }
    }

    /**
     * Generates the entire list of settings to include in the SettingsEditor
     * @return Array of Settings using the SettingNode class
     */
    private SettingNode[] generateSettings() {
        return new SettingNode[]{
                new SettingNode("File Extensions", Category.GENERAL,Category.FILES) {

                    TextField field;

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

                    public void initialize() {
                        checkBox = new CheckBox();
                        checkBox.setOnAction(e -> App.settings.setAggressiveSave(checkBox.isSelected()));
                    }

                    public void update() {
                        checkBox.setSelected(App.settings.isAggressiveSave());
                    }

                    public Node getNode() {
                        return checkBox;
                    }
                },
                new SettingNode("Warn on Delete",Category.GENERAL) {

                    CheckBox checkBox;

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
                },
                new SettingNode("Use Average as default Goal Time",Category.CALCULATIONS,Category.GENERAL) {
                    CheckBox checkBox;

                    public void initialize() {
                        checkBox = new CheckBox();
                        checkBox.setOnAction(e -> App.settings.setUseAverageAsGoalTime(checkBox.isSelected()));
                    }

                    public void update() {
                        checkBox.setSelected(App.settings.useAverageAsGoalTime());
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

    /**
     * Abstract class depicting a SettingNode, functionality should be implemented using the following methods
     * <ul><li>{@link #initialize()}: Executed immediately after the {@link #SettingNode(String, Category...)} constructor</li>
     * <li>{@link #getNode()}: Returns the node of the setting editor</li></ul>
     * @author Thomas Kwashnak
     * @since 1.0.0
     * @version 1.0.0
     */
    private abstract class SettingNode implements Updatable {

        /**
         * Array of categories the setting is classified under
         */
        Category[] categories;
        /**
         * Name of the setting
         */
        String name;

        /**
         * Creates a new abstract SettingNode with the setting name and its categories
         * @param name Title of the setting
         * @param categories List of categories to list the setting under
         */
        public SettingNode(String name, Category... categories) {
            this.name = name;
            this.categories = categories;
            initialize();
        }


        /**
         * Checks if the SettingNode is classified under a specific category
         * @param category Category to check if SettingNode is a part of
         * @return {@code true} if the setting node is classified under the given category, {@code false} otherwise
         */
        public boolean isCategory(Category category) {
            for(Category c : categories) {
                if(c == category) {
                    return true;
                }
            }
            return false;
        }

        /**
         * Method called immediately after the constructor, used for initializing and configuring the editing nodes
         */
        public abstract void initialize();

        /**
         * Returns the node used to edit the setting
         * @return Node of the setting editor
         */
        public abstract Node getNode();
    }

    /**
     * Categorization of settings. Each setting may have one or many categories. Filters within the SettingsEditor
     * allow the user to filter out settings by category.
     */
    enum Category {
        /**
         * Category for more general settings that the user will most likely wish to change at some point
         */
        GENERAL("General"),
        /**
         * Settings pertaining more to the calculation of average times and winners
         */
        CALCULATIONS("Calculations"),
        /**
         * Settings pertaining to the storing of config or other files
         */
        FILES("Files"),
        /**
         * Settings pertaining to application optimizations, such as freeing up memory (allowing for garbage collection)
         * or other optimizations
         */
        OPTIMIZATIONS("Optimizations");


        String display;

        Category(String display) {
            this.display = display;
        }

        public String toString() {
            return display;
        }
    }
}
