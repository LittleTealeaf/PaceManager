package org.tealeaf.pacemanager.app.layouts.content;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.events.EventManager;
import org.tealeaf.pacemanager.system.GsonWrapper;
import test.org.tealeaf.pacemanager.UserInterfaceTest;
import test.org.tealeaf.pacemanager.database.dataobjects.RandomDataObjects;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenPaceContentTest extends UserInterfaceTest implements RandomDataObjects {

    @Test
    void testOpen(@TempDir Path tempPath) throws Exception {
        press(KeyCode.ALT);
        clickOn(Identifier.LAYOUT_CONTENT_OPEN_PACE_BUTTON);
        release(KeyCode.ALT);
        clickOn(Identifier.LAYOUT_MANUAL_FILE_ENTRY_TEXT).write(randomPaceFile(tempPath).getPath());
        clickOn(Identifier.LAYOUT_MANUAL_FILE_ENTRY_BUTTON);
        assertTrue(exists(Identifier.LAYOUT_CONTENT_APP));
    }


}