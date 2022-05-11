package org.tealeaf.pacemanager.app.components;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.dialogs.EditTeamDialog;
import org.tealeaf.pacemanager.app.wrappers.IdentifiableTableCell;
import org.tealeaf.pacemanager.data.Team;

public class TableTeams extends TableView<Team> {

    private final App app;

    public TableTeams(App app) {
        this.app = app;
        Identity.APP_TAB_TEAMS_TABLE.set(this);
        app.addListener(this);

        setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                openTeam();
            }
        });
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                openTeam();
            }
        });

        setItems(app.getProject().getPace().getTeams());

        getColumns().add(new TableColumn<Team, String>("Team Name") {{
            setCellFactory(params -> new IdentifiableTableCell.IdentifiableStringCell<>(Identity.TABLE_TEAMS_CELL_TEAM_NAME));
            setCellValueFactory(param -> param.getValue().nameProperty());
        }});

        getColumns().add(new TableColumn<Team,String>("Riders") {{
            setCellFactory(params -> new IdentifiableTableCell.IdentifiableStringCell<>(Identity.TABLE_TEAMS_CELL_RIDERS));
            setCellValueFactory(param -> param.getValue().getPrintRidersProperty());
        }});

        getColumns().add(new TableColumn<Team,String>("Start Time") {{
            setCellFactory(params -> new IdentifiableTableCell.IdentifiableStringCell<>(Identity.TABLE_TEAMS_CELL_START_TIME));
            setCellValueFactory(param -> param.getValue().startTimeProperty().asString());
        }});

        getColumns().add(new TableColumn<Team,String>("End Time") {{
            setCellFactory(params -> new IdentifiableTableCell.IdentifiableStringCell<>(Identity.TABLE_TEAMS_CELL_END_TIME));
            setCellValueFactory(param -> param.getValue().endTimeProperty().asString());
        }});
    }



    private void openTeam() {
        int index = getSelectionModel().getSelectedIndex();
        try {
            Team team = app.getProject().getPace().getTeams().get(index);
            new EditTeamDialog(app, team);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
