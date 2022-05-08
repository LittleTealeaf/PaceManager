package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.util.Identifiable;

public class Team implements Identifiable {
    private final String id;

    public Team() {
        id = Identifiable.generateID();
    }

    @Override
    public String getID() {
        return id;
    }


    public interface OnTeamUpdated {
        void onTeamUpdated(Team team);
    }
}
