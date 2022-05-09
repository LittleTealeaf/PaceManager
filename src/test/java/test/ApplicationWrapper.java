package test;

import org.tealeaf.pacemanager.app.Identity;

/**
 * Wraps steps used for different tests
 */
public class ApplicationWrapper extends UserInterfaceWrapper implements Randomizer {

    protected void actionOpenCreatePace() {
        clickOn(Identity.LAUNCHER_BUTTON_NEW);
    }

    protected void actionCreateNewPace() {
        actionCreateNewPace(randomName());
    }

    protected void actionCreateNewPace(String name) {
        actionOpenCreatePace();
        writeInto(Identity.DIALOG_CREATE_PACE_FIELD_NAME, name);
        clickOn(Identity.DIALOG_CREATE_PACE_BUTTON_CREATE);
    }
}
