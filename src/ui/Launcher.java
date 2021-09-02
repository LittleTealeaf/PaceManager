package ui;

import app.App;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Launcher {

    /*
    Things needed on the launcher:
     - Option to create a new Hunter Pace
     - Option to open pre-existing hunter pace
     - List of changelogs / info
     - List of old Hunter Paces
     - Exit button
     */

    private static Stage stage;

    public static void open() {
        stage = new Stage();
        stage.setTitle("Pace Manager " + App.version);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setLeft(buttonPanel());
        borderPane.setCenter(generateRecentFiles());

        Scene scene = new Scene(borderPane);


        stage.setScene(scene);
        stage.setMinWidth(500);

        stage.show();
    }

    private static VBox buttonPanel() {
        VBox panel = new VBox();
        panel.setSpacing(10);
        panel.setPadding(new Insets(10));
        for (Button button : generateButtons()) {
            panel.getChildren().add(button);
        }
        return panel;
    }

    private static Button[] generateButtons() {
        //New, Open, Copy, Info, Exit
        Button[] buttons = new Button[4];

        buttons[0] = new Button("New");
        buttons[0].setOnAction(e -> open(null));

        buttons[1] = new Button("Open");
        buttons[1].setOnAction(e -> {
            String path = openFile();
            if (path != null) {
                open(path);
            }
        });

        buttons[2] = new Button("Info");

        buttons[3] = new Button("Close");
        buttons[3].setOnAction(e -> System.exit(0));

        return buttons;

    }

    private static BorderPane generateRecentFiles() {
        BorderPane borderPane = new BorderPane();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPadding(new Insets(10));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        updateRecentFiles(gridPane);

        scrollPane.setContent(gridPane);
        borderPane.setCenter(scrollPane);
        borderPane.setTop(new Label("Recent Files"));

        return borderPane;
    }

    private static void updateRecentFiles(GridPane gridPane) {
        int size = App.settings.getRecentFiles().size();
        for (int i = 0; i < size; i++) {
            String path = App.settings.getRecentFiles().get(i);
            if (path != null && new File(path).exists()) {
                Label fileLabel = new Label(path);
                fileLabel.setOnMouseClicked(e -> {
                    if (e.getClickCount() == 2) {
                        open(path);
                    }
                });
                gridPane.add(fileLabel, 0, i);

            } else {
                App.settings.getRecentFiles().remove(i--);
            }
        }
    }

    private static String openFile() {
        FileChooser prompt = new FileChooser();
        File startingDirectory = new File(App.settings.getPaceDirectory());
        if (!startingDirectory.exists()) {
            App.settings.setPaceDirectory(System.getProperty("user.home"));
            return openFile();
        }
        prompt.setInitialDirectory(startingDirectory);
        prompt.getExtensionFilters().add(new FileChooser.ExtensionFilter("PACE files (*.pace)", "*.pace"));

        File file = prompt.showOpenDialog(stage);
        if (file != null) {
            App.settings.setPaceDirectory(file.getParent());
            return file.getPath();
        } else {
            return null;
        }
    }

    private static void open(String path) {
        App.open(path);
        stage.close();
    }

}
