package data.structure;

import data.api.*;

import java.util.Collection;
import java.util.UUID;

public class Team implements ITeam {

    public Team() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public IDivision getDivision() {
        return null;
    }

    @Override
    public void setDivision(IDivision division) {

    }

    @Override
    public void setDivision(UUID divisionUUID) {

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
    public UUID getUUID() {
        return null;
    }

    @Override
    public IPace getPace() {
        return null;
    }

    @Override
    public void setPace(IPace pace) {

    }
}
