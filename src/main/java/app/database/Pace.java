package app.database;

import api.database.IDivision;
import api.database.IPace;
import api.database.ITeam;

import java.util.List;

public class Pace implements IPace {

    @Override
    public List<IDivision> getDivisions() {
        return null;
    }

    @Override
    public List<ITeam> getTeams() {
        return null;
    }

    public void onLoad() {

    }
}
