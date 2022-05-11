package org.tealeaf.pacemanager.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.tealeaf.pacemanager.util.Identifiable;

public class Division implements Identifiable {

    private final StringProperty name = new SimpleStringProperty("");
    private String id;

    public Division() {
        id = randomId();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
