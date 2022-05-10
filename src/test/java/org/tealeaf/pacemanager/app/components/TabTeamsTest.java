package org.tealeaf.pacemanager.app.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identity;
import test.ApplicationWrapper;

import static org.junit.jupiter.api.Assertions.*;

class TabTeamsTest extends ApplicationWrapper {

    @BeforeEach
    void beforeOpenPace() {
        actionCreateNewPace();
        clickOn(Identity.APP_TAB_TEAMS);
    }
}