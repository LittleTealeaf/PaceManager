package org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.util.Identifiable;

import java.io.Serializable;

public class Division implements Identifiable, PaceComponent, Serializable {
    private final String id;

    private String name;
    private transient Pace pace;

    public Division() {
        this.id = Identifiable.generateID();
        this.name = "";
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setPace(Pace pace) {
        this.pace = pace;
    }

    @Override
    public Pace getPace() {
        return pace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
