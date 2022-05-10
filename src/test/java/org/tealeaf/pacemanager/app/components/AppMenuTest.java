package org.tealeaf.pacemanager.app.components;

import com.sun.webkit.dom.CSSRuleListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identity;
import test.ApplicationWrapper;

import static org.junit.jupiter.api.Assertions.*;

class AppMenuTest extends ApplicationWrapper {

    @BeforeEach
    void openNewPace() {
        actionCreateNewPace();
    }

    @Test
    void testFile() {
        clickOn(Identity.APP_MENU_FILE);
        assertTrue(exists(Identity.APP_MENU_FILE_NEW));
    }

    @Test
    void testFileNew() {
        clickOn(Identity.APP_MENU_FILE,Identity.APP_MENU_FILE_NEW);
        assertTrue(exists(Identity.DIALOG_CREATE_PACE));
    }

    @Test
    void testFileClose() {
        clickOn(Identity.APP_MENU_FILE,Identity.APP_MENU_FILE_CLOSE_PROJECT);
        assertFalse(exists(Identity.APP_LAYOUT));
        assertTrue(exists(Identity.LAUNCHER_LAYOUT));
    }

}