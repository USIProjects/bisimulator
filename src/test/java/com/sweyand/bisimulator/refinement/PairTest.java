package com.sweyand.bisimulator.refinement;

import org.junit.jupiter.api.Test;

import com.sweyand.bisimulator.graph.Node;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @Test
    void testConstructor() {
        Node a = new Node("A");
        Node b = new Node("B");

        Pair p = new Pair(a, b);

        assertEquals(a, p.getU());
        assertEquals(b, p.getV());
        assertFalse(p.isMarked());
    }

    @Test
    void testMark() {
        Pair p = new Pair(new Node("A"), new Node("B"));
        assertFalse(p.isMarked());
        p.mark();
        assertTrue(p.isMarked());
    }

    @Test
    void testEqualsAndHashCode() {
        Pair p1 = new Pair(new Node("A"), new Node("B"));
        Pair p2 = new Pair(new Node("A"), new Node("B"));
        Pair p3 = new Pair(new Node("B"), new Node("A"));

        assertEquals(p1, p2);
        assertNotEquals(p1, p3); // Reihenfolge bleibt wichtig!
    }
}
