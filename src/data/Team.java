package data;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Team implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;
    private List<Rider> riders;
    private DivisionPointer division;
    private String notes, teamName;
    private boolean excluded;
    private Time startTime, endTime;

    public Team() {
        riders = new ArrayList<>();
        teamName = "";
        notes = "";
        excluded = false;
    }

    public Division getDivision() {
        return division == null ? null : (Division) (division instanceof Division ? division : (division = division.asDivision()));
    }

    public void lookupDivision(@NotNull Iterable<Division> divisions) {
        divisions.forEach(div -> {
            if (division.getUUID().equals(div.getUUID())) {
                division = div;
            }
        });
    }

    public Time getElapsedTime() {
        return startTime == null || endTime == null ? null : new Time(startTime.getTime() + endTime.getTime());
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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(teamName);
        out.writeObject(division.getUUID());
        out.writeObject(riders.toArray());
        out.writeObject(startTime);
        out.writeObject(endTime);
        out.writeObject(notes);
        out.writeBoolean(excluded);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        teamName = (String) in.readObject();
        division = new DivisionPointer((UUID) in.readObject());
        riders = Arrays.asList((Rider[]) in.readObject());
        startTime = (Time) in.readObject();
        endTime = (Time) in.readObject();
        notes = (String) in.readObject();
        excluded = in.readBoolean();
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setNotes(String notes) {this.notes = notes;}

    public boolean isExcluded() {
        return excluded;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }
}
