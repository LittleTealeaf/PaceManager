package api.data;

/**
 * @author Thomas Kwashnak
 */
public interface IClock {

    String TIME = IClock.class.getName() + ":TIME";

    /**
     *
     * @return Number of seconds since 00:00 (midnight)
     */
    int getTime();

    void setTime();

    String asString();
}
