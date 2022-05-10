package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public enum Identity {
    APP,
    LAUNCHER_LAYOUT,
    LAUNCHER_HBOX_TOP,
    LAUNCHER_BUTTON_NEW,
    DIALOG_CREATE_PACE,
    DIALOG_CREATE_PACE_BUTTON_CREATE,
    DIALOG_CREATE_PACE_GRIDPANE,
    DIALOG_CREATE_PACE_FIELD_NAME,
    APP_LAYOUT,
    DIALOG_CREATE_PACE_BUTTON_CANCEL,
    APP_MENU_FILE,
    APP_MENU_FILE_NEW,
    APP_MENU_FILE_OPEN,
    APP_MENU_FILE_RECENT,
    APP_MENU_FILE_CLOSE_PROJECT;

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
