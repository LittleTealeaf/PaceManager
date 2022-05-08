package org.tealeaf.pacemanager.app.layouts;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import static org.tealeaf.pacemanager.app.Identifier.LAYOUT_ABOUT;

public class About extends BorderPane {
    public About(App app) {
        LAYOUT_ABOUT.set(this);
        setPadding(new Insets(10));
        setCenter(new Text() {{
            setText("Hi, this is the about page");
        }});
    }

    public static Scene build(App app) {
        return new Scene(new About(app));
    }
}
