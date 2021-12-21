package data;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a single team in a given competition. A team is composed of one or more riders, is given a team number and division, and stores
 * additional values pertaining to the pace
 * @author Thomas Kwashnak
 */
public class Team implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;

    private List<Rider> riders;
    private DivisionPointer division;
    private String notes, name;
    private boolean excluded;
    private Time startTime, endTime;

    public Team() {
        riders = new ArrayList<>();
        name = "";
        notes = "";
        excluded = false;
    }

    /**
     * Attempts to return the division of the pace
     * @return The team's {@code division}, if it is referenced by the pointer. Returns {@code null} if the pointer does not have a reference to
     * the division.
     */
    public Division getDivision() {
        if(division instanceof Division division) {
            return division;
        } else if(division != null && (division = division.tryDivision()) instanceof Division division) {
            return division;
        } else {
            return null;
        }
    }

    /**
     * Returns the raw pointer to the team's division
     * @return The division pointer, which should have a reference to the team's division, or at least have the UUID of the proper division.
     */
    public DivisionPointer getDivisionPointer() {
        return division;
    }

    /**
     * Updates the team's division pointer from a provided list of divisions. If one of the divisions provided has the same UUID as the current
     * pointer, then the team sets its current pointer to that division.
     * @param divisions List of divisions (as an {@link Iterable}) to check division UUIDs with.
     */
    public void lookupDivision(@NotNull Iterable<Division> divisions) {
        divisions.forEach(div -> {
            if (division.getUUID().equals(div.getUUID())) {
                division = div;
            }
        });

    }

    /**
     * Gets the elapsed time between the start and the end time. Only returns the elapsed time if both times are not null
     * @return The elapsed time between the start and end time. Returns {@code null} if either or both start and end times are null.
     */
    public Time getElapsedTime() {
        return startTime == null || endTime == null ? null : new Time(endTime.getTime() - startTime.getTime());
    }

    public Time getStartTime() {
        return startTime;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public String getNotes() {
        return notes;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(name);
        out.writeObject(division != null ? division.asDivisionPointer() : null);
        out.writeObject(riders);
        out.writeObject(startTime);
        out.writeObject(endTime);
        out.writeObject(notes);
        out.writeBoolean(excluded);
    }

    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        division = (DivisionPointer) in.readObject();
        riders = (ArrayList<Rider>) in.readObject();
        startTime = (Time) in.readObject();
        endTime = (Time) in.readObject();
        notes = (String) in.readObject();
        excluded = in.readBoolean();
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setNotes(String notes) {
        if(notes != null) {
            this.notes = notes;
        }
    }

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDivision(DivisionPointer division) {
        this.division = division;
    }

    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int result = getRiders() != null ? getRiders().hashCode() : 0;
        result = 31 * result + (getDivision() != null ? getDivision().hashCode() : 0);
        result = 31 * result + (getNotes() != null ? getNotes().hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isExcluded() ? 1 : 0);
        result = 31 * result + (getStartTime() != null ? getStartTime().hashCode() : 0);
        result = 31 * result + (getEndTime() != null ? getEndTime().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (isExcluded() != team.isExcluded()) return false;
        if (getRiders() != null ? !getRiders().equals(team.getRiders()) : team.getRiders() != null) return false;
        if (getDivision() != null ? !getDivision().equals(team.getDivision()) : team.getDivision() != null) return false;
        if (getNotes() != null ? !getNotes().equals(team.getNotes()) : team.getNotes() != null) return false;
        if (!Objects.equals(name, team.name)) return false;
        if (getStartTime() != null ? !getStartTime().equals(team.getStartTime()) : team.getStartTime() != null) return false;
        return getEndTime() != null ? getEndTime().equals(team.getEndTime()) : team.getEndTime() == null;
    }
}
