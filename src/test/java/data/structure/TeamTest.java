package data.structure;

import data.interfaces.PaceComponentTest;
import org.junit.jupiter.api.Test;
import test.resources.TestIdentifiable;

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
}