package app;

/**
 * Indicates that an object is updatable, and thus has a {@link #update()} method
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @see App#update()
 * @since 1.0.0
 */
public interface Updatable {

    /**
     * This method is called whenever the application pings an update, if the object is stored within the list in {@link App},
     * and should contain commands that updates values within the object
     */
    void update ();

}
