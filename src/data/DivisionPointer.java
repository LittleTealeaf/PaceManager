package data;

import interfaces.Identifiable;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Thomas Kwashnak
 */
public class DivisionPointer implements Serializable, Identifiable {

    @Serial
    private static final long serialVersionUID = 42L;

    protected final UUID uuid;
    private transient Division division;

    public DivisionPointer() {
        this(UUID.randomUUID());
    }

    public DivisionPointer(UUID uuid) {
        this.uuid = uuid;
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

    @Override
    public String toString() {
        return uuid.toString();
    }
}
