package data.structure;

import data.api.IClock;
import data.api.IDivision;
import data.api.IPace;
import data.interfaces.PaceComponentTest;
import org.junit.jupiter.api.Test;
import test.resources.RandomUtils;
import test.resources.TestIdentifiable;

import java.util.UUID;

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

    /**
     * Creates a new Division object
     *
     * @return New Division object
     */
    private static IDivision newDivision() {
        UUID uuid = UUID.randomUUID();
        return new IDivision() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public void setName(String name) {

            }

            @Override
            public IClock getGoalTime() {
                return null;
            }

            @Override
            public void setGoalTime(IClock time) {

            }

            @Override
            public UUID getUUID() {
                return uuid;
            }

            @Override
            public IPace getPace() {
                return null;
            }

            @Override
            public void setPace(IPace pace) {

            }
        };
    }

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
}