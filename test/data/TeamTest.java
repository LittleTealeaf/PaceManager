package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    private static final int TEST_CASES = 1024;

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
        assertEquals(team.getDivisionUUID(),division.getUUID());
    }

    @Test
    public void updateDivisionUUID() {
        Team team = new Team();
        Division division = new Division();
        team.setDivision(division);
        team.clearDivisionUUID();
        team.updateDivisionUUID();
        assertEquals(team.getDivisionUUID(),division.getUUID());
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
        assertNotEquals(a.getUUID(),b.getUUID());
    }

    @Test
    public void testTeamName() {
        Team team = new Team();
        assertNull(team.getTeamName());
        String name = "12345";
        team.setTeamName(name);
        assertEquals(name,team.getTeamName());
        team.setTeamName(null);
        assertNull(team.getTeamName());
    }

    @Test
    public void getRiders() {
        Team team = new Team();
        assertNull(team.getRiders());
        String[] riders = {"a","b","c"};
        team.setRiders(riders);
        assertNotNull(team.getRiders());
    }

    @Test
    public void setRiders() {
        Team team = new Team();
        String[] riders = {"a","b","c"};
        team.setRiders(riders);
        assertEquals(team.getRiders(),riders);
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
        assertEquals(team.getNotes(),notes);
    }

    @Test
    public void testStartTime() {
        Team team = new Team();
        assertNull(team.getStartTime());
        Time time = new Time(124124);
        team.setStartTime(time);
        assertEquals(time,team.getStartTime());
    }

    @Test
    public void testEndTime() {
        Team team = new Team();
        assertNull(team.getEndTime());
        Time time = new Time(198423);
        team.setEndTime(time);
        assertEquals(time,team.getEndTime());
    }

    @Test
    public void getElapsedTime() {
        Team team = new Team();
        for(int i = 0; i < TEST_CASES; i++) {
            Time a = TimeTest.randomTime(), b = TimeTest.randomTime();
            long difference = 0;

            if(a.getValue() < b.getValue()) {
                team.setStartTime(a);
                team.setEndTime(b);
                difference = b.subtract(a).getValue();
            } else {
                team.setStartTime(b);
                team.setEndTime(a);
                difference = a.subtract(b).getValue();
            }

            assertEquals(team.getElapsedTime().getValue(),difference);
        }
    }

    @Test
    public void hasElapsed() {
    }

    @Test
    public void getDistanceToGoal() {
    }

    @Test
    public void isExcluded() {
    }

    @Test
    public void setExcluded() {
    }

    @Test
    public void isCompleted() {
    }

    @Test
    public void getNumberOfRiders() {
    }

    @Test
    public void getRidersString() {
    }

    @Test
    public void getStartString() {
    }

    @Test
    public void getEndString() {
    }

    @Test
    public void getElapsedString() {
    }

    @Test
    public void getNotesDisplay() {
    }

    @Test
    public void testToString() {
    }
}