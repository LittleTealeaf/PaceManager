package ui;

import app.App;
import data.Team;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

/**
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class TeamTable extends TableView<Team> {
    //TODO BUG clicking on a selected item and then clicking on empty space opens the selected item

    public TeamTable() {
        super();
        addColumns();
        update();

        setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
                if (getSelectionModel().getSelectedIndex() > -1) {
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
                case F5 -> update();
            }
        });
        setContextMenu(createContextMenu());
    }

    private ContextMenu createContextMenu() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem openItem = new MenuItem("Open");
        openItem.setOnAction(e -> new TeamEditor(getSelectionModel().getSelectedItem()));

        MenuItem newItem = new MenuItem("New");
        newItem.setOnAction(e -> new TeamEditor(App.openedPace.newTeam()));

        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(e -> App.openedPace.deleteTeam(getSelectionModel().getSelectedItem()));

        contextMenu.getItems().addAll(openItem, newItem, deleteItem);
        return contextMenu;
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
                columnFactory("Notes", "notesDisplay")
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
        getItems().addAll(App.openedPace.getTeams());

        if (selectedIndex >= 0) {
            getSelectionModel().select(selectedIndex);
        }
    }
}
