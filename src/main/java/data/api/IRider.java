package data.api;



/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface IRider {

    String KEY_FIRST_NAME = IRider.class.getName() + ".FirstName";
    String KEY_LAST_NAME = IRider.class.getName() + ".LastName";

    /**
     * The first name of the rider
     * @return The first name of the rider. {@code null} if a first name has not been given
     */
    String getFirstName();

    /**
     * Sets the first name of the rider
     * @param firstName The new first name of the rider. {@code firstName} will be set to {@code null} if this is ""
     */
    void setFirstName(String firstName);

    /**
     * The last name of the rider
     * @return The last name of the rider. {@code null} if a last name has not been given
     */
    String getLastName();

    /**
     * Sets the last name of the rider
     * @param lastName The new last name of the rider. {@code lastName} will be set to {@code null} if this is ""
     */
    void setLastName(String lastName);

    /**
     * Combines both the first name and the last name into a single name
     * @return The full name of the rider
     */
    String getFullName();
}
