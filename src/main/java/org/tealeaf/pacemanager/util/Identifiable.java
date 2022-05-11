package org.tealeaf.pacemanager.util;

import java.util.UUID;

public interface Identifiable {

    //TODO add setId
    default String randomId() {
        return UUID.randomUUID().toString();
    }

    default boolean idEquals(Identifiable other) {
        return other.getId().equals(getId());
    }

    String getId();

    void setId(String id);
}
