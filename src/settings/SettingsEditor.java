package settings;

import app.App;
import app.Resources;
import app.Updatable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * <p>Application window that allows the user to modify configurable settings. A panel on the left lists different
 * categories that settings are categorized under. When the user clicks or changes the category, the content on the
 * right changes to display the names and configuration elements of settings that are categorized under that
 * category.</p>
 * <p>Categories are derived from the {@link Category} enumerator</p>
 * <p>Configurable Settings are displayed using a {@link SettingNode}, which connects the link between a setting and its
 * editing node</p>
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @see Settings
 * @since 1.0.0
 */
public class SettingsEditor extends Stage implements Updatable {

    /**
     * The currently opened instance of the SettingsEditor. Prevents multiple instances from being opened
     */
    private static SettingsEditor openedInstance;

    /**
     * List of all editing node objects
     */
    private final SettingNode[] settingNodes;
    /**
     * GridPane where all currently-displaying settings are listed, children are cleared whenever category
     * is changed
     */
    private final GridPane settingsPanel;

    /**
     * Creates a new SettingsEditor. If there is already an instance opened, it will attempt to close that instance
     * and sets {@link #openedInstance} to itself. Also is set to set {@link #openedInstance} to null once it closes
     */
    public SettingsEditor () {
        super();
        setTitle("Settings");
        getIcons().add(Resources.APPLICATION_ICON);

        setOnCloseRequest(e -> {
            if (openedInstance == this) {
                openedInstance = null;
            }
            App.removeUpdatable(this);
        });

        if (openedInstance != null) {
            openedInstance.close();
        }
        openedInstance = this;

        BorderPane borderPane = new BorderPane();

        settingsPanel = new GridPane();
        settingsPanel.setPadding(new Insets(10));
        settingsPanel.setHgap(5);
        settingsPanel.setVgap(7);
        borderPane.setCenter(settingsPanel);

        settingNodes = generateSettings();

        ListView<Category> categoryPanel = new ListView<>();
        categoryPanel.getItems().addAll(Category.values());
        categoryPanel.getSelectionModel().selectedItemProperty().addListener((e, o, n) -> populateSettings(n));
        categoryPanel.getSelectionModel().select(0);
        borderPane.setLeft(categoryPanel);

        Scene scene = new Scene(borderPane);
        setScene(scene);
        update();
        show();
    }

    /**
     * Generates the entire list of settings to include in the SettingsEditor
     *
     * @return Array of Settings using the SettingNode class
     */
    private SettingNode[] generateSettings () {
        return new SettingNode[]{
                new SettingNode("File Extensions", Category.GENERAL, Category.FILES) {

                    TextField field;

                    public void initialize () {
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

                    private void parse () {
                        String text = field.getText();
                        text = text.replace(" ", "");
                        String[] extensions = text.split(",");
                        App.settings.setFileExtensions(extensions);
                        update();
                    }

                    public void update () {
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
                    public Node getNode () {
                        return field;
                    }

                }, new SettingNode("Aggressive Save", Category.OPTIMIZATIONS, Category.FILES, Category.APPLICATION) {
            CheckBox checkBox;

            public void initialize () {
                checkBox = new CheckBox();
                checkBox.setOnAction(e -> App.settings.setAggressiveSave(checkBox.isSelected()));
            }

            public Node getNode () {
                return checkBox;
            }

            public void update () {
                checkBox.setSelected(App.settings.isAggressiveSave());
            }
        }, new SettingNode("Warn on Delete", Category.GENERAL, Category.APPLICATION) {

            CheckBox checkBox;

            public void initialize () {
                checkBox = new CheckBox();
                checkBox.setOnAction(e -> App.settings.setWarnOnDelete(checkBox.isSelected()));
            }

            public Node getNode () {
                return checkBox;
            }

            public void update () {
                checkBox.setSelected(App.settings.warnOnDelete());
            }
        }, new SettingNode("Exclude Outliers", Category.CALCULATIONS, Category.GENERAL) {
            CheckBox checkBox;

            public void initialize () {
                checkBox = new CheckBox();
                checkBox.setOnAction(e -> {
                    App.settings.setExcludeOutliers(checkBox.isSelected());
                    App.update();
                });
            }

            public Node getNode () {
                return checkBox;
            }

            public void update () {
                checkBox.setSelected(App.settings.excludeOutliers());
            }
        }, new SettingNode("Aggressive Save Memory", Category.OPTIMIZATIONS, Category.FILES, Category.APPLICATION) {
            CheckBox checkBox;

            public void initialize () {
                checkBox = new CheckBox();
                checkBox.setOnAction(e -> App.settings.setAggressiveMemorySave(checkBox.isSelected()));
            }

            public Node getNode () {
                return checkBox;
            }

            public void update () {
                checkBox.setSelected(App.settings.excludeOutliers());
            }
        }, new SettingNode("Use Average as Default Goal Time", Category.CALCULATIONS, Category.GENERAL) {
            CheckBox checkBox;

            public void initialize () {
                checkBox = new CheckBox();
                checkBox.setOnAction(e -> App.settings.setUseAverageAsGoalTime(checkBox.isSelected()));
            }

            public Node getNode () {
                return checkBox;
            }

            public void update () {
                checkBox.setSelected(App.settings.useAverageAsGoalTime());
            }
        }, new SettingNode("Allow Multiple Team Editors open", Category.APPLICATION, Category.GENERAL) {
            CheckBox checkBox;

            public void initialize () {
                checkBox = new CheckBox();
                checkBox.setOnAction(e -> App.settings.setMultipleTeamsEditing(checkBox.isSelected()));
            }

            public Node getNode () {
                return checkBox;
            }

            public void update () {
                checkBox.setSelected(App.settings.isMultipleTeamsEditing());
            }
        }
        };
    }

    /**
     * Updates the GridPane with all settings that are classified under the specified category
     *
     * @param category Filter of which settings to display
     */
    private void populateSettings (Category category) {
        settingsPanel.getChildren().clear();
        int index = 0;
        final Font font = new Font(13);
        for (SettingNode settingNode : settingNodes) {
            if (settingNode.isCategory(category)) {
                Text label = new Text(settingNode.getName());
                label.setFont(font);
                settingsPanel.addRow(index++, label, settingNode.getNode());
            }
        }
    }

    /**
     * Sends an update ping to all {@code SettingNodes} in {@link #settingNodes}
     */
    public void update () {
        for (SettingNode settingNode : settingNodes) {
            settingNode.update();
        }
    }

    /**
     * Requests the current instance to be closed, only does so if it is populated.
     */
    public static void closeRequest () {
        if (openedInstance != null) {
            openedInstance.close();
            openedInstance = null;
        }
    }

}
