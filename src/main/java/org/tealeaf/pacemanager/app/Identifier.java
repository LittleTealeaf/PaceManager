package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import org.tealeaf.pacemanager.util.Identifiable;

public enum Identifier implements Identifiable {
    APP,
    WINDOW_ABOUT,
    MENU,
    MENU_FILE,
    MENU_FILE_EXIT,
    MENU_FILE_OPEN,
    MENU_FILE_SAVE,
    MENU_FILE_SAVE_AS,
    MENU_HELP,
    MENU_HELP_ABOUT;

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

    @Override
    public String getID() {
        return toString();
    }
}
