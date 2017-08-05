package datastructure;

import java.util.ArrayList;
import java.util.HashSet;

public class Graph{
	public ArrayList<Node> node;
	public int size;
	
	public Graph() {
		node = new ArrayList<Node>();
	}
	
	public void initNode(String name) {
		node.add(new Node());
		setName(size, name);
		size++;
	}
	
	public void setName(int index, String name) {
		node.get(index).name = name;
	}
	
	public String getName(int index) {
		return node.get(index).name;
	}
	
	public void setNeighborUndirected(int index1, int index2) {
		node.get(index1).setNeighbor(index2);
		node.get(index2).setNeighbor(index1);
	}
	
	public void setNeighborDirected(int first, int second) {
		node.get(first).setNeighbor(second);
	}
	
	public void setColor(int index, int val) {
		node.get(index).color = val;
	}
	
	public int getColor(int index) {
		return node.get(index).color;
	}
	
	public HashSet<Integer> getAllNeighbors(int index) {
		return node.get(index).neighbors;
	}
	
	public class Node{
		private String name;
		private HashSet<Integer> neighbors;
		private int color;
		
		public Node() {
			neighbors = new HashSet<Integer>();
		}
		
		private void setNeighbor(int index) {
			neighbors.add(index);
		}
	}
}


