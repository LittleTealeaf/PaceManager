package data.api;

import data.interfaces.PaceComponent;
import data.interfaces.Identifiable;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface IDivision extends Identifiable, PaceComponent {

    String KEY_NAME = IDivision.class.getName() + ".name";
    String KEY_GOAL_TIME = IDivision.class.getName() + ".goalTime";

    String getName();
    void setName(String name);

    IClock getGoalTime();
    void setGoalTime(IClock time);

    default boolean isDefaultDivision() {
        return getPace().getDefaultDivision() == this;
    }

}
