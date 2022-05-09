package org.tealeaf.pacemanager.app.layouts.content;

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
        File file = tempPath.resolve("abc.json").toFile();
        GsonWrapper.write(randomPace(new EventManager()), file);
        clickOn(Identifier.LAYOUT_CONTENT_OPEN_PACE_BUTTON);
        applyPath(file.getPath());
        assertTrue(exists(Identifier.LAYOUT_CONTENT_APP));
    }
}