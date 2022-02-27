package api.data;

import java.util.UUID;

public interface Identifiable {

    String UUID = Identifiable.class.getName() + ":UUID";

    UUID getUUID();
}
