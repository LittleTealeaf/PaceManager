package data.structure;

import data.api.IClock;
import data.api.IDivision;
import data.api.IPace;

import java.util.UUID;

public class Division implements IDivision {

    private String name;
    private IClock goalTime;
    private UUID uuid;

    private transient IPace pace;


    public Division() {
        uuid = UUID.randomUUID();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IClock getGoalTime() {
        return goalTime;
    }

    @Override
    public void setGoalTime(IClock time) {
        this.goalTime = time;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public IPace getPace() {
        return pace;
    }

    @Override
    public void setPace(IPace pace) {
        this.pace = pace;
    }
}
