package com.sweyand.bisimulator.refinement;

import java.util.Objects;

import com.sweyand.bisimulator.graph.Node;

public class Pair {

    private final Node u;
    private final Node v;
    private boolean marked = false;

    public Pair(Node u, Node v) {
        this.u = Objects.requireNonNull(u);
        this.v = Objects.requireNonNull(v);
    }

    public Node getU() {
        return u;
    }

    public Node getV() {
        return v;
    }

    public void mark() {
        this.marked = true;
    }

    public boolean isMarked() {
        return marked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair other = (Pair) o;
        return u.equals(other.u) && v.equals(other.v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v);
    }

    @Override
    public String toString() {
        return "(" + u.getLabel() + ", " + v.getLabel() + ")"
                + (marked ? " [marked]" : "");
    }
}
