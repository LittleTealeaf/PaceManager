package data.structure;

import data.api.*;

import java.util.Collection;
import java.util.UUID;

public class Team implements ITeam {

    private UUID uuid;
    private IPace pace;

    private String name;

    private transient IDivision division;
    private UUID divisionUUID;

    public Team() {
        this.uuid = UUID.randomUUID();
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
        return division == null || !division.getUUID().equals(divisionUUID) ? division = getPace().getDivision(divisionUUID) : division;
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
        return null;
    }

    @Override
    public void setStartTime(IClock startTime) {

    }

    @Override
    public IClock getEndTime() {
        return null;
    }

    @Override
    public void setEndTime(IClock endTime) {

    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public IClock getElapsedTime() {
        return null;
    }

    @Override
    public Collection<IRider> getRiders() {
        return null;
    }

    @Override
    public boolean addRider(IRider rider) {
        return false;
    }

    @Override
    public boolean removeRider(IRider rider) {
        return false;
    }

    @Override
    public boolean isIncluded() {
        return false;
    }

    @Override
    public void setIncluded(boolean included) {

    }

    @Override
    public String getNotes() {
        return null;
    }

    @Override
    public void setNotes(String notes) {

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
