package app;

/**
 * Indicates that an object is updatable, and thus has a {@link #update()} method
 * @since 1.0.0
 * @version 1.0.0
 * @author Thomas Kwashnak
 * @see App#update()
 */
public interface Updatable {
    /**
     * This method is called whenever the application pings an update, if the object is stored within the list in {@link App},
     * and should contain commands that updates values within the object
     */
    void update();
}
