package org.tealeaf.pacemanager.app.stages;

import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.app.components.MenuApp;
import org.tealeaf.pacemanager.app.components.TabTeams;

public class AppLayout extends BorderPane implements App.OnClose {

    private final App app;

    public AppLayout(App app) {
        this.app = app;
        app.addListener(this);
        Identity.APP_LAYOUT.set(this);
        setMinWidth(700);
        setMinHeight(700);

        setTop(new MenuApp(app));
        setCenter(new TabPane() {{

            Identity.APP_CONTENT.set(this);

            getTabs().addAll(new TabTeams(app));

            //Universal Settings
            getTabs().parallelStream().forEach(tab -> {
                tab.setClosable(false);
            });
        }});
    }

    @Override
    public void onAppClose() {
        app.removeListener(this);
    }
}
