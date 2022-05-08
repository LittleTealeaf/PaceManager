package org.tealeaf.pacemanager.database.dataobjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.tealeaf.pacemanager.events.EventManager;
import test.org.tealeaf.pacemanager.RandomValues;
import test.org.tealeaf.pacemanager.database.dataobjects.RandomDataObjects;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest implements RandomDataObjects {

    EventManager eventManager;
    Pace pace;

    boolean executed;

    @BeforeEach
    void init() {
        eventManager = new EventManager();
        pace = new Pace(eventManager);
        executed = false;
    }

    @AfterEach
    void close() {
        pace.close();
    }

    @Test
    void getID() {
        Set<String> ids = new HashSet<>();
        eventManager.addListener((Pace.OnDivisionAdded) division -> {
            assertFalse(ids.contains(division.getID()));
            ids.add(division.getID());
        });
        Stream.generate(this::randomDivision).limit(1000).parallel().forEach(pace::addDivision);
    }

    @Test
    void setPace() {
        Division division = randomDivision();
        assertNull(division.getPace());
        division.setPace(pace);
        assertSame(pace,division.getPace());
    }

    @Test
    void getPace() {
        eventManager.addListener((Pace.OnDivisionAdded) division -> assertSame(pace, division.getPace()));
        pace.addDivision(randomDivision());
    }
}