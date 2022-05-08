package org.tealeaf.pacemanager.app.layouts;

import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identifier;
import test.org.tealeaf.pacemanager.UserInterfaceTest;

import static org.junit.jupiter.api.Assertions.*;

class AboutTest extends UserInterfaceTest {

    @Test
    void canOpen() {
        actionOpenAbout();
        assertTrue(exists(Identifier.LAYOUT_ABOUT));
    }
}