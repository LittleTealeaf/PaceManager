package data.structure;

import org.junit.jupiter.api.Test;
import test.resources.TestIdentifiable;

class TeamTest implements TestIdentifiable {

    @Test
    @Override
    public void testIdentifiable() {
        doIdentifiableTests(Team::new);
    }
}