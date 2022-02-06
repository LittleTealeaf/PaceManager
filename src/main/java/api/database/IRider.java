package api.database;

/**
 * @author Thomas Kwashnak
 */
public interface IRider {

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getFullName();
}
