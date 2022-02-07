package api.database;

import api.interfaces.PaceComponent;

import java.util.UUID;

/**
 * @author Thomas Kwashnak
 */
public interface IDivision extends PaceComponent {

    String getName();

    /**
     * Gets the unique identifier that identifies the Division separately from it's name or values
     * @return Unique Identifier of the Division as a UUID
     */
    UUID getUUID();
}
