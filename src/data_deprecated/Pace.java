package data_deprecated;

import interfaces.JsonProcessable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public class Pace implements JsonProcessable, Serializable {

    private final Set<Team> teams;
    private final Set<Division> divisions;

    public Pace() {
        teams = new HashSet<>();
        divisions = new HashSet<>();
    }

    @Override
    public void postDeserialization() {
        teams.forEach(team -> team.lookupDivision(divisions));
    }
}
