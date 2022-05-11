package org.tealeaf.pacemanager.app.components;

import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.app.Identity;
import org.tealeaf.pacemanager.data.Team;
import test.ApplicationWrapper;

import static org.junit.jupiter.api.Assertions.*;

class TableTeamsTest extends ApplicationWrapper {

    @BeforeEach
    void loadPace() {
        actionCreateNewPace();
    }

    @Test
    void testName() {
        Team team = randomTeam();
        actionAddTeam(team);
        assertEquals(team.getName(),get(Text.class, Identity.TABLE_TEAMS_CELL_TEAM_NAME,0).getText());
    }

}