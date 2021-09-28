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

import java.io.File;

//TODO update javadocs
public class Launcher {

    /*
    Things needed on the launcher:
     - Option to create a new Hunter Pace
     - Option to open pre-existing hunter pace
     - List of changelogs / info
     - List of old Hunter Paces
     - Exit button
     */

    //TODO BUG clicking on a selected item and then clicking on empty space opens the selected item


    /**
     * @since 1.0.0
     */
    private static Stage stage;

    /**
     * @since 1.0.0
     */
    public static void open() {
        stage = new Stage();
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
     * @return
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
     * @return
     * @since 1.0.0
     */
    private static Button[] generateButtons() {
        //New, Open, Copy, Info, Exit
        Button[] buttons = new Button[4];

        buttons[0] = new Button("New");
        buttons[0].setOnAction(e -> open(null));

        buttons[1] = new Button("Open");
        buttons[1].setOnAction(e -> {
            File file = SystemResources.promptOpenPace();
            if (file != null) {
                open(file);
            }
        });

        buttons[2] = new Button("Info");

        buttons[3] = new Button("Close");
        buttons[3].setOnAction(e -> System.exit(0));

        return buttons;

    }

    /**
     * @return
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
                    open(new File(recentFiles.getSelectionModel().getSelectedItem()));
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
                case ENTER, SPACE -> open(new File(recentFiles.getSelectionModel().getSelectedItem()));
            }
        });

        borderPane.setCenter(recentFiles);
        borderPane.setTop(new Label("Recent Files"));

        return borderPane;
    }

    /**
     * @param file
     * @since 1.0.0
     */
    private static void open(File file) {
        App.open(file);
        stage.close();
    }

}
