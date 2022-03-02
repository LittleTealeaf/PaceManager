package data.interfaces;

import java.util.UUID;

/**
 * Indicates that a class has a unique identifier, or a UUID.
 * @version 2.0.0
 */
public interface Identifiable {

    /**
     * Classpath Tag for the UUID parameter
     */
    String KEY_UUID = Identifiable.class.getName() + ".UUID";

    /**
     * The unique identifier of the object.
     * @return A UUID uniquely identifying the object it was created under.
     */
    UUID getUUID();
}
