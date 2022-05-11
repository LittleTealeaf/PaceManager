package org.tealeaf.pacemanager.app.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identity;
import test.ApplicationWrapper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuAppTest extends ApplicationWrapper {

    @BeforeEach
    void openNewPace() {
        actionCreateNewPace();
    }

    @Test
    void testFileNew() {
        clickOn(Identity.APP_MENU_FILE, Identity.APP_MENU_FILE_NEW);
        assertTrue(exists(Identity.DIALOG_CREATE_PACE));
    }

    @Test
    void testFileClose() {
        clickOn(Identity.APP_MENU_FILE, Identity.APP_MENU_FILE_CLOSE_PROJECT);
        assertFalse(exists(Identity.APP_LAYOUT));
        assertTrue(exists(Identity.LAUNCHER_LAYOUT));
    }

    @Test
    void testFileExit() {
        clickOn(Identity.APP_MENU_FILE, Identity.APP_MENU_FILE_EXIT);
        assertFalse(exists(Identity.APP_LAYOUT));
        assertFalse(exists(Identity.LAUNCHER_LAYOUT));
    }
}