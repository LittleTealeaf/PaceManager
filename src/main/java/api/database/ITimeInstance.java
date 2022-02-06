package api.database;

/**
 * @author Thomas Kwashnak
 */
public interface ITimeInstance {
    int getTime();
    void setTime(int time);
    String asString();

    void add(int time);

    default void add(ITimeInstance time) {
        add(time.getTime());
    }

    default void subtract(int time) {
        add(-time);
    }

    default void subtract(ITimeInstance time) {
        subtract(time.getTime());
    }

    ITimeInstance getNegative();

    void absolute();

    ITimeInstance getAbsolute();
}
