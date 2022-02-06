package app.database;

import api.database.IDivision;
import api.database.IPace;
import api.database.ITeam;

import java.util.List;
import java.util.UUID;

public class Pace implements IPace {

    @Override
    public List<IDivision> getDivisions() {
        return null;
    }

    @Override
    public List<ITeam> getTeams() {
        return null;
    }

    @Override
    public IDivision findDivisionByUUID(UUID uuid) {
        return null;
    }
}
