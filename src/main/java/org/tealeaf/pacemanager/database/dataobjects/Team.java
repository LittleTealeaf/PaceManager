package org.tealeaf.pacemanager.database.dataobjects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import org.tealeaf.pacemanager.util.Identifiable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Identifiable, PaceComponent, Serializable {
    private final String id;

//    private StringProperty observableTeamNumber = new SimpleStringProperty();
//    private StringProperty observableDivisionId = new SimpleStringProperty();
//    private ObjectProperty<EventTime> observableStartTime = new SimpleObjectProperty<>();
//    private ObjectProperty<EventTime> observableEndTime = new SimpleObjectProperty<>();
//    private ObservableList<String> observableRiders = FXco


    private transient Pace pace;
    private final List<String> riders;
    private EventTime startTime;
    private EventTime endTime;
    private String divisionId;
    private String teamNumber;



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

    public List<String> getRiders() {
        return riders;
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

    public void addRider(String rider) {
        riders.add(rider);
    }

    public void removeRider(String rider) {
        riders.remove(rider);
    }

    public Status getStatus() {
        return startTime == null ? Status.NOT_STARTED : endTime == null ? Status.IN_PROGRESS : Status.COMPLETED;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public enum Status {
        NOT_STARTED,IN_PROGRESS,COMPLETED;
    }
}
