package data.structure;

import data.api.IDivision;
import data.api.IPace;
import data.api.ITeam;
import data.interfaces.Identifiable;

import java.util.*;


public class Pace implements IPace {

    private final List<ITeam> teams;
    private final List<IDivision> divisions;

    public Pace() {
        teams = new ArrayList<>();
        divisions = new ArrayList<>();
    }

    @Override
    public ITeam getTeam(UUID uuid) {
        return Identifiable.findInCollection(teams, uuid);
    }

    @Override
    public IDivision getDivision(UUID uuid) {
        return Identifiable.findInCollection(divisions, uuid);
    }

    @Override
    public List<ITeam> getTeams() {
        return teams;
    }

    @Override
    public List<ITeam> getTeams(IDivision division) {
        List<ITeam> group = new LinkedList<>();
        for (ITeam team : teams) {
            if (team.getDivision() == division) {
                group.add(team);
            }
        }
        return group;
    }

    @Override
    public Collection<IDivision> getDivisions() {
        return divisions;
    }

    @Override
    public boolean addTeam(ITeam team) {
        return teams.add(team);
    }

    @Override
    public boolean removeTeam(ITeam team) {
        return teams.remove(team);
    }

    @Override
    public boolean addDivision(IDivision division) {
        return divisions.add(division);
    }

    @Override
    public boolean removeDivision(IDivision division) {
        return division != getDefaultDivision() && divisions.remove(division);
    }

    @Override
    public boolean removeDivisionForced(IDivision division) {
        return divisions.size() > 1 && divisions.remove(division);
    }

    @Override
    public IDivision getDefaultDivision() {
        return divisions.get(0);
    }

    @Override
    public void setDefaultDivision(IDivision division) {
        divisions.remove(division);
        IDivision prev = divisions.get(0);
        divisions.set(0, division);
        divisions.add(prev);
    }
}
