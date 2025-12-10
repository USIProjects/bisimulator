package com.sweyand.bisimulator;

import org.junit.jupiter.api.Test;

import com.sweyand.bisimulator.graph.*;
import com.sweyand.bisimulator.refinement.*;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GraphExamplesTest {

    // ---------------------------------------------
    // t1()
    // ---------------------------------------------
    @Test
    void testT1_BisimilarPairCount() {
        TransitionGraph g = GraphExamples.t1();
        List<Pair> bisim = g.getPartitionRefinement().getBisimilarPairs();

        assertEquals(2, bisim.size(),
                "Unexpected number of bisimilar pairs for t1()");
    }

    @Test
    void testT1_FullRelationCount() {
        TransitionGraph g = GraphExamples.t1();
        List<Pair> rel = g.getBisimilarRelation();

        assertEquals(9, rel.size(),
                "Unexpected number of full bisimulation relation pairs for t1()");
    }


    // ---------------------------------------------
    // t2()
    // ---------------------------------------------
    @Test
    void testT2_BisimilarPairCount() {
        TransitionGraph g = GraphExamples.t2();
        List<Pair> bisim = g.getPartitionRefinement().getBisimilarPairs();

        assertEquals(1, bisim.size(),
                "Unexpected number of bisimilar pairs for t2()");
    }

    @Test
    void testT2_FullRelationCount() {
        TransitionGraph g = GraphExamples.t2();
        List<Pair> rel = g.getBisimilarRelation();

        assertEquals(7, rel.size(),
                "Unexpected number of full bisimulation relation pairs for t2()");
    }


    // ---------------------------------------------
    // t3()
    // ---------------------------------------------
    @Test
    void testT3_BisimilarPairCount() {
        TransitionGraph g = GraphExamples.t3();
        List<Pair> bisim = g.getPartitionRefinement().getBisimilarPairs();

        assertEquals(1, bisim.size(),
                "Unexpected number of bisimilar pairs for t3()");
    }

    @Test
    void testT3_FullRelationCount() {
        TransitionGraph g = GraphExamples.t3();
        List<Pair> rel = g.getBisimilarRelation();

        assertEquals(9, rel.size(),
                "Unexpected number of full bisimulation relation pairs for t3()");
    }
}
