package data.api;

import data.interfaces.Identifiable;
import data.interfaces.PaceComponent;

public interface IDivision extends Identifiable, PaceComponent {
    String getName();

    void setName(String name);

    IClock getGoalTime();

    void setGoalTime(IClock goalTime);
}
