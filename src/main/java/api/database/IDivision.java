package api.database;

import api.util.PaceComponent;
import api.util.UniqueIdentity;

import java.util.UUID;

public interface IDivision extends PaceComponent, UniqueIdentity {
    String getName();
    IClock getGoalTime();

    void setGoalTime(IClock goalTime);
    void setName(String name);
}
