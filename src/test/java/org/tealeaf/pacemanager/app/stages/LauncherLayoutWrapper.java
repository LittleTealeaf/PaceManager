package org.tealeaf.pacemanager.app.stages;

import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identity;
import test.ApplicationWrapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LauncherLayoutWrapper extends ApplicationWrapper {

    @Test
    void testOpenCreate() {
        clickOn(Identity.LAUNCHER_BUTTON_NEW);
        assertTrue(exists(Identity.DIALOG_CREATE_PACE));
    }
}