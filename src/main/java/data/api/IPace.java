package data.api;

import java.util.Collection;
import java.util.UUID;

public interface IPace {

    ITeam getTeam(UUID uuid);

    IDivision getDivision(UUID uuid);

    Collection<ITeam> getTeams();

    Collection<ITeam> getTeams(IDivision division);

    Collection<IDivision> getDivisions();

    void addTeam(ITeam team);

    boolean removeTeam(ITeam team);

    void addDivision(IDivision division);

    boolean removeDivision(IDivision division);

    boolean removeDivisionForced(IDivision division);

    IDivision getDefaultDivision();

    void setDefaultDivision(IDivision division);
}
