package com.sweyand.bisimulator.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Node {

    private final String label;
    private final List<Character> propositions;
    private final List<Node> incomingNodes;
    private final List<Node> outgoingNodes;

    public Node(int label) {
        this(Integer.toString(label));
    }

    public Node(String label) {
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.propositions = new ArrayList<>();
        this.incomingNodes = new ArrayList<>();
        this.outgoingNodes = new ArrayList<>();
    }

    /** Copy */
    public Node(Node n) {
        this.label = n.label;
        this.propositions = new ArrayList<>(n.propositions);
        this.incomingNodes = new ArrayList<>(n.incomingNodes);
        this.outgoingNodes = new ArrayList<>(n.outgoingNodes);
    }

    public String getLabel() {
        return label;
    }

    public List<Character> getPropositions() {
        return Collections.unmodifiableList(propositions);
    }

    public List<Node> getIncomingNodes() {
        return Collections.unmodifiableList(incomingNodes);
    }

    public List<Node> getOutgoingNodes() {
        return Collections.unmodifiableList(outgoingNodes);
    }

    public Node addPropositions(char... props) {
        for (char p : props) {
            propositions.add(p);
        }
        return this;
    }

    public Node addOutgoingNodes(Node... nodes) {
        for (Node n : nodes) {
            outgoingNodes.add(n);
            n.incomingNodes.add(this);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node that = (Node) o;
        return label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public String toString() {
        return "Node(" + label + ")";
    }
}
