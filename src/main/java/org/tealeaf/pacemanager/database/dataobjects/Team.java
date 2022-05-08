package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.util.Identifiable;

public class Team implements Identifiable, PaceComponent {
    private final String id;

    private String divisionId;
    private transient Pace pace;

    public Team() {
        id = Identifiable.generateID();
    }

    @Override
    public String getID() {
        return id;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public Division getDivision() {
        return getPace().getDivision(divisionId);
    }

    public void setDivision(Division division) {
        this.divisionId = division.getID();
    }

    public void setDivisionId(String id) {
        this.divisionId = divisionId;
    }

    @Override
    public void setPace(Pace pace) {
        this.pace = pace;
    }

    @Override
    public Pace getPace() {
        return pace;
    }

    public interface OnTeamUpdated {
        void onTeamUpdated(Team team);
    }
}
