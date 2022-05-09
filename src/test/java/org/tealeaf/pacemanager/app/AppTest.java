package org.tealeaf.pacemanager.app;

import org.junit.jupiter.api.Test;
import test.ApplicationWrapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest extends ApplicationWrapper {

    @Test
    void testCorrectTitle() {
        String name = randomName();
        actionCreateNewPace(name);
        assertTrue(getApp().getTitle().contains(name));
    }

    @Test
    void testOpenNewPace() {
        actionCreateNewPace();
        clickOn(Identity.APP_MENU_FILE, Identity.APP_MENU_FILE_NEW);
        String text = randomName();
        writeInto(Identity.DIALOG_CREATE_PACE_FIELD_NAME, text);
        clickOn(Identity.DIALOG_CREATE_PACE_BUTTON_CREATE);
        assertTrue(getApp().getTitle().contains(text));
    }
}