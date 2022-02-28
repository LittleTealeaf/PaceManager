package data.api;

import java.util.Collection;
import java.util.UUID;
import data.interfaces.PaceComponent;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface IPace {

    String KEY_TEAMS = IPace.class.getName() + ".Teams";
    String KEY_DIVISIONS = IPace.class.getName() + ".Divisions";

    ITeam getTeam(UUID uuid);

    IDivision getDivision(UUID uuid);

    Collection<ITeam> getTeams();

    Collection<IDivision> getDivisions();

    void addTeam(ITeam team);

    boolean removeTeam(ITeam team);

    void addDivision(IDivision division);

    boolean removeDivision(IDivision division);

    IDivision getDefaultDivision();
}
