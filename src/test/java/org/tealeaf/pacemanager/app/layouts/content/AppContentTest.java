package org.tealeaf.pacemanager.app.layouts.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.events.EventManager;
import test.org.tealeaf.pacemanager.UserInterfaceTest;
import test.org.tealeaf.pacemanager.database.dataobjects.RandomDataObjects;

import java.io.IOException;
import java.nio.file.Path;

class AppContentTest extends UserInterfaceTest implements RandomDataObjects {

    @Test
    void switchTabs(@TempDir Path tmpDir) throws IOException {
        actionOpenPaceFile(randomPace(new EventManager()), tmpDir.resolve("test.json"));

        get(AppContent.class,lookup(Identifier.LAYOUT_CONTENT_APP)).getTabs().forEach(tab -> clickOn(tab.getId()));
        get(AppContent.class,lookup(Identifier.LAYOUT_CONTENT_APP)).getTabs().forEach(tab -> clickOn(tab.getId()));
    }

}