package com.sweyand.bisimulator.refinement;

import java.util.*;

import com.sweyand.bisimulator.graph.Node;
import com.sweyand.bisimulator.graph.TransitionGraph;

public class PartitionRefinement {

    private final TransitionGraph graph;

    private Map<String, Pair> pairMap;
    private List<Pair> pairList;

    public PartitionRefinement(TransitionGraph graph) {
        this.graph = graph;
    }

    /** Key für (a,b) unabhängig von Reihenfolge */
    private String key(Node a, Node b) {
        String la = a.getLabel();
        String lb = b.getLabel();
        return la.compareTo(lb) < 0 ? la + "|" + lb : lb + "|" + la;
    }

    /** Erzeugt alle eindeutigen Paare von Knoten */
    private List<Pair> computePairs() {
        List<Node> nodes = graph.getNodes();
        int n = nodes.size();

        pairMap = new HashMap<>(n * n / 2);
        List<Pair> result = new ArrayList<>(n * n / 2);

        for (int i = 0; i < n; i++) {
            Node u = nodes.get(i);
            for (int j = i + 1; j < n; j++) {
                Node v = nodes.get(j);

                Pair p = new Pair(u, v);

                // Markieren wenn Propositionen verschieden
                if (!u.getPropositions().equals(v.getPropositions())) {
                    p.mark();
                }

                pairMap.put(key(u, v), p);
                result.add(p);
            }
        }

        return result;
    }

    private boolean isMarked(Node a, Node b) {
        Pair p = pairMap.get(key(a, b));
        return p != null && p.isMarked();
    }

    /** Algorithmus für Partition Refinement nach der Vorlesung */
    public List<Pair> run() {
        if (pairList != null)
            return pairList;

        pairList = computePairs();

        boolean changed = true;

        while (changed) {
            changed = false;

            for (Pair pair : pairList) {

                if (pair.isMarked())
                    continue;

                Node u = pair.getU();
                Node v = pair.getV();

                List<Node> uOut = u.getOutgoingNodes();
                List<Node> vOut = v.getOutgoingNodes();

                // u → u' muss v → v' matchen
                boolean condition1 =
                        uOut.stream().anyMatch(
                                uSucc -> vOut.stream().allMatch(
                                        vSucc -> isMarked(uSucc, vSucc)));

                // v → v' muss u → u' matchen
                boolean condition2 =
                        vOut.stream().anyMatch(
                                vSucc -> uOut.stream().allMatch(
                                        uSucc -> isMarked(vSucc, uSucc)));

                if (condition1 || condition2) {
                    pair.mark();
                    changed = true;
                }
            }
        }

        return pairList;
    }

    /** Gibt unmarkierte (bisimilare) Paare zurück */
    public List<Pair> getBisimilarPairs() {
        run();
        List<Pair> result = new ArrayList<>();
        for (Pair p : pairList) {
            if (!p.isMarked())
                result.add(p);
        }
        return result;
    }

    /** Identität: (x,x) für alle x */
    public List<Pair> getIdentity() {
        List<Pair> identity = new ArrayList<>(graph.getNodes().size());
        for (Node node : graph.getNodes()) {
            identity.add(new Pair(node, node));
        }
        return identity;
    }
}
