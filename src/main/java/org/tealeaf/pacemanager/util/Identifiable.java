package org.tealeaf.pacemanager.util;

import java.util.UUID;

public interface Identifiable {

    static String generateID() {
        return UUID.randomUUID().toString();
    }

    String getID();
}
