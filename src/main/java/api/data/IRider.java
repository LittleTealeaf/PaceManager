package api.data;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface IRider {

    String KEY_FIRST_NAME = IRider.class.getName() + ".FirstName";
    String KEY_LAST_NAME = IRider.class.getName() + ".LastName";

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getFullName();
}
