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

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rider rider = (Rider) o;

        if (getFirstName() != null ? !getFirstName().equals(rider.getFirstName()) : rider.getFirstName() != null) return false;
        return getLastName() != null ? getLastName().equals(rider.getLastName()) : rider.getLastName() == null;
    }

    public String toString() {
        return firstName.equals("") ? lastName : lastName.equals("") ? firstName : String.format("%s %s", firstName, lastName);
    }
}
