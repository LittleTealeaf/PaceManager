package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;

public enum Identifier {
    APPLICATION,
    MENU,
    MENU_FILE,
    MENU_FILE_EXIT,
    MENU_FILE_OPEN;

    @Override
    public String toString() {
        return "#%s".formatted(ordinal());
    }

    public void set(Node node) {
        node.setId(toString());
    }

    public void set(MenuItem menuItem) {
        menuItem.setId(toString());
    }
}
