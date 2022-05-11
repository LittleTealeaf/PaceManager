package org.tealeaf.pacemanager.data;

import com.google.gson.annotations.SerializedName;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.tealeaf.pacemanager.util.Identifiable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Team implements Identifiable {

    @SerializedName("name")
    private final StringProperty nameProperty = new SimpleStringProperty("");
    @SerializedName("divisionId")
    private final StringProperty divisionIdProperty = new SimpleStringProperty();
    @SerializedName("riders")
    private final ObservableList<String> ridersProperty = FXCollections.observableList(new LinkedList<>());
    @SerializedName("startTime")
    private final ObjectProperty<EventTime> startTimeProperty = new SimpleObjectProperty<>();
    @SerializedName("endTime")
    private final ObjectProperty<EventTime> endTimeProperty = new SimpleObjectProperty<>();
    private final transient StringProperty printRidersProperty = new SimpleStringProperty();
    @SerializedName("id")
    private String id;

    public Team() {
        id = randomId();
        ridersProperty.addListener((ListChangeListener<String>) c -> {
            StringBuilder builder = new StringBuilder();
            ridersProperty.forEach(item -> builder.append(item).append("\n"));
            printRidersProperty.set(builder.append("\\").toString().replace("\n\\", ""));
        });
    }

    public StringProperty getPrintRidersProperty() {
        return printRidersProperty;
    }

    public void setDivision(Division division) {
        this.divisionIdProperty.set(division == null ? null : division.getId());
    }

    public void addRider(String rider) {
        ridersProperty.add(rider);
    }

    public void removeRider(String rider) {
        ridersProperty.remove(rider);
    }

    public StringProperty divisionProperty() {
        return divisionIdProperty;
    }

    public ObjectProperty<EventTime> startTimeProperty() {
        return startTimeProperty;
    }

    public ObjectProperty<EventTime> endTimeProperty() {
        return endTimeProperty;
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public State getState() {
        return startTimeProperty.isNull().get() ? State.NOT_STARTED : endTimeProperty.isNull().get() ? State.IN_PROGRESS : State.COMPLETED;
    }

    @Override
    public Team clone() {
        return new Team().update(this);
    }

    public Team update(Team team) {
        setId(team.getId());
        setDivisionId(team.getDivisionId());
        setStartTime(team.getStartTime());
        setEndTime(team.getEndTime());
        setName(team.getName());

        new ArrayList<>(ridersProperty).parallelStream().filter(i -> !team.getRiders().contains(i)).sequential().forEach(ridersProperty::remove);
        team.getRiders().parallelStream().filter(i -> !ridersProperty.contains(i)).sequential().forEach(ridersProperty::add);

        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivisionId() {
        return divisionIdProperty.get();
    }

    public void setDivisionId(String divisionId) {
        this.divisionIdProperty.set(divisionId);
    }

    public EventTime getStartTime() {
        return startTimeProperty.getValue();
    }

    public EventTime getEndTime() {
        return endTimeProperty.getValue();
    }

    public void setEndTime(EventTime endTime) {
        if (!endTimeProperty.isEqualTo(endTime).get()) {
            endTimeProperty.set(endTime);
        }
    }

    public String getName() {
        return nameProperty.get();
    }

    public ObservableList<String> getRiders() {
        return ridersProperty;
    }

    public void setName(String name) {
        if (!nameProperty.isEqualTo(name).get()) {
            nameProperty.set(name);
        }
    }

    public void setStartTime(EventTime startTime) {
        if (!startTimeProperty.isEqualTo(startTime).get()) {
            startTimeProperty.set(startTime);
        }
    }

    public enum State {
        NOT_STARTED("In"),
        IN_PROGRESS("Out"),
        COMPLETED("Done");

        private final String string;

        State(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }
}
