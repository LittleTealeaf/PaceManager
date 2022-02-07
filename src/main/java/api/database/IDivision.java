package api.database;

import api.util.PaceComponent;
import api.util.UniqueIdentity;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface IDivision extends PaceComponent, UniqueIdentity {

    String getName();

    IClock getGoalTime();

    void setGoalTime(IClock goalTime);

    void setName(String name);
}
