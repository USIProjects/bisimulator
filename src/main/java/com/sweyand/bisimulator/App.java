package com.sweyand.bisimulator;

import java.nio.file.Path;
import java.util.List;

import com.sweyand.bisimulator.graph.*;
import com.sweyand.bisimulator.refinement.Pair;

public class App {
    public static void main(String[] args) throws Exception {
        TransitionGraph t1 = GraphExamples.t1();
        run(t1, "t1", "Slide 145");
        TransitionGraph t2 = GraphExamples.t2();
        run(t2, "t2", "Slide 127 (right)");
        TransitionGraph t3 = GraphExamples.t3();
        run(t3, "t3", "Exercise 5.1");
    }

    public static void run(TransitionGraph t, String name, String title) throws Exception {
        t.print();
        List<Pair> bisimilarPairs = t.getBisimilarRelation();

        GraphExporter.saveDotFile(t, Path.of(name + ".dot"), title);
        GraphExporter.generatePng(Path.of(name + ".dot"), Path.of(name + ".png"));

        System.out.println("\nBisimilar pairs:");
        for (Pair pair : bisimilarPairs) {
            System.out.println("(" + pair.getU().getLabel() + ", " + pair.getV().getLabel() + ")");
        }
    }

}
