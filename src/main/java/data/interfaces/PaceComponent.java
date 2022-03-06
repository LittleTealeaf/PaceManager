package data.interfaces;

import data.api.IPace;

/**
 * Indicates that a class has a reference to a pace object. Outlines the ability to get and set the {@link IPace} reference using {@link #getPace()}
 * and {@link #setPace(IPace pace)} respectivevly.
 *
 * @author Thomas Kwashnak
 * @since 2.0.0
 */
public interface PaceComponent {

    /**
     * Returns the reference to the provided {@link IPace}
     *
     * @return Reference to the IPace assigned to this object
     */
    IPace getPace();

    /**
     * Updates the object's current reference
     *
     * @param pace The new reference to assign to this object
     */
    void setPace(IPace pace);
}
