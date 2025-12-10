package com.sweyand.bisimulator.graph;

public class GraphExamples {
    public static TransitionGraph t1() {
        TransitionGraph t = new TransitionGraph();

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

        t.addNodes(v0, v1, v2, v3, v4);

        return t;
    }

    public static TransitionGraph t2() {
        TransitionGraph t = new TransitionGraph();

        // Init nodes
        Node v4 = new Node(4).addPropositions('p');
        Node v5 = new Node(5);
        Node v6 = new Node(6).addPropositions('q');
        Node v7 = new Node(7).addPropositions('q');
        Node v8 = new Node(8).addPropositions('r');

        // Add transitions
        v4.addOutgoingNodes(v5);
        v5.addOutgoingNodes(v6, v7, v8);
        v6.addOutgoingNodes(v4);
        v7.addOutgoingNodes(v4);
        v8.addOutgoingNodes(v4);

        t.addNodes(v4, v5, v6, v7, v8);

        return t;
    }

    public static TransitionGraph t3() {
        TransitionGraph t = new TransitionGraph();

        // Init nodes
        Node v0 = new Node("v0").addPropositions('p');
        Node v1 = new Node("v1").addPropositions('p');
        Node v2 = new Node("v2").addPropositions('p');
        Node v3 = new Node("v3").addPropositions('q');
        Node v4 = new Node("v4").addPropositions('p');
        Node v5 = new Node("v5").addPropositions('p');
        Node v6 = new Node("v6");
        // Add transitions
        v0.addOutgoingNodes(v1, v3, v4);
        v1.addOutgoingNodes(v2, v3);
        v2.addOutgoingNodes(v2, v3);
        v3.addOutgoingNodes(v0);
        v4.addOutgoingNodes(v5, v6);
        v5.addOutgoingNodes(v6);
        v6.addOutgoingNodes(v0);

        t.addNodes(v0, v1, v2, v3, v4, v5, v6);
        
        return t;
    }
}
