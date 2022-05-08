package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.util.Identifiable;

public class Division implements Identifiable {
    private final String id;

    public Division() {
        this.id = Identifiable.generateID();
    }

    @Override
    public String getID() {
        return id;
    }
}
