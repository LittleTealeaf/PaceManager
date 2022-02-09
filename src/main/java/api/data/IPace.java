package api.data;

import api.util.PaceComponent;

import java.util.List;
import java.util.UUID;

public interface IPace {

    List<IDivision> getDivisions();

    List<ITeam> getTeams();

    IDivision lookupDivision(UUID uuid);

    default void initializeReferences() {
        for (PaceComponent component : getPaceComponents()) {
            component.setPace(this);
        }
    }

    List<PaceComponent> getPaceComponents();
}
