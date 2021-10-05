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
 * @see Settings
 * @author Thomas Kwashnak
 * @since 1.0.0
 * @version 1.0.0
 */
public class SettingsEditor extends Stage implements Updatable {

    /**
     * The currently opened instance of the SettingsEditor
     */
    private static SettingsEditor openedInstance;

    private final SettingNode[] settingNodes;
    private final GridPane settingsPanel;

    public SettingsEditor() {
        super();
        setTitle("Settings");
        getIcons().add(Resources.APPLICATION_ICON);

        setOnCloseRequest(e -> {
            openedInstance = null;
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
        categoryPanel.getSelectionModel().selectedItemProperty().addListener((e,o,n) -> populateSettings(n));
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
                Text label = new Text(settingNode.getName());
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
                new SettingNode("File Extensions", Category.GENERAL, Category.FILES) {

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
                new SettingNode("Aggressive Save", Category.OPTIMIZATIONS, Category.FILES, Category.APPLICATION) {
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
                new SettingNode("Warn on Delete", Category.GENERAL, Category.APPLICATION) {

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
                new SettingNode("Exclude Outliers", Category.CALCULATIONS, Category.GENERAL) {
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
                new SettingNode("Aggressive Save Memory", Category.OPTIMIZATIONS, Category.FILES, Category.APPLICATION) {
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
                new SettingNode("Use Average as default Goal Time", Category.CALCULATIONS, Category.GENERAL) {
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
                },
                new SettingNode("Allow Multiple Team Editors open", Category.APPLICATION, Category.GENERAL) {
                    CheckBox checkBox;

                    public void initialize() {
                        checkBox = new CheckBox();
                        checkBox.setOnAction(e -> App.settings.setMultipleTeamsEditing(checkBox.isSelected()));
                    }

                    public void update() {
                        checkBox.setSelected(App.settings.isMultipleTeamsEditing());
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



    public static void closeRequest() {
        if(openedInstance != null) {
            openedInstance.close();
        }
    }
}
