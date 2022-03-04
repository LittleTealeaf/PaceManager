package data.structure;

import data.api.IDivision;
import data.api.IPace;
import data.interfaces.PaceComponentTest;
import org.junit.jupiter.api.Test;
import test.resources.RandomUtils;
import data.interfaces.IdentifiableTest;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest implements TestIdentifiable, PaceComponentTest {

    /**
     * Creates a new Pace object
     *
     * @return New Pace object
     */
    private static IPace newPace() {
        return new Pace();
    }

//    @Test
//    public void testDivisionUUID() {
//        Team team = new Team();
//        IDivision division = newDivision();
//        assertNotNull(division);
//        IPace pace = newPace();
//        pace.addDivision(division);
//        team.setPace(pace);
//        team.setDivision(division.getUUID());
//        assertEquals(division, team.getDivision());
//    }

    @Test
    @Override
    public void testIdentifiable() {
        doIdentifiableTests(Team::new);
    }

    @Test
    @Override
    public void testPaceComponent() {
        doPaceComponentTests(Team::new);
    }

    @Test
    public void testConstructor() {
        Team team = new Team();
        assertNotNull(team.getUUID());
        assertNotNull(team.getRiders());
        assertTrue(team.isIncluded());
        assertEquals(0, team.getRiders().size());
        assertNull(team.getName());
        assertNull(team.getDivision());
    }

    @Test
    public void testName() {
        Team team = new Team();
        assertNull(team.getName());
        String name = RandomUtils.randomName();
        team.setName(name);
        assertEquals(name, team.getName());
    }

    @Test
    public void testNullDivision() {
        Team team = new Team();
        assertNull(team.getDivision());
    }

    /**
     * Creates a new Division object
     *
     * @return New Division object
     */
    private static IDivision newDivision() {
        return null;
    }
}