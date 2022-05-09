package org.tealeaf.pacemanager.app.layouts;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.Launcher;
import org.tealeaf.pacemanager.app.dialogs.CreatePaceDialog;

public class LauncherLayout extends BorderPane {

    public LauncherLayout(Launcher launcher) {
        Identity.LAUNCHER_LAYOUT.set(this);
        setPadding(new Insets(5));
        setTop(new HBox() {{
            Identity.LAUNCHER_HBOX_TOP.set(this);
            getChildren().add(new Button() {{
                Identity.LAUNCHER_BUTTON_NEW.set(this);
                setText("New");
                setOnAction(event -> new CreatePaceDialog(launcher));
            }});
        }});
    }
}
