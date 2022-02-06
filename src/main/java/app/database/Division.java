package app.database;

import api.database.IDivision;
import api.database.IPace;

import java.util.UUID;

public class Division implements IDivision {

    private transient IPace pace;
    private UUID uuid;

    public Division(IPace pace) {
        this.uuid = UUID.randomUUID();
        setPace(pace);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public void setPace(IPace pace) {
        this.pace = pace;
    }

    public IPace getPace() {
        return pace;
    }
}
