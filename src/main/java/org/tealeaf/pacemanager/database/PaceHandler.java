package org.tealeaf.pacemanager.database;

import org.tealeaf.pacemanager.app.components.AppMenu;
import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.events.EventCoordinator;

public class PaceHandler implements AppMenu.OnFileNew {

    private final EventCoordinator eventCoordinator;

    private Pace pace;

    public PaceHandler(EventCoordinator eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
        eventCoordinator.addListener(this);
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

    @Override
    public void onMenuFileNew() {
        openNewPace();

    }

    public interface OnPaceOpened {
        void onPaceOpened(Pace pace);
    }

    public interface OnPaceClosed {
        void onPaceClosed();
    }

    public interface OnPaceEdited {
        void onPaceEdited(Pace pace);
    }

}
