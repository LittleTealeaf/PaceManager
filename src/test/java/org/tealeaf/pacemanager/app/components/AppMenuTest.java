package org.tealeaf.pacemanager.app.components;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.UserInterfaceTest;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identifier;

import static org.junit.jupiter.api.Assertions.*;

class AppMenuTest extends UserInterfaceTest {

    private App app;
    @BeforeEach
    void getApp() {
        app = get(App.class, lookup(Identifier.APP));
    }
    @Test
    void testFileExit() {
        clickOn(Identifier.MENU_FILE);
        clickOn(Identifier.MENU_FILE_EXIT);
        assertFalse(exists(Identifier.APP));
    }

    @Test
    void testHelpAbout() {
        clickOn(Identifier.MENU_HELP);
        clickOn(Identifier.MENU_HELP_ABOUT);
        assertTrue(exists(Identifier.WINDOW_ABOUT));
    }

}