package api.database;

import api.interfaces.PaceComponent;

import java.util.UUID;

/**
 * @author Thomas Kwashnak
 */
public interface IDivision extends PaceComponent {

    String getName();
    UUID getUUID();
}
