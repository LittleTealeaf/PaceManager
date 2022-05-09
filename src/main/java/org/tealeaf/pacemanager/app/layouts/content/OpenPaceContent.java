package org.tealeaf.pacemanager.app.layouts.content;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.app.layouts.ManualFileEntry;
import org.tealeaf.pacemanager.util.Closable;

import java.io.File;
import java.security.Key;

public class OpenPaceContent extends BorderPane implements Closable {

    private App app;

    public OpenPaceContent(App app) {
        this.app = app;
        Identifier.LAYOUT_CONTENT_OPEN_PACE.set(this);
        app.addListener(this);
        setLeft(new VBox(new Button() {
            boolean controlPressed = false;
            {
            Identifier.LAYOUT_CONTENT_OPEN_PACE_BUTTON.set(this);
            setText("Open");

            setOnKeyPressed(e -> {
                if( e.getCode() == KeyCode.SHIFT) {
                    controlPressed = true;
                }
            });
            setOnKeyReleased(e -> {
                if(e.getCode() == KeyCode.SHIFT) {
                    controlPressed = false;
                }
            });

            setOnMouseClicked(e -> {
                if(!e.isAltDown()) {
                    FileChooser fc = new FileChooser();


                    File file = fc.showOpenDialog(app.getScene().getWindow());
                    if(file != null) {
                        app.getPaceHandler().openFile(file);
                    }
                } else {
                    app.launchWindow(new ManualFileEntry(app));
                }
            });

        }}));
    }

    @Override
    public void close() {
        app.removeListener(this);
    }
}
