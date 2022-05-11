package org.tealeaf.pacemanager.app.dialogs;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.Context;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.listeners.OpenProjectListener;
import org.tealeaf.pacemanager.data.Pace;
import org.tealeaf.pacemanager.data.Project;
import org.tealeaf.pacemanager.util.TextFormatters;

public class CreatePaceDialog extends Stage implements OpenProjectListener {

    private final Context context;
    private final Project project = new Project(new Pace());

    public CreatePaceDialog(Context context) {
        this(context, false);
    }

    public CreatePaceDialog(Context context, boolean quickAdd) {
        this.context = context;
        context.addListener(this);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Create New Pace");
        setMinWidth(400);
        setOnCloseRequest(event -> this.context.removeListener(this));
        setScene(new Scene(buildLayout()));
        show();
        if (quickAdd) {
            context.run(OpenProjectListener.class, l -> l.openProject(project));
        }
    }

    protected Parent buildLayout() {
        return new BorderPane() {{
            Identity.DIALOG_CREATE_PACE.set(this);
            setPadding(new Insets(2));

            setCenter(buildLayoutCenter());
            setBottom(buildLayoutBottom());
        }};
    }

    protected Node buildLayoutCenter() {

        final TextField nameField = buildNameField();

        return new GridPane() {{
            Identity.DIALOG_CREATE_PACE_GRIDPANE.set(this);
            setPadding(new Insets(5));
            setHgrow(nameField, Priority.ALWAYS);
            add(new Label("Project Name"), 0, 1);
            add(nameField, 1, 1);
        }};
    }

    protected Node buildLayoutBottom() {
        return new HBox(buildButtonCancel(), buildButtonCreate()) {{
            setAlignment(Pos.CENTER_RIGHT);
            getChildren().forEach(child -> setMargin(child, new Insets(2)));
        }};
    }

    protected TextField buildNameField() {
        return new TextField() {{
            Identity.DIALOG_CREATE_PACE_FIELD_NAME.set(this);
            setPromptText("Name");
            setTextFormatter(TextFormatters.projectNameFormatter());
            textProperty().addListener((e, o, n) -> project.setName(n));
        }};
    }

    protected Node buildButtonCancel() {
        return new Button("Cancel") {{
            Identity.DIALOG_CREATE_PACE_BUTTON_CANCEL.set(this);
            setOnAction(event -> close());
        }};
    }

    protected Node buildButtonCreate() {
        return new Button("Create") {{
            Identity.DIALOG_CREATE_PACE_BUTTON_CREATE.set(this);
            setOnAction(CreatePaceDialog.this::eventCreate);
        }};
    }

    protected void eventCreate(ActionEvent event) {
        context.run(OpenProjectListener.class, l -> l.openProject(project));
        context.removeListener(this);
    }

    @Override
    public void openProject(Project project) {
        close();
    }
}
