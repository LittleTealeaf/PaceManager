package data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents an individual rider, or participant in the competition.
 * @author Thomas Kwashnak
 */
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

    /**
     * Returns the full name of the rider
     * @return Output of {@link #getName()}
     * @see #getName()
     */
    public String toString() {
        return getName();
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

    /**
     * Combines both the given first name and last name to create a complete name. <p>For example, if {@code firstName = "thomas"} and {@code
     * lastName = "kwashnak"}, this method would return {@code "thomas kwashnak"}. If only one name is provided, then it will return only that
     * provided name.
     * @return Full name of the rider, as a {@code String}
     * @see #toString()
     */
    public String getName() {
        return firstName.equals("") ? lastName : lastName.equals("") ? firstName : String.format("%s %s", firstName, lastName);
    }
}
