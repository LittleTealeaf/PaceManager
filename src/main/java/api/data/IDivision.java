package api.data;

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



}
