package org.tealeaf.pacemanager.app.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.org.tealeaf.pacemanager.UserInterfaceTest;
import org.tealeaf.pacemanager.app.layouts.App;
import org.tealeaf.pacemanager.app.Identifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppMenuTest extends UserInterfaceTest {

    private App app;
    @BeforeEach
    void getApp() {
        app = get(App.class, lookup(Identifier.LAYOUT_APP));
    }

    @Test
    void testFile() {
        clickOn(Identifier.MENU_FILE);
        assertTrue(exists(Identifier.MENU_FILE_NEW));
        assertTrue(exists(Identifier.MENU_FILE_OPEN));
        assertTrue(exists(Identifier.MENU_FILE_SAVE));
        assertTrue(exists(Identifier.MENU_FILE_SAVE_AS));
    }

    @Test
    void testFileNew() {
        clickSequence(Identifier.MENU_FILE,Identifier.MENU_FILE_NEW);
        assertTrue(exists(Identifier.LAYOUT_CONTENT_APP));
    }

}