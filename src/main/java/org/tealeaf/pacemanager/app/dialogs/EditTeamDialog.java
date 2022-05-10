package org.tealeaf.pacemanager.app.dialogs;

import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.constants.ProjectNameRestrictions;
import org.tealeaf.pacemanager.data.EventTime;
import org.tealeaf.pacemanager.data.Team;
import org.tealeaf.pacemanager.events.EventCoordinator;

import java.util.LinkedList;
import java.util.List;

public class EditTeamDialog extends Stage {

    /*
    TODO refactor others to do the same as this
     */

    private final EventCoordinator eventCoordinator;
    private final Team team;
    private final boolean isNew;

    public EditTeamDialog(EventCoordinator eventCoordinator) {
        this(eventCoordinator, null);
    }

    public EditTeamDialog(EventCoordinator eventCoordinator, Team inputTeam) {
        this.eventCoordinator = eventCoordinator;
        isNew = inputTeam == null;
        team = isNew ? new Team() : inputTeam.clone();


        initModality(Modality.APPLICATION_MODAL);
        setTitle(isNew ? "New Team" : "Edit Team");

        setScene(buildScene());
        sizeToScene();

        show();
    }

    protected Scene buildScene() {
        return new Scene(buildLayout());
    }

    protected Parent buildLayout() {
        return new BorderPane() {{
            Identity.DIALOG_EDIT_TEAM.set(this);

            setLeft(buildLayoutLeft());
            setBottom(buildLayoutBottom());
            setRight(buildLayoutRight());
        }};
    }

    protected Node buildLayoutLeft() {
        return new GridPane() {{



            add(new Label("Team Name"),0,0);
            add(new TextField() {{
                Identity.DIALOG_EDIT_TEAM_INPUT_NAME.set(this);
                setText(team.getName());
                team.nameProperty().bind(textProperty());
                setPromptText("Team Name");
            }},1,0);
            add(new Label("Riders"),0,1);
            add(new RidersNode(),1,1);
        }};
    }



    protected Node buildLayoutRight() {
        return new GridPane() {{

            add(new Label("Start Time"),0,0);
            add(new TextField() {{
                Identity.DIALOG_EDIT_TEAM_INPUT_TIME_START.set(this);
                setText(team.getStartTime() == null ? "" : team.getStartTime().toString());
                textProperty().addListener((e,o,n) -> team.setStartTime(new EventTime(Long.parseLong(n))));
                setTextFormatter(new TextFormatter<>(change -> {
                    if (!change.isContentChange()) {
                        return change;
                    }
                    String text = change.getControlNewText();
                    try {
                        Long.parseLong(text);
                    } catch(Exception e) {
                        return null;
                    }
                    return change;
                }));
                setPromptText("Time");
            }},1,0);
            add(new Label("End Time"),0,1);
            add(new TextField() {{
                Identity.DIALOG_EDIT_TEAM_INPUT_TIME_END.set(this);
                setPromptText("Time");
                setText(team.getEndTime() == null ? "" : team.getEndTime().toString());
                textProperty().addListener((e,o,n) -> team.setEndTime(n.equals("") ? null : new EventTime(Long.parseLong(n))));
                setTextFormatter(new TextFormatter<>(change -> {
                    if (!change.isContentChange()) {
                        return change;
                    }
                    String text = change.getControlNewText();
                    try {
                        Long.parseLong(text);
                    } catch(Exception e) {
                        return null;
                    }
                    return change;
                }));


            }},1,1);
        }};
    }



    protected Node buildLayoutBottom() {
        return new HBox(buildSaveButton()) {{
            setSpacing(10);
            setAlignment(Pos.CENTER_RIGHT);
        }};
    }

    protected Node buildSaveButton() {
        return new Button(isNew ? "Create" : "Save") {{
            Identity.DIALOG_EDIT_TEAM_BUTTON_SAVE.set(this);
            setOnAction(event -> {
                eventCoordinator.run(OnSaveTeam.class,i -> i.onSaveTeam(team));
                close();
            });
        }};
    }

    protected class RidersNode extends GridPane implements ListChangeListener<String> {

        int size = 0;
        private final List<Button> buttons = new LinkedList<>();
        private final List<TextField> textFields = new LinkedList<>();

        private boolean updating = false;
        Button addButton = new Button() {{setText("Add");
           Identity.DIALOG_EDIT_TEAM_BUTTON_ADD_RIDER.set(this);
           setOnAction(event -> team.getRiders().add(""));
        }};

        RidersNode() {

            team.getRiders().addListener(this);

            updateRows();
        }

        void updateRows() {
            while(size < team.getRiders().size()) {
                final int i = size;
                Button button = new Button("Remove") {{
                    Identity.DIALOG_EDIT_TEAM_BUTTON_REMOVE_RIDER.set(this, size);
                    setOnAction(event -> team.getRiders().remove(i));
                }};
                TextField textField = new TextField() {{
                    Identity.DIALOG_EDIT_TEAM_INPUT_RIDER_NAME.set(this, size);
                    textProperty().addListener((e,o,n) -> {
                        if(!updating) {
                            team.getRiders().set(i, n);
                        }
                    });
                }};

                buttons.add(button);
                textFields.add(textField);

                add(textField,1,size);
                add(button,0,size);

                size++;
            }
            while(size > team.getRiders().size()) {
                size--;
                TextField textField = textFields.remove(size);
                getChildren().remove(buttons.remove(size));
                getChildren().remove(textField);
            }
            updating = true;
            for(int i = 0; i < size; i++) {
                textFields.get(i).setText(team.getRiders().get(i));
            }
            updating = false;
            sizeToScene();
            updateAddButton();
        }


        @Override
        public void onChanged(Change<? extends String> c) {
            updateRows();
        }

        void updateAddButton() {
            getChildren().remove(addButton);
            add(addButton,2,Math.max(0,team.getRiders().size() - 1));
        }

    }

    public interface OnSaveTeam {

        void onSaveTeam(Team team);
    }
}
