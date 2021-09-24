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

        TableColumn<Team, String> colDivision = new TableColumn<>("Division");
        colDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
        getColumns().add(colDivision);

        TableColumn<Team, String> colIdentifier = new TableColumn<>("Identifier");


        TableColumn<Team, String> colRiders = new TableColumn<>("Riders");


    }

    public void updateRiders() {
        getItems().clear();
        getItems().addAll(pace.getTeams());
    }
}
