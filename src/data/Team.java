package data;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {

    @Serial
    private static final long serialVersionUID = 42L;
    private final List<Rider> riders;
    private DivisionPointer division;
    private String notes;
    private Time startTime, endTime;

    public Team() {
        riders = new ArrayList<>();
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
}
