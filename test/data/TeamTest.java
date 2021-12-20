package data;

import org.junit.jupiter.api.Test;
import test.Config;
import test.Util;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    private static final int NUM_TRIALS = Config.NUM_TRIALS;

    @Test
    public void testDivision() {
        Team team = new Team();
        Division division = new Division();
        team.setDivision(division);
        assertTrue(division.getTeams().contains(team));
        team.setDivision(null);
        assertNull(team.getDivision());
    }

    @Test
    public void getDivisionUUID() {
        Team team = new Team();
        Division division = new Division();
        assertNull(team.getDivisionUUID());
        team.setDivision(division);
        assertEquals(team.getDivisionUUID(), division.getUUID());
    }

    @Test
    public void updateDivisionUUID() {
        Team team = new Team();
        Division division = new Division();
        team.setDivision(division);
        team.clearDivisionUUID();
        team.updateDivisionUUID();
        assertEquals(team.getDivisionUUID(), division.getUUID());
        team.setDivision(null);
        team.clearDivisionUUID();
        assertNull(team.getDivisionUUID());
    }

    @Test
    public void clearDivisionUUID() {
        Team team = new Team();
        Division division = new Division();
        team.setDivision(division);
        team.clearDivisionUUID();
        assertNull(team.getDivisionUUID());
    }

    @Test
    public void getUUID() {
        Team a = new Team(), b = new Team();
        assertNotNull(a.getUUID());
        assertNotNull(b.getUUID());
        assertNotEquals(a.getUUID(), b.getUUID());
    }

    @Test
    public void testTeamName() {
        Team team = new Team();
        assertNull(team.getTeamName());
        String name = "12345";
        team.setTeamName(name);
        assertEquals(name, team.getTeamName());
        team.setTeamName(null);
        assertNull(team.getTeamName());
    }

    @Test
    public void getRiders() {
        Team team = new Team();
        assertNull(team.getRiders());
        String[] riders = {"a", "b", "c"};
        team.setRiders(riders);
        assertNotNull(team.getRiders());
    }

    @Test
    public void setRiders() {
        Team team = new Team();
        String[] riders = {"a", "b", "c"};
        team.setRiders(riders);
        assertEquals(team.getRiders(), riders);
    }

    @Test
    public void getNotes() {
        Team team = new Team();
        assertNull(team.getNotes());
        team.setNotes("");
        assertNotNull(team.getNotes());
    }

    @Test
    public void setNotes() {
        Team team = new Team();
        String notes = "test";
        team.setNotes(notes);
        assertEquals(team.getNotes(), notes);
    }

    @Test
    public void testStartTime() {
        Team team = new Team();
        assertNull(team.getStartTime());
        Time time = new Time(124124);
        team.setStartTime(time);
        assertEquals(time, team.getStartTime());
    }

    @Test
    public void testEndTime() {
        Team team = new Team();
        assertNull(team.getEndTime());
        Time time = Util.randomTime();
        team.setEndTime(time);
        assertEquals(time, team.getEndTime());
    }

    @Test
    public void getElapsedTime() {
        Team team = new Team();

        //Check nulls
        assertNull(team.getElapsedTime());
        team.setStartTime(new Time(5));
        assertNull(team.getElapsedTime());
        team.setStartTime(null);
        team.setEndTime(new Time(5));
        assertNull(team.getElapsedTime());

        //Test differences
        for (int i = 0; i < NUM_TRIALS; i++) {
            Time a = Util.randomTime(), b = Util.randomTime();
            long difference;

            if (a.getValue() < b.getValue()) {
                team.setStartTime(a);
                team.setEndTime(b);
                difference = b.subtract(a).getValue();
            } else {
                team.setStartTime(b);
                team.setEndTime(a);
                difference = a.subtract(b).getValue();
            }

            assertEquals(team.getElapsedTime().getValue(), difference);
        }
    }

    @Test
    public void hasElapsed() {
        Team team = new Team();
        assertFalse(team.hasElapsed());
        team.setStartTime(Util.randomTime());
        assertFalse(team.hasElapsed());
        team.setEndTime(Util.randomTime());
        assertTrue(team.hasElapsed());
    }

    @Test
    public void getDistanceToGoal() {
        Team team = new Team();
        assertNull(team.getElapsedTime());
        Division division = new Division();
        team.setDivision(division);
        assertNull(team.getElapsedTime());
        for(int i = 0; i < NUM_TRIALS; i++) {
            division.setGoalTime(Util.randomTime());
            team.setStartTime(Util.randomTime());
            team.setEndTime(new Time(team.getStartTime().getValue() + Util.randomTimeValue()));
            Time expected = division.getGoalTime().subtract(team.getElapsedTime()).absolute();
            assertEquals(expected,team.getDistanceToGoal());
        }
    }

    @Test
    public void excluded() {
        Team team = new Team();
        assertFalse(team.isExcluded());
        team.setExcluded(true);
        assertTrue(team.isExcluded());
        team.setExcluded(false);
        assertFalse(team.isExcluded());
    }

    @Test
    public void isCompleted() {
        Team team = new Team();
        assertFalse(team.isCompleted());
        team.setStartTime(Util.randomTime());
        assertFalse(team.isCompleted());
        team.setEndTime(Util.randomTime());
        assertTrue(team.isCompleted());
        team.setExcluded(true);
        assertFalse(team.isCompleted());
    }

    public static String[] randomRiders(int length) {
        String[] riders = new String[length];
        for(int i = 0; i < length; i++) {
            riders[i] = Util.randomFullName();
        }
        return riders;
    }

    @Test
    public void getNumberOfRiders() {
        Team team = new Team();
        for(int i = 0; i < NUM_TRIALS; i++) {
            int expected = (int) (Math.random() * 50);
            team.setRiders(randomRiders(expected));
            assertEquals(expected,team.getNumberOfRiders());
        }
    }

    //TODO test
    @Test
    public void getRidersString() {
    }

    //TODO test
    @Test
    public void getStartString() {
    }

    //TODO test
    @Test
    public void getEndString() {
    }

    //TODO test
    @Test
    public void getElapsedString() {
    }

    //TODO test
    @Test
    public void getNotesDisplay() {
    }
}