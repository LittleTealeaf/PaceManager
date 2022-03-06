package data.api;

import data.interfaces.Identifiable;
import data.interfaces.PaceComponent;

public interface IDivision extends Identifiable, PaceComponent {

    String getName();

    void setName(String name);

    IClock getGoalTime();

    void setGoalTime(IClock time);

    default boolean isDefaultDivision() {
        return getPace() != null && getPace().getDefaultDivision() == this;
    }
}
