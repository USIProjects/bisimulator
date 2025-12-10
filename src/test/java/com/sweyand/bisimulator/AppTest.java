package com.sweyand.bisimulator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.sweyand.bisimulator.graph.Node;
import com.sweyand.bisimulator.graph.TransitionGraph;
import com.sweyand.bisimulator.refinement.Pair;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Test bisimulation relation on sample transition graph.
     */
    @Test
    public void testBisimulation() {
        // Init nodes
        Node v0 = new Node(0).addPropositions('p');
        Node v1 = new Node(1).addPropositions('q');
        Node v2 = new Node(2).addPropositions('q');
        Node v3 = new Node(3).addPropositions('p');
        Node v4 = new Node(4).addPropositions('p');

        // Add transitions
        v0.addOutgoingNodes(v1, v2);
        v1.addOutgoingNodes(v3, v4);
        v2.addOutgoingNodes(v4);
        v3.addOutgoingNodes(v0);
        v4.addOutgoingNodes(v0);

        TransitionGraph t = new TransitionGraph(v0, v1, v2, v3, v4);

        // Check bisimilar pairs size
        List<Pair> bisimilarRelation = t.getBisimilarRelation();
        assertEquals(9, bisimilarRelation.size());
    }
}
