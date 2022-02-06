package api.database;

import api.interfaces.PaceComponent;

import java.util.UUID;

public interface IDivision extends PaceComponent {

    String getName();
    UUID getUUID();
}
