package data.api;

import data.interfaces.Identifiable;
import data.interfaces.PaceComponent;

import static util.ClassUtil.formatKey;

/**
 * @author Thomas Kwashnak
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IDivision extends Identifiable, PaceComponent {

    /**
     * Key tags used for variables used in implementations of IDivision
     */
    String KEY_NAME = formatKey(IDivision.class, "name");
    /**
     * Key tags used for variables used in implementations of IDivision
     */
    String KEY_GOAL_TIME = formatKey(IDivision.class, "goalTime");

    /**
     * The displayable name of the division
     * @return The current name of the division
     */
    String getName();

    /**
     * Sets the displayable name of the division
     * @param name The division's displayable name
     */
    void setName(String name);

    /**
     * Gets the current goal time that teams in the division should aim for
     * @return The clock time that the teams should be aiming for
     */
    IClock getGoalTime();

    /**
     * Sets the goal time that teams in the division should aim for
     * @param time The clock time that teams should aim for.
     */
    void setGoalTime(IClock time);

    /**
     * Checks if this division is the default division in the pace.
     * @return {@code true} if this is the default division. {@code false} if this division is not the default division, or if no pace has been
     * specified
     */
    default boolean isDefaultDivision() {
        return getPace() != null && getPace().getDefaultDivision() == this;
    }

}
