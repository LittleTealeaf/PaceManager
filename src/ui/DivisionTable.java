package ui;

import app.App;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DivisionTable extends GridPane implements Updatable {

    public DivisionTable() {
        super();
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10));
        update();
    }

    public void update() {
        getChildren().clear();
        App.pingUpdate();

        String[] headers = new String[]{"Division", "Num Riders", "Average", "Goal Time", "Deviation", "Deviation %"};
        for (int i = 0; i < headers.length; i++) {
            Label label = new Label(headers[i]);
            add(label, i, 0);
        }
    }
}
