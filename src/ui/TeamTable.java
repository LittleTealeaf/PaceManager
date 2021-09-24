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
        update();

        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                if (getSelectionModel().getSelectedItem() != null) {
                    new TeamEditor(getSelectionModel().getSelectedItem());
                }
            }
        });
        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER, SPACE -> {
                    if (getSelectionModel().getSelectedItem() != null) {
                        new TeamEditor(getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
    }

    private void addColumns() {
        getColumns().clear();

        TableColumn<Team, String> times = new TableColumn<>("Times");
        times.setReorderable(false);
        times.getColumns().addAll(
                columnFactory("Start", "startString"),
                columnFactory("End", "endString"),
                columnFactory("Elapsed", "elapsedString")
        );

        getColumns().addAll(
                columnFactory("Division", "division"),
                columnFactory("Team", "teamNumber"),
                columnFactory("Riders", "ridersString"),
                times,
                columnFactory("Notes", "notes")
        );

    }

    private TableColumn<Team, String> columnFactory(String name, String propertyName) {
        TableColumn<Team, String> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        column.setReorderable(false);
        return column;
    }

    public void update() {
        int selectedIndex = getSelectionModel().getSelectedIndex();

        getItems().clear();
        getItems().addAll(pace.getTeams());

        if (selectedIndex >= 0) {
            getSelectionModel().select(selectedIndex);
        }
    }
}
