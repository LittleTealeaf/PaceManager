package data;

import java.util.ArrayList;
import java.util.List;

public class Division extends DivisionPointer {

    private final transient List<Team> teams;

    public Division() {
        teams = new ArrayList<>();
    }

    public void registerTeam(Team team) {
        teams.add(team);
    }

    public void unregisterTeam(Team team) {
        teams.remove(team);
    }

    @Override
    public Division asDivision() {
        return this;
    }

    @Override
    public DivisionPointer asDivisionPointer() {
        return new DivisionPointer(this);
    }
}
