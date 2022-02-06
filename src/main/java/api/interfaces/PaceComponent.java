package api.interfaces;

import api.database.IPace;

/**
 * Indicates that a class is a subcomponent of an {@link IPace}. Requires the class to implement methods to reference the pace that the class is a
 * part of.
 * @author Thomas Kwashnak
 */
public interface PaceComponent {

    /**
     * Tells the object which pace it is currently tied to
     * @param pace {@link IPace} object that this class is a part of
     */
    void setPace(IPace pace);

    /**
     * Provides the reference to the {@link IPace} that this class is a part of
     * @return A reference to the {@code IPace}
     */
    IPace getPace();
}
