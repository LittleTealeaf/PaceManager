package org.tealeaf.pacemanager.app.windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.tealeaf.pacemanager.app.App;

import static org.tealeaf.pacemanager.app.Identifier.WINDOW_ABOUT;

public class About extends BorderPane {
    public About(App app) {
        WINDOW_ABOUT.set(this);
        setPadding(new Insets(10));
        setCenter(new Text() {{
            setText("Hi, this is the about page");
        }});
    }

    public static Scene build(App app) {
        return new Scene(new About(app));
    }
}
