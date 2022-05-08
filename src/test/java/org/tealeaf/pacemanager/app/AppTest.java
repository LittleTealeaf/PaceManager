package org.tealeaf.pacemanager.app;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.tealeaf.pacemanager.Launcher;
import org.tealeaf.pacemanager.UserInterfaceTest;
import org.tealeaf.pacemanager.events.EventManagerTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AppTest extends UserInterfaceTest {


    int count;


    @BeforeEach
    void resetCount() {
        count = 0;
    }

    @Test
    void getListeners() {
        assertNotNull(app.getListeners());
        app.addListener(this);
        assertTrue(app.getListeners().contains(this));
    }

    @Test
    void addListener() {
        app.addListener(this);
        assertTrue(app.getListeners().contains(this));
    }

    @Test
    void removeListener() {
        app.addListener(this);
        app.removeListener(this);
        assertFalse(app.getListeners().contains(this));
    }

    @Test
    void runEvent() {
        final int createCount = new Random().nextInt(1, 1000);
        int listenerCount = 0;
        for(int i = 0; i < createCount; i++) {
            if(Math.random() > 0.5) {
                app.addListener(new TestClass());
                listenerCount++;
            } else {
                app.addListener("");
            }
        }
        app.runEvent(TestClass.class, TestInterface::execute);
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