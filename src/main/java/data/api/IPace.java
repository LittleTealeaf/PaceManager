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

    /**
     * Finds a team based on its UUID
     * @param uuid The team's unique identifier
     * @return The team, if a team by that UUID exists, otherwise returns {@code null}.
     */
    ITeam getTeam(UUID uuid);

    /**
     * Finds a division based on its UUID
     * @param uuid The division's unique identifier
     * @return The division, if a division by that UUID exists, otherwise returns {@code null}
     */
    IDivision getDivision(UUID uuid);

    /**
     *
     * @return The teams currently in the pace
     */
    Collection<ITeam> getTeams();

    /**
     *
     * @return The divisions currently in the pace
     */
    Collection<IDivision> getDivisions();

    /**
     * Adds a team object to the pace
     * @param team The new team object
     */
    void addTeam(ITeam team);

    /**
     * Removes a team object from the pace
     * @param team The team object to remove
     * @return {@code true} if the removal was successful, {@code false} otherwise
     */
    boolean removeTeam(ITeam team);

    void addDivision(IDivision division);

    boolean removeDivision(IDivision division);

    IDivision getDefaultDivision();

    void setDefaultDivision(IDivision division);
}
