package com.sweyand.bisimulator.graph;

import java.util.*;

import com.sweyand.bisimulator.refinement.Pair;
import com.sweyand.bisimulator.refinement.PartitionRefinement;

public class TransitionGraph {

    private final List<Node> nodes;
    private final Set<String> labels;
    private final PartitionRefinement partitionRefinement;

    public TransitionGraph() {
        this.nodes = new ArrayList<>();
        this.labels = new HashSet<>();
        this.partitionRefinement = new PartitionRefinement(this);
    }

    public TransitionGraph(Node... nodes) {
        this.nodes = new ArrayList<>();
        this.labels = new HashSet<>();
        addNodes(nodes);
        this.partitionRefinement = new PartitionRefinement(this);
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public void addNodes(Node... newNodes) {
        for (Node n : newNodes) {
            addNode(n);
        }
    }

    public void addNode(Node node) {
        String label = node.getLabel();

        if (!labels.add(label)) {
            throw new IllegalArgumentException(
                    "Node with label '" + label + "' already exists in the graph.");
        }

        nodes.add(node);
    }

    public void print() {
        for (Node node : this.nodes) {
            System.out.print("Node " + node.getLabel() +
                    " (props=" + node.getPropositions() + ") → [");

            List<Node> outs = node.getOutgoingNodes();
            for (int i = 0; i < outs.size(); i++) {
                System.out.print(outs.get(i).getLabel());
                if (i < outs.size() - 1) System.out.print(", ");
            }

            System.out.println("]");
        }
    }

    /**
     * Liefert die vollständige Bisimulationsrelation:
     *  - unmarkierte Paare
     *  - symmetrische Paare
     *  - Identitätspaare
     */
    public List<Pair> getBisimilarRelation() {

        List<Pair> base = partitionRefinement.getBisimilarPairs();
        List<Pair> identity = partitionRefinement.getIdentity();

        List<Pair> result = new ArrayList<>(base.size() * 2 + nodes.size());

        for (Pair p : base) {
            if (!p.isMarked()) {
                result.add(p);
                result.add(new Pair(p.getV(), p.getU())); // symmetrisch
            }
        }

        result.addAll(identity);
        return result;
    }

    public PartitionRefinement getPartitionRefinement() {
        return this.partitionRefinement;
    }
}
