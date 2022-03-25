package data.api;

import data.interfaces.Identifiable;
import data.interfaces.PaceComponent;

import java.util.List;

/**
 * Represents a division of riders with a goal time
 * @author Thomas Kwashnak
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
     * 
     * @return
     */
    IClock getGoalTime();

    void setGoalTime(IClock goalTime);

    List<ITeam> getTeams();

    boolean isDefaultDivision();
}
