package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

public enum Identity {
    APP_CONTENT,
    APP_LAYOUT,
    APP_MENU_FILE,
    APP_MENU_FILE_CLOSE_PROJECT,
    APP_MENU_FILE_EXIT,
    APP_MENU_FILE_NEW,
    APP_MENU_FILE_OPEN,
    APP_TAB_TEAMS,
    APP_TAB_TEAMS_BUTTON_ADD_TEAM,
    APP_TAB_TEAMS_CONTENT,
    APP_TAB_TEAMS_TABLE,
    DIALOG_CREATE_PACE,
    DIALOG_CREATE_PACE_BUTTON_CANCEL,
    DIALOG_CREATE_PACE_BUTTON_CREATE,
    DIALOG_CREATE_PACE_FIELD_NAME,
    DIALOG_CREATE_PACE_GRIDPANE,
    DIALOG_EDIT_TEAM,
    DIALOG_EDIT_TEAM_BUTTON_ADD_RIDER,
    DIALOG_EDIT_TEAM_BUTTON_REMOVE_RIDER,
    DIALOG_EDIT_TEAM_BUTTON_SAVE,
    DIALOG_EDIT_TEAM_GRIDPANE,
    DIALOG_EDIT_TEAM_INPUT_NAME,
    DIALOG_EDIT_TEAM_INPUT_RIDER_NAME,
    LAUNCHER_BUTTON_NEW,
    LAUNCHER_HBOX_TOP,
    LAUNCHER_LAYOUT,
    DIALOG_EDIT_TEAM_INPUT_TIME_START,
    DIALOG_EDIT_TEAM_INPUT_TIME_END,
    TABLE_TEAMS_CELL_TEAM_NAME,
    TABLE_TEAMS_CELL_RIDERS,
    TABLE_TEAMS_CELL_START_TIME,
    TABLE_TEAMS_CELL_END_TIME;

    public void set(Node node, int i) {
        set(node::setId, i);
    }

    public void set(Holder object, int i) {
        object.setId(toID(i));
    }

    public String toID(int i) {
        return "%s-%s".formatted(toID(), convert(i));
    }

    public String toID() {
        return convert(ordinal());
    }

    private static String convert(int id) {
        return Integer.toString(id, 36);
    }

    public void set(MenuItem menuItem, int i) {
        set(menuItem::setId, i);
    }

    public void set(TableColumn<?, ?> column, int i) {
        set(column::setId, i);
    }

    public void set(Tab tab, int i) {
        set(tab::setId, i);
    }

    public void set(Node node) {
        set(node::setId);
    }

    public void set(Holder object) {
        object.setId(toID());
    }

    public void set(MenuItem item) {
        set(item::setId);
    }

    public void set(TableColumn<?, ?> column) {
        set(column::setId);
    }

    public void set(Tab tab) {
        set(tab::setId);
    }

    public interface Holder {

        void setId(String id);
    }
}
