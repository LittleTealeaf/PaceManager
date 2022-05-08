package org.tealeaf.pacemanager.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class EventManagerTest {

    EventManager eventManager;
    int count;

    @BeforeEach
    public void setUp() {
        eventManager = new EventManager();
        count = 0;
    }

    @Test
    public void getListeners() {
        assertNotNull(eventManager.getListeners());
        eventManager.addListener(this);
        assertTrue(eventManager.getListeners().contains(this));
    }

    @Test
    public void addListener() {
        eventManager.addListener(this);
        assertTrue(eventManager.getListeners().contains(this));
    }

    @Test
    public void removeListener() {
        eventManager.addListener(this);
        eventManager.removeListener(this);
        assertFalse(eventManager.getListeners().contains(this));
    }

    @RepeatedTest(10)
    public void runEvent() {
        final int createCount = new Random().nextInt(1,1000);
        int listenerCount = 0;
        for(int i = 0; i < createCount; i++) {
            if(Math.random() > 0.5) {
                eventManager.addListener(new TestClass());
                listenerCount++;
            } else {
                eventManager.addListener("");
            }
        }
        eventManager.runEvent(TestInterface.class,TestInterface::execute);
        assertEquals(listenerCount,count);
    }

    interface TestInterface {
        void execute();
    }

    class TestClass implements TestInterface {

        @Override
        public boolean equals(Object obj) {
            return false;
        }

        public void execute() {
            count++;
        }
    }
}