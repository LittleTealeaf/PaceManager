package ui;

import data.Pace;
import data.Team;
import javafx.scene.control.TableView;

/**
 * @author Thomas Kwashnak
 * @version 1.0.0-tableview
 * @since 1.0.0-tableview
 */
public class TeamTable extends TableView<Team> {
    private final Pace pace;

    public TeamTable(Pace pace) {
        super();
        this.pace = pace;
    }
}
