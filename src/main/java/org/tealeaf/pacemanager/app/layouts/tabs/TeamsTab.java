package org.tealeaf.pacemanager.app.layouts.tabs;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.database.dataobjects.Team;
import org.tealeaf.pacemanager.util.Closable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamsTab extends Tab implements Closable {

    private App app;

    public TeamsTab(App app) {
        this.app = app;
        app.addListener(this);
        Identifier.TAB_TEAMS.set(this);

        setText("Teams");
        setClosable(false);
        setContent(new TeamsContent(app));

    }

    @Override
    public void close() {
        app.removeListener(this);
    }

    public static class TeamsContent extends BorderPane {

        protected TeamsContent(App app) {
            Identifier.TAB_TEAMS_CONTENT.set(this);
            app.addListener(this);
            setCenter(new TeamsContentTable(app));

            setTop(new HBox() {{

            }});
        }

        public static class TeamsContentTable extends TableView<Team> implements Pace.OnTeamAdded, Pace.OnTeamRemoved {

            private ObservableList<Team> teams;
            protected TeamsContentTable(App app) {
                Identifier.TAB_TEAMS_CONTENT_TABLE.set(this);
                app.addListener(this);
                teams = FXCollections.observableList(app.getPaceHandler().getPace().getTeams());
                setItems(teams);

                getColumns().addAll(new TableColumn<Team,String>() {{
                    setText("Team Number");
                    setCellValueFactory(new Callback<CellDataFeatures<Team, String>, ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(CellDataFeatures<Team, String> param) {
                            return new SimpleStringProperty(param.getValue().getTeamNumber());
                        }
                    });
                }}, new TableColumn<Team,String>() {{
                    setText("Riders");
                    setCellValueFactory((param) -> new SimpleStringProperty(String.join(", ", param.getValue().getRiders())));
                }});
            }

            @Override
            public void onTeamAdded(Team team) {
                teams.add(team);
            }

            @Override
            public void onTeamRemoved(Team team) {
                teams.remove(team);
            }
        }
    }
}
