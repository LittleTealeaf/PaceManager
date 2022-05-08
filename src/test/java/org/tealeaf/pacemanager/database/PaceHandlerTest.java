package org.tealeaf.pacemanager.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.org.tealeaf.pacemanager.UserInterfaceTest;
import org.tealeaf.pacemanager.database.dataobjects.Pace;

import static org.junit.jupiter.api.Assertions.*;

class PaceHandlerTest extends UserInterfaceTest {

    private Pace pace;
    private boolean executed;

    @BeforeEach
    void resetPace() {
        pace = null;
    }

    @BeforeEach
    void resetExecuted() {
        executed = false;
    }

    @Test
    void openNewPace() {

        app.addListener((PaceHandler.OnPaceOpened) pace -> PaceHandlerTest.this.pace = pace);
        app.getProjectManager().openNewPace();
        assertNotNull(pace);
        assertNotNull(app.getProjectManager().getPace());
    }

    @Test
    void closePace() {
        app.addListener((PaceHandler.OnPaceClosed) () -> executed = true);
        app.getProjectManager().openNewPace();
        app.getProjectManager().closePace();
        assertTrue(executed);
        assertNull(app.getProjectManager().getPace());
    }


}