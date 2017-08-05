package datastructure;

import java.util.HashSet;

public class ColorGraphRun {

	public static void main(String[] args) {
		Graph graph = new Graph(4);
		graph.initNode(0, "S");
		graph.initNode(1, "A");
		graph.initNode(2, "M");
		graph.initNode(3, "X");
		graph.setNeighborUndirected(0, 1);
		graph.setNeighborUndirected(0, 3);
		graph.setNeighborUndirected(1, 2);
		graph.setNeighborUndirected(1, 3);
		
		HashSet<Integer> neighbors = graph.getAllNeighbors(0);
		for (Integer neighbor : neighbors) {
			System.out.println("S's neighbor is " + graph.getName(neighbor));
		}

	}

}
