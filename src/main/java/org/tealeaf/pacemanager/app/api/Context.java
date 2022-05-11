package org.tealeaf.pacemanager.app.api;

import org.tealeaf.pacemanager.app.key.KeyTracker;
import org.tealeaf.pacemanager.events.EventCoordinator;

public interface Context extends KeyTracker, EventCoordinator {}
