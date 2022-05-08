package org.tealeaf.pacemanager.app.layouts.tabs;

import javafx.scene.control.Tab;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.util.Closable;

public class StandingsTab extends Tab implements Closable {

    private App app;

    public StandingsTab(App app) {
        this.app = app;
        setText("Standings");
        app.addListener(this);
        Identifier.TAB_STANDINGS.set(this);

    }

    @Override
    public void close() {
        app.removeListener(this);
    }
}
