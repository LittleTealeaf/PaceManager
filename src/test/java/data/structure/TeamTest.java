package data.structure;

import data.interfaces.PaceComponentTest;
import org.junit.jupiter.api.Test;
import test.resources.RandomUtils;
import test.resources.TestIdentifiable;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest implements TestIdentifiable, PaceComponentTest {

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
    public void testDivision() {
        Team team = new Team();
        assertNull(team.getDivision());
    }
}