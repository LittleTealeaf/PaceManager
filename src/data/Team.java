package data;

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
    private ClockTime startTime, endTime;

    public Team() {
        riders = new ArrayList<>();
    }

    public Division getDivision() {
        return division == null ? null : (Division) (division instanceof Division ? division : (division = division.asDivision()));
    }

    public void lookupDivision(Iterable<Division> divisions) {
        divisions.forEach(div -> {
            if (division.getUUID().equals(div.getUUID())) {
                division = div;
            }
        });
    }
}
