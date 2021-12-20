package data_deprecated;

import interfaces.JsonProcessable;

@Deprecated
public class Team implements JsonProcessable {

    private DivisionPointer division;

    public Division getDivision() {
        return division == null ? null : (Division) (division instanceof Division ? division : (division = division.asDivision()));
    }

    public void lookupDivision(Iterable<Division> divisions) {
        divisions.forEach(division -> {
            if (this.division.getUUID().equals(division.getUUID())) {
                this.division = division;
            }
        });
    }

    @Override
    public void preSerialization() {
        division = division.asDivisionPointer();
    }
}
