package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.events.EventCoordinator;

import java.util.ArrayList;
import java.util.List;

public class Pace {

    private final List<Division> divisions = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();

    private final EventCoordinator eventCoordinator;

    public Pace(EventCoordinator eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
    }





    public interface OnTeamAdded {
        void onTeamAdded(Team team);
    }

    public interface OnTeamRemoved {
        void onTeamRemoved(Team team);
    }

    public interface OnDivisionAdded {
        void onDivisionAdded(Division division);
    }

    public interface OnDivisionRemoved {
        void onDivisionRemoved(Division division);
    }

}
