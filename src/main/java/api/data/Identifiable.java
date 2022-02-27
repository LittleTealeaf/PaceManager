package api.data;

import java.util.UUID;

/**
 * Indicates that a class has a unique identifier, or a UUID.
 */
public interface Identifiable {

    /**
     * Classpath Tag for the UUID parameter
     */
    String UUID = Identifiable.class.getName() + ".UUID";

    /**
     * The unique identifier of the object.
     * @return A UUID uniquely identifying the object it was created under.
     */
    UUID getUUID();
}
