package data.structure;

import data.api.IDivision;
import data.api.IPace;
import data.api.ITeam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Pace implements IPace {

    private final Collection<ITeam> teams = new ArrayList<>();
    private final Collection<IDivision> divisions = new ArrayList<>();
    private IDivision defaultDivision;

    public Pace() {

    }

    @Override
    public ITeam getTeam(UUID uuid) {
        for(ITeam team : teams) {
            if(team.getUUID().equals(uuid)) {
                return team;
            }
        }
        return null;
    }

    @Override
    public IDivision getDivision(UUID uuid) {
        for(IDivision division : divisions) {
            if(division.getUUID().equals(uuid)) {
                return division;
            }
        }
        return null;
    }

    @Override
    public Collection<ITeam> getTeams() {
        return teams;
    }

    @Override
    public Collection<IDivision> getDivisions() {
        return divisions;
    }

    @Override
    public void addTeam(ITeam team) {
        if(!teams.contains(team)) {
            teams.add(team);
            team.setPace(this);
        }
    }

    @Override
    public boolean removeTeam(ITeam team) {
        if(teams.remove(team)) {
            team.setPace(null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addDivision(IDivision division) {
        divisions.add(division);
        division.setPace(this);
    }

    @Override
    public boolean removeDivision(IDivision division) {
        if(!division.isDefaultDivision() && divisions.remove(division)) {
            division.setPace(null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IDivision getDefaultDivision() {
        return defaultDivision;
    }
}