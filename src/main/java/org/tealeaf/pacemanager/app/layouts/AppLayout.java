package org.tealeaf.pacemanager.app.layouts;

import javafx.scene.layout.BorderPane;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.components.AppMenu;

public class AppLayout extends BorderPane implements App.OnClose {

    private App app;

    public AppLayout(App app) {
        this.app = app;
        app.addListener(this);
        Identity.APP_LAYOUT.set(this);
        setMinWidth(500);
        setMinHeight(500);

        setTop(new AppMenu(app));
    }

    @Override
    public void onAppClose() {
        app.removeListener(this);
    }
}
