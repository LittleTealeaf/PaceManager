package org.tealeaf.pacemanager.app;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {

    @Test
    void testToString() {
        Set<String> strings = new HashSet<>();
        for (Identifier value : Identifier.values()) {
            assertNotNull(value.toString());
            assertTrue(value.toString().contains("#"));
            assertFalse(strings.contains(value.toString()));
            strings.add(value.toString());
        }
    }

    @Test
    void testSetNode() {
        for(Identifier value : Identifier.values()) {
            Node node = new Node() {};
            value.set(node);
            assertEquals(value.toString(),node.getId());
        }
    }


    @Test
    void testSetMenuItem() {
        for(Identifier value : Identifier.values()) {
            MenuItem menuItem = new MenuItem();
            value.set(menuItem);
            assertEquals(value.toString(),menuItem.getId());
        }
    }
}