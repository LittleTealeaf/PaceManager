package data.api;

import data.interfaces.Identifiable;
import data.interfaces.PaceComponent;

import java.util.List;

/**
 * Represents a division of riders with a goal time
 * @author Thomas Kwashnak
 * @since 2.0.0
 */
public interface IDivision extends Identifiable, PaceComponent {

    /**
     * Gets the display name of the division
     * @return Name of the division
     */
    String getName();

    /**
     * Sets the display name of the division
     * @param name Display name of the division
     */
    void setName(String name);

    /**
     * Gets the current goal time that riders within the division should aim for
     * @return The current goal time of the division
     */
    IClock getGoalTime();

    /**
     * Sets the goal time that riders within the division should aim for
     * @param goalTime The new goal time
     */
    void setGoalTime(IClock goalTime);

    /**
     * Gets the list of teams that are currently stored within the division
     * @return List of teams in the division
     */
    List<ITeam> getTeams();

    /**
     * Checks the associated IPace to see if this is the default division
     * @return {@code true} if this division is the default division, {@code false} otherwise.
     */
    boolean isDefaultDivision();
}
