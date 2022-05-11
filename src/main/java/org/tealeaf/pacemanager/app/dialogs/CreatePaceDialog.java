package org.tealeaf.pacemanager.app.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.api.Context;
import org.tealeaf.pacemanager.app.listeners.OpenProjectListener;
import org.tealeaf.pacemanager.constants.ProjectNameRestrictions;
import org.tealeaf.pacemanager.data.Pace;
import org.tealeaf.pacemanager.data.Project;
import org.tealeaf.pacemanager.events.EventCoordinator;

import java.util.function.UnaryOperator;

public class CreatePaceDialog extends Stage implements OpenProjectListener {

    public CreatePaceDialog(Context context) {
        this(context,false);
    }

    public CreatePaceDialog(Context context, boolean quickAdd) {

        context.addListener(this);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Create New Pace");

        setMinWidth(400);

        setOnCloseRequest(event -> context.removeListener(this));

        setScene(new Scene(new BorderPane() {{
            Identity.DIALOG_CREATE_PACE.set(this);
            setPadding(new Insets(2));
            setOnKeyPressed(event -> {
                if( event.getCode() == KeyCode.ESCAPE) {
                    CreatePaceDialog.this.close();
                }
            });

            final TextField nameField = new TextField() {{
                Identity.DIALOG_CREATE_PACE_FIELD_NAME.set(this);
                setPromptText("Name");

                setTextFormatter(new TextFormatter<>(change -> {
                    if (!change.isContentChange()) {
                        return change;
                    }
                    String text = change.getControlNewText();
                    if(text.length() > ProjectNameRestrictions.MAX_LENGTH) {
                        return null;
                    }
                    for (char c : ProjectNameRestrictions.ILLEGAL_CHARACTERS.toCharArray()) {
                        if (text.contains(Character.toString(c))) {
                            return null;
                        }
                    }
                    return change;
                }));

            }};

            setCenter(new GridPane() {{
                Identity.DIALOG_CREATE_PACE_GRIDPANE.set(this);
                setPadding(new Insets(5));

                setHgap(5);
                setVgap(5);

                setHgrow(nameField, Priority.ALWAYS);

                add(new Label("Project Name"),0,1);
                add(nameField,1,1);

            }});

            setBottom(new HBox() {{

                getChildren().addAll(new Button() {{
                    Identity.DIALOG_CREATE_PACE_BUTTON_CANCEL.set(this);
                    setText("Cancel");
                    setOnAction(event -> close());
                }},new Button() {{
                    Identity.DIALOG_CREATE_PACE_BUTTON_CREATE.set(this);
                    setText("Create");

                    setOnAction(event -> {

                        Project project = new Project(new Pace(nameField.getText()));

                        context.run(OpenProjectListener.class, l -> l.openProject(project));
                        context.removeListener(this);
                    });
                }});
                setAlignment(Pos.CENTER_RIGHT);
                getChildren().forEach(child -> setMargin(child,new Insets(2)));
            }});


        }}));

        show();
        if(quickAdd) {
            context.run(OpenProjectListener.class,l -> l.openProject(new Project(new Pace("New Pace"))));
        }
    }



    @Override
    public void openProject(Project project) {
        close();
    }
}
