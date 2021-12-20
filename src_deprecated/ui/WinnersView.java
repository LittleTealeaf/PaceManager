package ui;

import app.App;
import app.Updatable;
import data_deprecated.Division;
import javafx.scene.control.TabPane;

public class WinnersView extends TabPane implements Updatable {

    public WinnersView() {
        super();
        update();
    }

    public void update() {
        getTabs().clear();
        for(Division division : App.openedPace.getDivisions()) {
            getTabs().add(new WinnersTab(division));
        }
    }
}
