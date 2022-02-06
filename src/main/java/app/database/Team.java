package app.database;

import api.database.*;

import java.util.List;
import java.util.UUID;

public class Team implements ITeam {

    private String name;
    private List<IRider> riders;

    private transient IPace pace;

    public Team() {

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
    public List<IRider> getRiders() {
        return riders;
    }

    @Override
    public void clearRiders() {

    }

    @Override
    public void addRider(IRider rider) {

    }

    @Override
    public void removeRider(IRider rider) {

    }

    @Override
    public String getNotes() {
        return null;
    }

    @Override
    public void setNotes(String notes) {

    }

    @Override
    public ITimeInstance getTimeStart() {
        return null;
    }

    @Override
    public void setTimeStart(ITimeInstance timeStart) {

    }

    @Override
    public ITimeInstance getTimeFinish() {
        return null;
    }

    @Override
    public void setTimeFinish(ITimeInstance timeFinish) {

    }

    @Override
    public ITimeInstance getTimeElapsed() {
        return null;
    }

    @Override
    public UUID getDivisionUUID() {
        return null;
    }

    @Override
    public IDivision getDivision() {
        return null;
    }

    @Override
    public void setDivision(IDivision division) {

    }

    @Override
    public void setPace(IPace pace) {
        this.pace = pace;
    }

    @Override
    public IPace getPace() {
        return pace;
    }
}
