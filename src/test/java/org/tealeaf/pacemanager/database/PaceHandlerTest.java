package org.tealeaf.pacemanager.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identifier;
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
        actionOpenNewPace();
        assertNotNull(pace);
        assertNotNull(app.getPaceHandler().getPace());
    }

    @Test
    void closePace() {
        app.addListener((PaceHandler.OnPaceClosed) () -> executed = true);
        actionOpenNewPace();
        clickSequence(Identifier.MENU_FILE,Identifier.MENU_FILE_CLOSE);
        assertTrue(executed);
        assertNull(app.getPaceHandler().getPace());
    }


}