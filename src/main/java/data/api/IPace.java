package data.api;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface IPace {

    ITeam getTeam(UUID uuid);

    IDivision getDivision(UUID uuid);

    List<ITeam> getTeams();

    List<ITeam> getTeams(IDivision division);

    Collection<IDivision> getDivisions();

    boolean addTeam(ITeam team);

    boolean removeTeam(ITeam team);

    boolean addDivision(IDivision division);

    boolean removeDivision(IDivision division);

    boolean removeDivisionForced(IDivision division);

    IDivision getDefaultDivision();

    void setDefaultDivision(IDivision division);
}
