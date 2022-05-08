package org.tealeaf.pacemanager.system;

import javafx.event.Event;
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;
import org.tealeaf.pacemanager.Launcher;
import org.tealeaf.pacemanager.events.EventCoordinator;

import java.io.File;

public class Preferences implements Launcher.OnClose {


    private static Preferences preferences;




    public Preferences(EventCoordinator eventCoordinator) {
        eventCoordinator.addListener(this);
        preferences = this;
    }

    @Override
    public void onClose() {
        System.out.println("Save preferences");
    }

    public static Preferences getInstance() {
        return preferences;
    }


}
