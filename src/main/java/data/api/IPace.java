package data.api;

import java.util.Collection;
import java.util.UUID;

import static util.ClassUtil.formatKey;

/**
 * @author Thomas Kwashnak
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IPace {

    /**
     * Variable Key for the Teams list in implementations of IPace
     */
    String KEY_TEAMS = formatKey(IPace.class, "teams");
    /**
     * Variable Key for the Divisions list in implementations of IPace
     */
    String KEY_DIVISIONS = formatKey(IPace.class, "divisions");

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
     * Gets the collection of teams currently participating in the pace
     *
     * @return The teams currently in the pace
     */
    Collection<ITeam> getTeams();

    /**
     * Gets the collection of divisions that the pace currently has
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
    
    /**
     * Adds a division to the pace
     * @param division The division to add to the pace
     */
    void addDivision(IDivision division);

    /**
     * Removes a division from the pace. Will not remove the division if it is not the default division in the pace. 
     * @param division The division to remove
     * @return {@code true} if the division was removed, {@code false} if the division was unable to be removed, or if it
     * was the default division
     */
    boolean removeDivision(IDivision division);

    /**
     * Removes a division from the pace. If the division is the default division, the next division will become the new
     * division. Will not remove the division if it is the last division in the pace
     * @param division The division to remove
     * @return {@code true} if the division was removed, {@code false} if it could not remove, or if it was the last division
     */
    boolean removeDivisionForced(IDivision division);

    /**
     * Gets the default division. The default division is a protected division that can only be removed if forced,
     * and never be removed if it is the last division. By default, any new team will be assigned to this division
     * @return The default division
     */
    IDivision getDefaultDivision();

    /**
     * Sets the default division for the pace. This division is indicated as the first division in the pace.
     * This will add the division if it is not already in the division list.
     * @param division The new default division
    */
    void setDefaultDivision(IDivision division);
}
