package api.data;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 */
public interface IPace {

    ITeam getTeam(UUID uuid);

    IDivision getDivision(UUID uuid);

    Collection<ITeam> getTeams();

    Collection<IDivision> getDivisions();

    void addTeam(ITeam team);

    boolean removeTeam(ITeam team);

    void addDivision(IDivision division);

    boolean removeDivision(IDivision division);

    default void addPaceComponent(PaceComponent component) {
        component.setPace(this);
    }

    default void removePaceComponent(PaceComponent component) {
        component.setPace(null);
    }
}
