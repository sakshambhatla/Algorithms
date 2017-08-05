package datastructure;

import java.util.HashSet;

public class Graph{
	public Node []node;
	
	public Graph(int n) {
		node = new Node[n];
	}
	
	public void initNode(int index, String name) {
		node[index] = new Node();
		setName(index, name);
	}
	
	public void setName(int index, String name) {
		node[index].name = name;
	}
	
	public String getName(int index) {
		return node[index].name;
	}
	
	public void setNeighborUndirected(int index1, int index2) {
		node[index1].setNeighbor(index2);
		node[index2].setNeighbor(index1);
	}
	
	public void setNeighborDirected(int first, int second) {
		node[first].setNeighbor(second);
	}
	
	public void setColor(int index, int val) {
		node[index].color = val;
	}
	
	public int getColor(int index) {
		return node[index].color;
	}
	
	public HashSet<Integer> getAllNeighbors(int index) {
		return node[index].neighbors;
	}
	
	public class Node{
		private String name;
		private HashSet<Integer> neighbors;
		private int color;
		
		Node() {
			neighbors = new HashSet<Integer>();
		}
		
		private void setNeighbor(int index) {
			neighbors.add(index);
		}
	}
}


