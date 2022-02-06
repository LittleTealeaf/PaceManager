package api.interfaces;

import api.database.IPace;

/**
 * Indicates that a class is a subcomponent of an {@link IPace}. Requires the class to implement methods to reference the pace that the class is a
 * part of.
 * @author Thomas Kwashnak
 */
public interface PaceComponent {
    void setPace(IPace pace);
    IPace getPace();
}
