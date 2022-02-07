package api.util;

import java.util.UUID;

public interface UniqueIdentity {

    static UUID createUUID() {
        return UUID.randomUUID();
    }

    UUID getUUID();
}
