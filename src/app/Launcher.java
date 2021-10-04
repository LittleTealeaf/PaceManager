package app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.SettingsEditor;

import java.io.File;

/**
 * Pace-Launcher allowing the user to open a pace, create a new pace, or open a recently opened pace
 * @author Thomas Kwashnak
 * @since 1.0.0
 * @version 1.0.0
 * @bug Clicking on a recent file and then clicking on empty space opens the selected item
 */
public class Launcher {

    /*
    Things needed on the launcher:
     - Option to create a new Hunter Pace
     - Option to open pre-existing hunter pace
     - List of changelogs / info
     - List of old Hunter Paces
     - Exit button
     */

    /**
     * Currently opened stage of launcher, is null if the launcher is not opened
     * @since 1.0.0
     */
    private static Stage stage;

    /**
     * Closes any previous persistent stages, opens the launcher as a new stage, and sets up interface.
     * @since 1.0.0
     */
    public static void open() {
        if(stage != null && stage.isShowing()) {
            stage.close();
        }
        stage = new Stage();
        stage.getIcons().add(SystemResources.APPLICATION_ICON);
        stage.setTitle("Pace Manager " + App.version);
        stage.setMaximized(App.settings.isLauncherMaximized());
        stage.maximizedProperty().addListener(e -> App.settings.setLauncherMaximized(stage.isMaximized()));

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setLeft(buttonPanel());
        borderPane.setCenter(generateRecentFiles());

        Scene scene = new Scene(borderPane);


        stage.setScene(scene);
        stage.setMinWidth(500);

        stage.show();
    }

    /**
     * Creates the vertical set of buttons on the left side of the launcher
     * @return VBox element containing all buttons
     * @since 1.0.0
     */
    private static VBox buttonPanel() {
        VBox panel = new VBox();
        panel.setSpacing(10);
        panel.setPadding(new Insets(10));
        for (Button button : generateButtons()) {
            panel.getChildren().add(button);
        }
        return panel;
    }

    /**
     * Generates the list of buttons to be included on the left hand of the launcher
     * @return Array of buttons to include
     * @since 1.0.0
     */
    private static Button[] generateButtons() {
        //New, Open, Copy, Info, Exit
        Button[] buttons = new Button[5];

        buttons[0] = new Button("New");
        buttons[0].setOnAction(e -> openPace(null));

        buttons[1] = new Button("Open");
        buttons[1].setOnAction(e -> {
            File file = SystemResources.promptOpenPace();
            if (file != null) {
                openPace(file);
            }
        });

        buttons[2] = new Button("Info");

        buttons[3] = new Button("Settings");
        buttons[3].setOnAction(e -> new SettingsEditor());

        buttons[4] = new Button("Close");
        buttons[4].setOnAction(e -> System.exit(0));

        return buttons;

    }

    /**
     * Generates the borderpane responsible for displaying all recently opened files
     * @return Border Pane with all elements pertaining to listing recent files
     * @since 1.0.0
     */
    private static BorderPane generateRecentFiles() {
        BorderPane borderPane = new BorderPane();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));

        ListView<String> recentFiles = new ListView<>();
        recentFiles.getItems().setAll(App.settings.getRecentFiles());
        recentFiles.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
                if (recentFiles.getSelectionModel().getSelectedItem() != null) {
                    openPace(new File(recentFiles.getSelectionModel().getSelectedItem()));
                }
            }
        });
        recentFiles.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case DELETE, BACK_SPACE -> {
                    String selected = recentFiles.getSelectionModel().getSelectedItem();
                    if (selected != null) {
                        App.settings.getRecentFiles().remove(selected);
                        recentFiles.getItems().setAll(App.settings.getRecentFiles());
                    }
                }
                case ENTER, SPACE -> openPace(new File(recentFiles.getSelectionModel().getSelectedItem()));
            }
        });

        borderPane.setCenter(recentFiles);
        borderPane.setTop(new Label("Recent Files"));

        return borderPane;
    }

    /**
     * Opens a selected pace in the application, closes the launcher and marks launcher for garbage collection
     * @param file File of pace to open
     * @since 1.0.0
     * @see App#open(File)
     */
    private static void openPace(File file) {
        App.open(file);
        stage.close();
        stage = null;
    }

}
