package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public enum Identity {
    APP,
    APP_LAYOUT,
    APP_MENU_FILE,
    APP_MENU_FILE_CLOSE_PROJECT,
    APP_MENU_FILE_NEW,
    APP_MENU_FILE_OPEN,
    APP_MENU_FILE_RECENT,
    DIALOG_CREATE_PACE,
    DIALOG_CREATE_PACE_BUTTON_CANCEL,
    DIALOG_CREATE_PACE_BUTTON_CREATE,
    DIALOG_CREATE_PACE_FIELD_NAME,
    DIALOG_CREATE_PACE_GRIDPANE,
    LAUNCHER_BUTTON_NEW,
    LAUNCHER_HBOX_TOP,
    LAUNCHER_LAYOUT,
    APP_MENU_FILE_EXIT;

    public String toID() {
        return "#%d".formatted(ordinal());
    }

    public void set(Node node) {
        node.setId(toID());
    }

    public void set(MenuItem menuItem) {
        menuItem.setId(toID());
    }

    public void set(Tab tab) {
        tab.setId(toID());
    }
}
