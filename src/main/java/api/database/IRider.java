package api.database;

public interface IRider {
    String getFirstName();
    String getLastName();
    String getFullName();
    void setFirstName(String firstName);
    void setLastName(String lastName);
}
