package org.tealeaf.pacemanager.util;

import org.tealeaf.pacemanager.app.Identity;

import java.util.UUID;

public interface Identifiable {

    //TODO add setId
    default String randomId() {
        return UUID.randomUUID().toString();
    }

    String getId();

    void setId(String id);

    default boolean idEquals(Identifiable other) {
        return other.getId().equals(getId());
    }
}
