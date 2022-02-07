package api.database;

/**
 * A Rider is an individual person in a hunter pace.
 *
 * @author Thomas Kwashnak
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IRider {

    /**
     * The first name of the rider.
     *
     * @param ifNull Value to return if no first name was provided
     *
     * @return The first name of the rider. Returns {@code ifNull} if no first name was provided
     */
    default String getFirstName(String ifNull) {
        String name = getFirstName();
        return name != null ? name : ifNull;
    }

    /**
     * The first name of the rider.
     *
     * @return The first name of the rider. Returns {@code null} if no first name was provided
     */
    String getFirstName();

    /**
     * Sets the first name of the rider
     *
     * @param firstName First name of the rider
     */
    void setFirstName(String firstName);

    /**
     * The last name of the rider.
     *
     * @param ifNull Value to return if no last name was provided
     *
     * @return The last name of the rider. Returns {@code ifNull} if no last name was provided
     */
    default String getLastName(String ifNull) {
        String name = getLastName();
        return name != null ? name : ifNull;
    }

    /**
     * The last name of the rider
     *
     * @return The last name of the rider. Returns {@code null} if no last name was provided
     */
    String getLastName();

    /**
     * Sets the last name of the rider
     *
     * @param lastName Last name of the rider
     */
    void setLastName(String lastName);

    /**
     * The complete name of the rider, including both first name and last name
     *
     * @return The full name of the rider, in the form of "firstName lastName". If only a firstName or lastName was provided, returns just that
     * name. If no name is provided, returns an empty string
     */
    String getFullName();
}
