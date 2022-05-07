package org.tealeaf.pacemanager.listeners;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListenerManagerTest {

    ListenerManager listenerManager;

    int executed;

    @BeforeEach
    public void setUp() {
        listenerManager = new ListenerManager();
        executed = 0;
    }

    @Test
    public void registerListener() {
        listenerManager.registerListener(this);
        assertTrue(listenerManager.getListeners().contains(this));
    }

    @Test
    public void unregisterListener() {
        listenerManager.registerListener(this);
        listenerManager.unregisterListener(this);
        assertFalse(listenerManager.getListeners().remove(this));
    }

    @Test
    public void callListeners() {
        listenerManager.registerListener(this);
        listenerManager.registerListener(new TestClass());
        listenerManager.callListeners(TestInterface.class, TestInterface::execute);
        assertEquals(1,executed);
    }

    interface TestInterface {
        void execute();
    }

    class TestClass implements TestInterface {
        public void execute() {
            executed++;
        }
    }

}