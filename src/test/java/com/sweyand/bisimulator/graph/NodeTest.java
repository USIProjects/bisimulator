package com.sweyand.bisimulator.graph;

import org.junit.jupiter.api.Test;


import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testLabelConstructor() {
        Node n = new Node("A");
        assertEquals("A", n.getLabel());
        assertTrue(n.getPropositions().isEmpty());
    }

    @Test
    void testAddPropositions() {
        Node n = new Node("A");
        n.addPropositions('a', 'b');

        assertEquals(List.of('a', 'b'), n.getPropositions());
    }

    @Test
    void testAddOutgoingNodes() {
        Node a = new Node("A");
        Node b = new Node("B");

        a.addOutgoingNodes(b);

        assertEquals(List.of(b), a.getOutgoingNodes());
        assertEquals(List.of(a), b.getIncomingNodes());
    }

    @Test
    void testImmutabilityOfLists() {
        Node a = new Node("A");
        a.addPropositions('x');

        assertThrows(UnsupportedOperationException.class,
                () -> a.getPropositions().add('y'));

        assertThrows(UnsupportedOperationException.class,
                () -> a.getOutgoingNodes().add(new Node("B")));
    }

    @Test
    void testEqualsAndHashCode() {
        Node a1 = new Node("A");
        Node a2 = new Node("A");
        Node b = new Node("B");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1, b);
    }
}
