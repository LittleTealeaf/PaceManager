package data.structure;

import data.api.IRider;

public class Rider implements IRider {

    private String firstName, lastName;

    public Rider() {

    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName.equals("") ? null : firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName.equals("") ? null : lastName;
    }

    @Override
    public String getFullName() {
        if(firstName == null) {
            if(lastName == null) {
                return "";
            } else {
                return lastName;
            }
        } else {
            if(lastName == null) {
                return firstName;
            } else {
                return firstName + " " + lastName;
            }
        }
    }
}
