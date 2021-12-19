package data;

import interfaces.Nameable;

public class Rider implements Nameable {

    private String firstName, lastName;

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

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
