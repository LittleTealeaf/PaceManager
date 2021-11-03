package data;

import org.junit.jupiter.api.Test;
import test.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


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
    public void testAverageTime() {
        //        for(int j = 0; j < 2; j++) {
        //            App.settings.setExcludeOutliers(!App.settings.excludeOutliers());
        //            Division division = new Division(null);
        //            Time sum = new Time(0);
        //            long max = 0,min = 0;
        //            int count = 100;
        //            for(int i = 0; i < count; i++) {
        //                Team team = new Team();
        //                team.setStartTime(Util.randomTime());
        //                team.setEndTime(Util.randomTime().add(team.getStartTime()));
        //                sum = sum.add(team.getElapsedTime());
        //                if(max < team.getElapsedTime().getValue() || i == 0) {
        //                    max = team.getElapsedTime().getValue();
        //                }
        //                if(min > team.getElapsedTime().getValue() || i == 0) {
        //                    min = team.getElapsedTime().getValue();
        //                }
        //                division.addTeam(team);
        //            }
        //            if(App.settings.excludeOutliers()) {
        //                sum = sum.subtract(new Time(max + min));
        //            }
        //            assertEquals(sum.getValue() / count, division.getAverageTime().getValue());
        //        }
    }

    @Test
    public void testTeams() {

    }

    @Test
    public void testClearTeamShallow() {

    }

    @Test
    public void testGetUUID() {

    }

    @Test
    public void testEquals() {

    }

    @Test
    public void testName() {

    }
}