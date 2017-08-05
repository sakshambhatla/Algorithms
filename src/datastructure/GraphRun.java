package datastructure;

import java.util.HashSet;

public class GraphRun {

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.initNode("S");
		graph.initNode("A");
		graph.initNode("M");
		graph.initNode("X");
		graph.setNeighborUndirected(0, 1);
		graph.setNeighborUndirected(0, 3);
		graph.setNeighborUndirected(1, 2);
		graph.setNeighborUndirected(1, 3);
		
		for (int i=0; i<4; i++) {
			System.out.println("Node is " + graph.getName(i));
		}
		
		HashSet<Integer> neighbors = graph.getAllNeighbors(0);
		for (Integer neighbor : neighbors) {
			System.out.println("S's neighbor is " + graph.getName(neighbor));
		}

	}

}
