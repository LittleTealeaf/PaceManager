package api.database;

import java.util.List;
import java.util.UUID;

/**
 * @author Thomas Kwashnak
 */
public interface IPace {

    /**
     * All division currently used in a pace. There must be at least one division that is marked as the default division
     * @return List of Divisions
     */
    List<IDivision> getDivisions();

    /**
     * All teams currently in the pace.
     * @return
     */
    List<ITeam> getTeams();

    /**
     * Finds the division from a given UUID
     * @param uuid Unique Identifier of the Division
     * @return The division that has that UUID, returns {@code null} if no division was found with that UUID
     */
    IDivision findDivisionByUUID(UUID uuid);
}
