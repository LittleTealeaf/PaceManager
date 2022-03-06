package data.interfaces;

import org.junit.jupiter.api.Test;

/**
 * Provides tests used to test a class that extends the {@link PaceComponent} interface.
 *
 * @author Thomas Kwashnak
 * @version 2.0.0
 * @since 2.0.0
 */
public interface PaceComponentTest {

    /**
     * Runs tests on instances of the PaceComponent object.
     *
     * Checks the following:
     * <ul><li>Pace object is null by default</li>
     * <li>Setting pace to an instance of IPace returns that pace reference</li>
     * <li>Setting pace to null will return a null instance</li></ul>
     */
    @Test
    void testPaceComponent();

//    /**
//     * Performs the tests indicated in {@link #testPaceComponent()}.
//     *
//     * @param factory The factory builder for the class that extends PaceComponent
//     *
//     * @see Pace#Pace()
//     */
//    default void doPaceComponentTests(PaceComponentFactory factory) {
//        PaceComponent paceComponent = factory.build();
//        assertNull(paceComponent.getPace());
//        Pace pace = new Pace();
//        paceComponent.setPace(pace);
//        assertSame(pace, paceComponent.getPace());
//        paceComponent.setPace(null);
//        assertNull(paceComponent.getPace());
//    }

    /**
     * A factory that creates an instance of the PaceComponent interface
     */
    interface PaceComponentFactory {

        PaceComponent build();
    }
}