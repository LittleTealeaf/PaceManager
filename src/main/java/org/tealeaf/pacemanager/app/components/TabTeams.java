package org.tealeaf.pacemanager.app.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.dialogs.EditTeamDialog;

public class TabTeams extends Tab {

    private App app;

    public TabTeams(App app) {
        this.app = app;
        app.addListener(this);
        setText("Teams");
        Identity.APP_TAB_TEAMS.set(this);

        setContent(new BorderPane() {{
            Identity.APP_TAB_TEAMS_CONTENT.set(this);

            setCenter(new TableTeams(app));

            setTop(new HBox(
                    new Button("Add Team") {{
                        Identity.APP_TAB_TEAMS_BUTTON_ADD_TEAM.set(this);
                        setOnAction(event -> new EditTeamDialog(app));
                    }}

            ) {{
                setSpacing(10);
                setPadding(new Insets(0,5,0,5));
            }});

        }});
    }
}
