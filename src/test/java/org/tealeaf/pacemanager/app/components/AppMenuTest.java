package org.tealeaf.pacemanager.app.components;

import org.junit.jupiter.api.BeforeEach;
import test.org.tealeaf.pacemanager.UserInterfaceTest;
import org.tealeaf.pacemanager.app.App;
import org.tealeaf.pacemanager.app.Identifier;

class AppMenuTest extends UserInterfaceTest {

    private App app;
    @BeforeEach
    void getApp() {
        app = get(App.class, lookup(Identifier.APP));
    }

//    @Test
//    void testHelpAbout() {
//        clickOn(Identifier.MENU_HELP);
//        clickOn(Identifier.MENU_HELP_ABOUT);
//        assertTrue(exists(Identifier.WINDOW_ABOUT));
//    }

}