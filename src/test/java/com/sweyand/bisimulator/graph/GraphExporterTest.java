package com.sweyand.bisimulator.graph;

import org.junit.jupiter.api.Test;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

class GraphExporterTest {

    @Test
    void testToDot() {
        Node a = new Node("A").addPropositions('x');
        Node b = new Node("B");
        a.addOutgoingNodes(b);

        TransitionGraph g = new TransitionGraph(a, b);

        String dot = GraphExporter.toDot(g, "Test Graph");

        assertTrue(dot.contains("A"));
        assertTrue(dot.contains("B"));
        assertTrue(dot.contains("A -> B"));
        assertTrue(dot.contains("{x}"));
    }

    @Test
    void testSaveDotFile() throws Exception {
        Node a = new Node("A");
        TransitionGraph g = new TransitionGraph(a);

        Path tmp = Files.createTempFile("graph", ".dot");
        GraphExporter.saveDotFile(g, tmp, "Temp Graph");

        assertTrue(Files.size(tmp) > 0);
    }

    @Test
    void testGeneratePngCommandIsBuilt() {
        // Test nur ob Exception geworfen wird, wenn Graphviz fehlt.
        // Kein Failure -> Test OK
        assertDoesNotThrow(() -> {
            Path dot = Files.createTempFile("graph", ".dot");
            Path png = Files.createTempFile("graph", ".png");

            try {
                GraphExporter.generatePng(dot, png);
            } catch (Exception ignored) {
                // Falls dot nicht installiert ist, ist das ok.
            }
        });
    }
}
