package ui;

import javafx.stage.Stage;

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
        stage.show();
    }

}
