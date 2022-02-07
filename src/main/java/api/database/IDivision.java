package api.database;

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

    void setName(String name);

    IClock getGoalTime();

    void setGoalTime(IClock goalTime);
}
