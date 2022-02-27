package api.data;

/**
 * Indicates that an object is part of an {@link IPace}, and should have a back-reference to the {@link IPace} it is a part of
 * @author Thomas Kwashnak
 * @since 2.0.0
 */
public interface PaceComponent {

    /**
     * Gets the pace that this object is a part of
     * @return The IPace object
     */
    IPace getPace();

    /**
     * Updates the object's current pace
     * @param pace The pace this object is a part of
     */
    void setPace(IPace pace);
}
