package data;

import interfaces.Nameable;
import interfaces.UniqueIdentifier;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Team implements Serializable, UniqueIdentifier, Nameable {

    private final UUID uuid;

    private String name;

    private List<Rider> riders;

    public Team() {
        uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
