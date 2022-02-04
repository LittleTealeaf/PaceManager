package api.database;

import java.util.List;

public interface IPace {

    List<IDivision> getDivisions();

    List<ITeam> getTeams();
}
