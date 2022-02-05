package app.database;

import api.database.IRider;

public class Rider implements IRider {

    private String firstName, lastName;

    public Rider() {
        firstName = null;
        lastName = null;
    }

    public Rider(String firstName) {
        this.firstName = firstName;
        this.lastName = null;
    }

    public Rider(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        if (hasFirstName()) {
            if (hasLastName()) {
                return String.format("%s %s", getFirstName(), getLastName());
            } else {
                return getFirstName();
            }
        } else {
            if (hasLastName()) {
                return getLastName();
            } else {
                return "";
            }
        }
    }

    @Override
    public String getFirstName() {
        return firstName != null ? firstName : "";
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName != null ? lastName : "";
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean hasFirstName() {
        return firstName != null && !firstName.replace(" ", "").equals("");
    }

    public boolean hasLastName() {
        return lastName != null && !lastName.replace(" ", "").equals("");
    }
}
