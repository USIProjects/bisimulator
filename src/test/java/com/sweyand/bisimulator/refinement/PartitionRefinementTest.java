package com.sweyand.bisimulator.refinement;

import org.junit.jupiter.api.Test;

import com.sweyand.bisimulator.graph.Node;
import com.sweyand.bisimulator.graph.TransitionGraph;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PartitionRefinementTest {

    @Test
    void testIdentityPairs() {
        Node a = new Node("A");
        Node b = new Node("B");
        TransitionGraph g = new TransitionGraph(a, b);

        PartitionRefinement pr = g.getPartitionRefinement();

        List<Pair> identity = pr.getIdentity();

        assertEquals(2, identity.size());
        assertTrue(identity.stream().anyMatch(p -> p.getU().equals(a) && p.getV().equals(a)));
        assertTrue(identity.stream().anyMatch(p -> p.getU().equals(b) && p.getV().equals(b)));
    }

    @Test
    void testTriviallyBisimilar() {
        Node a = new Node("A").addPropositions('p');
        Node b = new Node("B").addPropositions('p');

        TransitionGraph g = new TransitionGraph(a, b);
        PartitionRefinement pr = g.getPartitionRefinement();

        List<Pair> bisim = pr.getBisimilarPairs();

        assertEquals(1, bisim.size());
        Pair p = bisim.get(0);
        assertEquals("A", p.getU().getLabel());
        assertEquals("B", p.getV().getLabel());
    }

    @Test
    void testNonBisimilarDueToProps() {
        Node a = new Node("A").addPropositions('x');
        Node b = new Node("B").addPropositions('y');

        TransitionGraph g = new TransitionGraph(a, b);
        PartitionRefinement pr = g.getPartitionRefinement();

        List<Pair> bisim = pr.getBisimilarPairs();

        assertTrue(bisim.isEmpty());
    }

    @Test
    void testSuccessorMismatch() {
        Node a = new Node("A").addPropositions('p');
        Node b = new Node("B").addPropositions('p');
        Node c = new Node("C").addPropositions('p');

        a.addOutgoingNodes(c);
        // b hat keinen Nachfolger → nicht bisimilar zu a

        TransitionGraph g = new TransitionGraph(a, b, c);

        List<Pair> bisim = g.getPartitionRefinement().getBisimilarPairs();

        assertFalse(bisim.stream().anyMatch(p -> p.getU().equals(a) && p.getV().equals(b)));
    }
}
