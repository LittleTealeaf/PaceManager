package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.util.Identifiable;

import java.util.ArrayList;
import java.util.List;

public class Team implements Identifiable, PaceComponent {
    private final String id;

    private String divisionId;
    private transient Pace pace;

    private final List<String> riders;

    private EventTime startTime;
    private EventTime endTime;



    public Team() {
        id = Identifiable.generateID();
        riders = new ArrayList<>();
    }

    @Override
    public String getID() {
        return id;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public Division getDivision() {
        return getPace().getDivision(divisionId);
    }

    public void setDivision(Division division) {
        setDivisionId(division.getID());
    }

    public void setDivisionId(String id) {
        this.divisionId = divisionId;
    }

    @Override
    public void setPace(Pace pace) {
        this.pace = pace;
    }

    @Override
    public Pace getPace() {
        return pace;
    }

    public EventTime getEndTime() {
        return endTime;
    }

    public void setEndTime(EventTime endTime) {
        this.endTime = endTime;
    }

    public EventTime getStartTime() {
        return startTime;
    }

    public void setStartTime(EventTime startTime) {
        this.startTime = startTime;
    }

    public EventTime getElapsed() {
        return getStatus() == Status.COMPLETED ? endTime.subtract(startTime) : null;
    }

    public Status getStatus() {
        return startTime == null ? Status.NOT_STARTED : endTime == null ? Status.IN_PROGRESS : Status.COMPLETED;
    }

    public enum Status {
        NOT_STARTED,IN_PROGRESS,COMPLETED;
    }
}
