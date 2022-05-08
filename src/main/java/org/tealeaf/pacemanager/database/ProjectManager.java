package org.tealeaf.pacemanager.database;

import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.events.EventCoordinator;

public class ProjectManager {

    private final EventCoordinator eventCoordinator;

    private Pace pace;

    public ProjectManager(EventCoordinator eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
    }


    public void openNewPace() {
        pace = new Pace(eventCoordinator);
        eventCoordinator.runEvent(OnPaceOpened.class,i -> i.onPaceOpened(pace));
    }

    public void closePace() {
        pace = null;
        eventCoordinator.runEvent(OnPaceClosed.class, OnPaceClosed::onPaceClosed);
    }

    public Pace getPace() {
        return pace;
    }

    public interface OnPaceOpened {
        void onPaceOpened(Pace pace);
    }

    public interface OnPaceClosed {
        void onPaceClosed();
    }
}
