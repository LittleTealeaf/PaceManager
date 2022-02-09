package api.util;

import java.util.UUID;

/**
 * @author Thomas Kwashnak
 */
public interface UniqueIdentity {

    static UUID createUUID() {
        return UUID.randomUUID();
    }

    UUID getUUID();
}
