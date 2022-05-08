package org.tealeaf.pacemanager.app.layouts.content;

import javafx.scene.control.TabPane;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.app.layouts.tabs.DivisionsTab;
import org.tealeaf.pacemanager.app.layouts.tabs.StandingsTab;
import org.tealeaf.pacemanager.app.layouts.tabs.TeamsTab;
import org.tealeaf.pacemanager.util.Closable;

public class AppContent extends TabPane implements Closable {

    public AppContent(App app) {

        Identifier.LAYOUT_CONTENT_APP.set(this);

        getTabs().addAll(new TeamsTab(app), new DivisionsTab(app), new StandingsTab(app));
        getTabs().parallelStream().forEach(tab -> tab.setClosable(false));
    }

    @Override
    public void close() {
        getTabs().parallelStream().filter(Closable.class::isInstance).map(Closable.class::cast).forEach(Closable::close);
    }
}
