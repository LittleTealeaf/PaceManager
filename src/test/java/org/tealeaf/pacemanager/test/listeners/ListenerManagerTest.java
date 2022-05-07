package org.tealeaf.pacemanager.test.listeners;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListenerManagerTest {

    ListenerManager listenerManager;

    @BeforeEach
    void setUp() {
        listenerManager = new ListenerManager();
    }

    @AfterEach
    void tearDown() {
        listenerManager.getListeners().forEach(item -> listenerManager.unregisterListener(item));
    }

    @Test
    void registerListener() {
    }

    @Test
    void unregisterListener() {
    }

    @Test
    void callListeners() {
    }
}