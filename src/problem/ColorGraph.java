package problem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import datastructure.Graph;

/*
 * Given an undirected graph with maximum degree D, find a graph coloring using at most 
 * D+1 colors.
 * Gotchas:
 * Have a node for visited to prevent loops.
 */

public class ColorGraph {
	
	public static boolean ColorAll(Graph graph) throws InterruptedException {
		boolean []visited = new boolean[graph.size];
		
		LinkedList<Integer> Q = new LinkedList<Integer>();
		graph.setColor(0, 0);
		visited[0] = true;
		Q.add(0);
		
		while (!Q.isEmpty()) {
			HashSet<Integer> colorSet = new HashSet<Integer>();
			
			int curr = Q.removeFirst();
			System.out.println("Curr is " + curr);
			for (int i=0; i<graph.size+1; i++) {
				colorSet.add(i);
			}
			System.out.println("Curr is " + curr + " with color " + graph.getColor(curr));
			colorSet.remove(graph.getColor(curr));
			
			HashSet<Integer> Neighbors = graph.getAllNeighbors(curr);
			for (int neighbor : Neighbors) {
				if (neighbor == curr) {
					System.out.println("Loop detected at " + curr);
					return (false);
				}
				System.out.println("Neighbor is " + neighbor);
				if (visited[neighbor] == true) {
					System.out.println("Neighbor " + neighbor + " already visited");
					colorSet.remove(graph.getColor(neighbor));
				}
			}
			
			Iterator<Integer> iter = colorSet.iterator();
			for (int neighbor : Neighbors) {
				if (visited[neighbor] == false) {
					System.out.println("Neighbor " + neighbor + " NOT visited before" );
					visited[neighbor] = true;
					int next = (Integer) iter.next();
					System.out.println("next color is " + next);
					graph.setColor(neighbor, next);
					Q.addLast(neighbor);
				}
			}
			
		}
		return (true);
	}
	
	static void printColor(Graph graph) {
		boolean []visited = new boolean[graph.size];
		
		LinkedList<Integer> Q = new LinkedList<Integer>();
		Q.add(0);
		
		while (!Q.isEmpty()) {
			int curr = Q.removeFirst();
			visited[curr] = true;
			System.out.println("For " + curr + " with name " + graph.getName(curr) + " color is " + graph.getColor(curr));
			HashSet<Integer> Neighbors = graph.getAllNeighbors(curr);
			for (int neighbor : Neighbors) {
				if (visited[neighbor] == false) {
					Q.addLast(neighbor);
				}
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		Graph graph = new Graph();
		graph.initNode("A");
		graph.initNode("B");
		graph.initNode("C");
		graph.initNode("D");
		graph.initNode("E");
		graph.initNode("F");
		graph.initNode("G");
		graph.setNeighborUndirected(0, 1);
		graph.setNeighborUndirected(1, 2);
		graph.setNeighborUndirected(2, 3);
		graph.setNeighborUndirected(1, 3);
		graph.setNeighborUndirected(3, 4);
		graph.setNeighborUndirected(4, 5);
		graph.setNeighborUndirected(4, 6);
		graph.setNeighborUndirected(5, 6);
		graph.setNeighborUndirected(5, 0);
		
		if (ColorAll(graph) == true) {
			System.out.println("Successfully colored the graph");
		}
		
		printColor(graph);

	}

}
