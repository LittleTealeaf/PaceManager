package org.tealeaf.pacemanager.app.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.dialogs.EditTeamDialog;
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

        getColumns().add(new TableColumn<Team, String>("ID") {{
            setCellValueFactory(param -> param.getValue().nameProperty());
        }});
        getColumns().add(new TableColumn<Team, String>("Riders") {{
            setCellValueFactory(param -> param.getValue().getPrintRidersProperty());
        }});
        getColumns().add(new TableColumn<Team, String>("Start Time") {{
            setCellValueFactory(param -> param.getValue().startTimeProperty().asString());
        }});
        getColumns().add(new TableColumn<Team, String>("End Time") {{
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
