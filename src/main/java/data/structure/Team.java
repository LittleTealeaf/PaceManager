package data.structure;

import data.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Team implements ITeam {

    private UUID uuid;
    private IPace pace;

    private String name;

    private IClock startTime;
    private IClock endTime;

    private List<IRider> riders;

    private transient IDivision division;
    private UUID divisionUUID;

    private String notes;
    private boolean included;

    public Team() {
        this.uuid = UUID.randomUUID();
        riders = new ArrayList<>();
        included = true;
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
    public IDivision getDivision() {
        if (division == null) {
            if (divisionUUID == null || getPace() == null) {
                return null;
            } else {
                return division = getPace().getDivision(divisionUUID);
            }
        } else {
            return division;
        }
    }

    @Override
    public void setDivision(IDivision division) {
        this.division = division;
        this.divisionUUID = division.getUUID();
    }

    @Override
    public void setDivision(UUID divisionUUID) {
        this.divisionUUID = divisionUUID;
    }

    @Override
    public IClock getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(IClock startTime) {
        this.startTime = startTime;
    }

    @Override
    public IClock getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(IClock endTime) {
        this.endTime = endTime;
    }

    @Override
    public Status getStatus() {
        return getStartTime() == null ? Status.NOT_STARTED : getEndTime() == null ? Status.IN_PROGRESS : Status.COMPLETED;
    }

    @Override
    public IClock getElapsedTime() {
        return null;
    }

    @Override
    public Collection<IRider> getRiders() {
        return riders;
    }

    @Override
    public boolean addRider(IRider rider) {
        return riders.add(rider);
    }

    @Override
    public boolean removeRider(IRider rider) {
        return riders.remove(rider);
    }

    @Override
    public boolean isIncluded() {
        return included;
    }

    @Override
    public void setIncluded(boolean included) {
        this.included = included;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public IPace getPace() {
        return pace;
    }

    @Override
    public void setPace(IPace pace) {
        this.pace = pace;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
