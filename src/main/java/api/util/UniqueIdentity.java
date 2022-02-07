package api.util;

import java.util.UUID;

public interface UniqueIdentity {
    UUID getUUID();

    static UUID createUUID() {
        return UUID.randomUUID();
    }
}
