package org.tealeaf.pacemanager.app.layouts.tabs;

import javafx.scene.control.Tab;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.util.Closable;

public class DivisionsTab extends Tab implements Closable {

    private App app;
    public DivisionsTab(App app) {
        this.app = app;
        app.addListener(this);
        Identifier.TAB_DIVISIONS.set(this);
        setText("Divisions");
        setClosable(false);
    }

    @Override
    public void close() {
        app.removeListener(this);
    }
}
