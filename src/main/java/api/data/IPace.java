package api.data;

import java.util.UUID;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 */
public interface IPace {

    ITeam getTeam(UUID uuid);

    IDivision getDivision(UUID uuid);
}
