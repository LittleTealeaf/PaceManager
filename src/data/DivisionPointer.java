package data;

import interfaces.Identifiable;

import java.util.UUID;

public class DivisionPointer implements Identifiable {

    protected UUID uuid;
    private transient Division division;

    public DivisionPointer() {
        uuid = UUID.randomUUID();
    }

    protected DivisionPointer(Division division) {
        this.division = division;
        this.uuid = division.getUUID();
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public Division asDivision() {
        return division;
    }

    public DivisionPointer asDivisionPointer() {
        return this;
    }
}
