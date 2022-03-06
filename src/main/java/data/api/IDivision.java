package data.api;

import data.interfaces.Identifiable;
import data.interfaces.PaceComponent;

import java.util.List;

public interface IDivision extends Identifiable, PaceComponent {

    String getName();

    void setName(String name);

    IClock getGoalTime();

    void setGoalTime(IClock goalTime);

    List<ITeam> getTeams();

    boolean isDefaultDivision();
}
