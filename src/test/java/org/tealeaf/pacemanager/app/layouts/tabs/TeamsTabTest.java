package org.tealeaf.pacemanager.app.layouts.tabs;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.tealeaf.pacemanager.app.Identifier;
import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.events.EventManager;
import test.org.tealeaf.pacemanager.UserInterfaceTest;
import test.org.tealeaf.pacemanager.database.dataobjects.RandomDataObjects;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TeamsTabTest extends UserInterfaceTest implements RandomDataObjects {

    @Test
    void testContentVisible() {
        actionOpenNewPace();
        clickOn(Identifier.TAB_TEAMS);
        assertTrue(exists(Identifier.TAB_TEAMS_CONTENT));
        assertTrue(exists(Identifier.TAB_TEAMS_CONTENT_TABLE));
    }

    @Test
    void hasItems(@TempDir Path tmpdir) throws IOException {
        Pace pace = randomPace(new EventManager());
        actionOpenPaceFile(pace,tmpdir.resolve("test.json"));
        actionSelectItemTable(Identifier.TAB_TEAMS_CONTENT_TABLE,RANDOM.nextInt(pace.getTeams().size()));
    }
}