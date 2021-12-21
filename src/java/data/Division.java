package data;

import java.io.Serial;

/**
 * @author Thomas Kwashnak
 */
public class Division extends DivisionPointer {

    @Serial
    private static final long serialVersionUID = 42L;

    private String name;
    private Time goalTime;

    public Division() {
        name = "";
    }

    public Division(String name) {
        this.name = name;
    }

    public Division(String name, Time goalTime) {
        this.name = name;
        this.goalTime = goalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(Time goalTime) {
        this.goalTime = goalTime;
    }

    @Override
    public Division asDivision() {
        return this;
    }

    @Override
    public DivisionPointer asDivisionPointer() {
        return new DivisionPointer(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
