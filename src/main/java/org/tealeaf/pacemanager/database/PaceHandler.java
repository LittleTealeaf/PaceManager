package org.tealeaf.pacemanager.database;

import org.tealeaf.pacemanager.app.components.AppMenu;
import org.tealeaf.pacemanager.database.dataobjects.Pace;
import org.tealeaf.pacemanager.events.EventCoordinator;
import org.tealeaf.pacemanager.system.GsonWrapper;

import java.io.File;
import java.io.FileReader;

public class PaceHandler implements AppMenu.OnFileNew {

    private final EventCoordinator eventCoordinator;

    private Pace pace;

    public PaceHandler(EventCoordinator eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
        eventCoordinator.addListener(this);
    }


    public void openNewPace() {
        pace = new Pace(eventCoordinator);
        pace.initialize();
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

    public void openFile(File file) {
        System.out.println("Opening " + file.getPath());

        try {
            FileReader fileReader = new FileReader(file);
            Pace pace = GsonWrapper.gson.fromJson(fileReader,Pace.class);
            fileReader.close();

            if(pace != null) {
                if(this.pace != null) {
                    this.pace.close();
                }

                this.pace = pace;
                pace.initialize();

                System.out.println("HELLO");
                eventCoordinator.runEvent(OnPaceOpened.class,i -> i.onPaceOpened(pace));

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
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
