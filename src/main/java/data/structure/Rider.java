package data.structure;

import data.api.IRider;

public class Rider implements IRider {

    private String firstName;
    private String lastName;

    /**
     * Creates a rider with no name
     */
    public Rider() {

    }

    /**
     * Creates a rider with a first name
     * 
     * @param firstName The first name of the rider
     */
    public Rider(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Creates a rider with a full name
     * @param firstName The first name of the rider
     * @param lastName The last name of the rider
     */
    public Rider(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        if (firstName != null) {
            if (lastName != null) {
                return firstName + " " + lastName;
            } else {
                return firstName;
            }
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return getFullName();
    }

}
