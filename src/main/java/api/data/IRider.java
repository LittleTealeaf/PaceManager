package api.data;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 */
public interface IRider {

    String FIRST_NAME = IRider.class.getName() + ".FirstName";
    String LAST_NAME = IRider.class.getName() + ".LastName";

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getFullName();
}
