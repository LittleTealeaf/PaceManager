package data;

import java.io.Serial;
import java.io.Serializable;

public class Rider implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;

    private String firstName;
    private String lastName;

    public Rider() {
        this("", "");
    }

    public Rider(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Rider(String firstName) {
        this(firstName, "");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return toString();
    }

    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
