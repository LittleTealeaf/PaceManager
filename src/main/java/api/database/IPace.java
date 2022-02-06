package api.database;

import java.util.List;
import java.util.UUID;

public interface IPace {

    List<IDivision> getDivisions();

    List<ITeam> getTeams();

    IDivision findDivisionByUUID(UUID uuid);
}
