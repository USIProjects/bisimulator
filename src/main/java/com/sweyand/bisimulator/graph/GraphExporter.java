package com.sweyand.bisimulator.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GraphExporter {

    /**
     * Exportiert einen TransitionGraph im Graphviz-DOT-Format.
     *
     * @param graph TransitionGraph
     * @return DOT-String
     */
    public static String toDot(TransitionGraph graph, String title) {
        StringBuilder sb = new StringBuilder();

        sb.append("digraph G {\n");
        sb.append("  rankdir=UD;\n");
        sb.append("  node [shape=circle];\n\n");

        if (title != null && !title.isEmpty()) {
            sb.append("  label=\"").append(title).append("\";\n");
            sb.append("  labelloc=top;\n"); // platziert den Titel oben
            sb.append("  fontsize=20;\n"); // optional, Größe des Titels
        }

        for (Node node : graph.getNodes()) {

            String label = node.getLabel();
            String props = node.getPropositions().isEmpty()
                    ? "{}"
                    : "{" + node.getPropositions().stream()
                            .map(String::valueOf)
                            .reduce((a, b) -> a + "," + b)
                            .orElse("") + "}";

            sb.append("  ")
                    .append(label)
                    .append(" [label=\"")
                    .append(label)
                    .append("\n")
                    .append(props)
                    .append("\"];\n");

            for (Node out : node.getOutgoingNodes()) {
                sb.append("  ")
                        .append(label)
                        .append(" -> ")
                        .append(out.getLabel())
                        .append(";\n");
            }
        }

        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Speichert das DOT-File auf die Festplatte.
     */
    public static void saveDotFile(TransitionGraph graph, Path path, String title) throws IOException {
        String dot = toDot(graph, title);
        Files.writeString(path, dot);
    }

    /**
     * Erzeugt mit installiertem Graphviz (dot) eine PNG-Datei.
     *
     * @param dotPath   Pfad zur DOT-Datei
     * @param outputPng Pfad zur PNG-Datei
     */
    public static void generatePng(Path dotPath, Path outputPng) throws IOException, InterruptedException {
        Process p = new ProcessBuilder(
                "dot", "-Tpng", dotPath.toString(), "-o", outputPng.toString()).start();

        int exit = p.waitFor();
        if (exit != 0) {
            throw new RuntimeException("Graphviz dot failed with exit code " + exit);
        }
    }
}
