package data;

import org.junit.jupiter.api.Test;
import test.Util;

import static org.junit.jupiter.api.Assertions.*;


public class DivisionTest {

    @Test
    public void testGoalTime() {
        Division division = new Division(null);
        assertNull(division.getGoalTime());
        Time time = Util.randomTime();
        division.setGoalTime(time);
        assertEquals(time, division.getGoalTime());
    }

    @Test
    public void testTeams() {
        Division division = new Division();
        Team team = Util.randomTeam();
        assertEquals(0, division.getTeams().size());
        assertFalse(division.getTeams().contains(team));
        division.addTeam(team);
        assertTrue(division.getTeams().contains(team));
        division.removeTeam(team);
        assertFalse(division.getTeams().contains(team));
    }

    @Test
    public void testClearTeamShallow() {
        Division division = new Division();
        Team team = Util.randomTeam();
        division.addTeam(team);
        team.setDivision(division);
        assertTrue(division.getTeams().contains(team));
        assertEquals(division, team.getDivision());
        division.clearTeamsShallow();
        assertFalse(division.getTeams().contains(team));
        assertEquals(division, team.getDivision());
    }

    @Test
    public void testGetUUID() {
        Division division = new Division();
        assertNotNull(division.getUUID());
        assertNotEquals(division.getUUID(), new Division().getUUID());
    }

    @Test
    public void testEquals() {
        Division division = new Division();
        assertEquals(division, division);
        assertNotEquals(division, new Division());
    }

    @Test
    public void testName() {
        Division division = new Division();
        assertNull(division.getName());
        String name = Util.randomName();
        division.setName(name);
        assertEquals(name, division.getName());
    }
}