package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import org.tealeaf.pacemanager.util.Identifiable;

public enum Identifier implements Identifiable {
    LAYOUT_ABOUT,
    LAYOUT_APP,
    LAYOUT_CONTENT_APP,
    LAYOUT_CONTENT_OPEN_PACE,
    LAYOUT_CONTENT_OPEN_PACE_BUTTON,
    MENU,
    MENU_FILE,
    MENU_FILE_CLOSE,
    MENU_FILE_EXIT,
    MENU_FILE_NEW,
    MENU_FILE_OPEN,
    MENU_FILE_SAVE,
    MENU_FILE_SAVE_AS,
    MENU_HELP,
    MENU_HELP_ABOUT,
    TAB_DIVISIONS,
    TAB_TEAMS,
    TAB_TEAMS_CONTENT,
    TAB_TEAMS_CONTENT_TABLE,
    TEST_A,
    TEST_B,
    TEST_C,
    TEST_D,
    TAB_STANDINGS;

    private static int dynamic = 0;

    public static String getDynamicId() {
        return "#Dynamic%d".formatted(dynamic++);
    }

    public void set(Node node) {
        node.setId(toString());
    }

    public void set(Tab tab) {
        tab.setId(toString());
    }

    @Override
    public String toString() {

        return "#%s".formatted(ordinal());
    }

    public void set(MenuItem menuItem) {
        menuItem.setId(toString());
    }

    @Override
    public String getID() {
        return toString();
    }
}
