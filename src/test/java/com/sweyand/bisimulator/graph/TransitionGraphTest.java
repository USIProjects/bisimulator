package com.sweyand.bisimulator.graph;

import org.junit.jupiter.api.Test;

import com.sweyand.bisimulator.refinement.Pair;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransitionGraphTest {

    @Test
    void testAddNode() {
        TransitionGraph g = new TransitionGraph();

        Node a = new Node("A");
        g.addNode(a);

        assertEquals(List.of(a), g.getNodes());
    }

    @Test
    void testAddDuplicateNodeFails() {
        TransitionGraph g = new TransitionGraph();

        g.addNode(new Node("A"));
        assertThrows(IllegalArgumentException.class,
                () -> g.addNode(new Node("A")));
    }

    @Test
    void testGraphStructure() {
        Node a = new Node("A").addPropositions('x');
        Node b = new Node("B");
        a.addOutgoingNodes(b);

        TransitionGraph g = new TransitionGraph(a, b);

        assertEquals(2, g.getNodes().size());
        assertEquals(List.of(b), a.getOutgoingNodes());
        assertEquals(List.of(a), b.getIncomingNodes());
    }

    @Test
    void testGetBisimilarRelationSimple() {
        Node a = new Node("A").addPropositions('p');
        Node b = new Node("B").addPropositions('p');

        TransitionGraph g = new TransitionGraph(a, b);

        List<Pair> rel = g.getBisimilarRelation();

        assertTrue(rel.stream().anyMatch(p -> p.getU().equals(a) && p.getV().equals(b)));
        assertTrue(rel.stream().anyMatch(p -> p.getU().equals(b) && p.getV().equals(a)));
        assertTrue(rel.stream().anyMatch(p -> p.getU().equals(a) && p.getV().equals(a)));
        assertTrue(rel.stream().anyMatch(p -> p.getU().equals(b) && p.getV().equals(b)));
    }
}
