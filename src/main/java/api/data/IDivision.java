package api.data;

import api.util.PaceComponent;
import api.util.UniqueIdentity;

/**
 * @author Thomas Kwashnak
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IDivision extends PaceComponent, UniqueIdentity {

    /**
     * Display name of the Division
     * @return Name of the division
     */
    String getName();

    /**
     * Sets the display name of the Division
     * @param name Division Name
     */
    void setName(String name);

    /**
     * Returns the goal time that participants in the division should return
     * @return The goal time
     */
    IClock getGoalTime();

    void setGoalTime(IClock goalTime);
}
