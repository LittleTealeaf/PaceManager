package org.tealeaf.pacemanager.app.layouts;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.app.util.StageHolder;

import java.io.File;

public class ManualFileEntry extends BorderPane implements StageHolder {

    private App app;
    private Stage stage;

    public ManualFileEntry(App app) {
        this.app = app;

        final TextField textField =new TextField() {{
            Identifier.LAYOUT_MANUAL_FILE_ENTRY_TEXT.set(this);
        }} ;

        setCenter(textField);
        setRight(new Button() {{
            setText("Open File");
            Identifier.LAYOUT_MANUAL_FILE_ENTRY_BUTTON.set(this);
            setOnAction(e -> {
                app.getPaceHandler().openFile(new File(textField.getText()));
                stage.close();
            });
        }});
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
