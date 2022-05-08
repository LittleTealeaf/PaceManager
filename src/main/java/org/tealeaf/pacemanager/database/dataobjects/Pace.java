package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.events.EventCoordinator;

public class Pace {

    private final EventCoordinator eventCoordinator;

    public Pace(EventCoordinator eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
    }

}
