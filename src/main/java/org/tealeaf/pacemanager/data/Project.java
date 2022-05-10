package org.tealeaf.pacemanager.data;

import org.tealeaf.pacemanager.app.dialogs.EditTeamDialog;
import org.tealeaf.pacemanager.events.EventCoordinator;

import java.io.File;

public class Project implements EditTeamDialog.OnSaveTeam {

    private Pace pace;

    public Project() {

    }

    public Project(File file) {

    }

    public void registerEventCoordinator(EventCoordinator eventCoordinator) {
        eventCoordinator.addListener(this);
    }

    public Pace getPace() {
        return pace;
    }

    public Project(Pace pace) {
        this.pace = pace;
    }

    public String getName() {
        return pace.getName();
    }

    public void setName(String name) {
        pace.setName(name);
    }

    @Override
    public void onSaveTeam(Team team) {
        pace.getTeams().parallelStream().filter(team::idEquals).findFirst().orElseGet(pace::newTeam).update(team);
    }
}
