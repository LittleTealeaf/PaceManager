package org.tealeaf.pacemanager.app.components;

import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.UserInterfaceTest;
import static org.tealeaf.pacemanager.app.Identifier.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppMenuTest extends UserInterfaceTest {

    @Test
    public void testMenuFile() {
        clickOn(MENU_FILE);
        assertNotNull(lookup(MENU_FILE_OPEN).query());
        assertNotNull(lookup(MENU_FILE_EXIT).query());
    }
}