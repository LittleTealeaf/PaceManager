package ui;

import app.App;
import data.Pace;
import data.Team;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Thomas Kwashnak
 * @version 1.0.0-tableview
 * @since 1.0.0-tableview
 */
public class TeamTable extends TableView<Team> {
    private final Pace pace;

    public TeamTable() {
        this(App.openedPace);
    }

    public TeamTable(Pace pace) {
        super();
        this.pace = pace;
        addColumns();
        updateRiders();
    }

    private void addColumns() {
        getColumns().clear();

        getColumns().addAll(
                columnFactory("Division", "division"),
                columnFactory("Team", "teamNumber"),
                columnFactory("Riders", "ridersString")
        );

    }

    private TableColumn<Team, String> columnFactory(String name, String propertyName) {
        TableColumn<Team, String> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    public void updateRiders() {
        getItems().clear();
        getItems().addAll(pace.getTeams());
    }
}
