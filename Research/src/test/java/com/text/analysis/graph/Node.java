//package com.text.analysis.graph;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Node {
//
//	private int id;
//    private List<Edge> neighbours = new ArrayList<Edge>();
//    public int getNodeId() {
//        return this.id;
//    }
//    public void addNeighbour(Edge e) {
//        if(this.neighbours.contains(e)) {
//            System.out.println("This edge has already been used for this node.");
//        } else {
//            System.out.println("Successfully added " + e);
//            this.neighbours.add(e);
//        }
//    }
//    public void getNeighbours() {
//        System.out.println("List of all edges that node " + this.id +" has: ");
//        System.out.println("=================================");
//        for (int i = 0; i < this.neighbours.size(); i++ ){
//            System.out.println("ID of Edge: " + neighbours.get(i).getId() + "\nID of the first node: " + neighbours.get(i).getIdOfStartNode() + 
//            "\nID of the second node: " + neighbours.get(i).getIdOfEndNode());
//            System.out.println();
//        }
//        System.out.println(neighbours);
//    }
//    public Node(int id) {
//        this.id = id;
//    }
//}
