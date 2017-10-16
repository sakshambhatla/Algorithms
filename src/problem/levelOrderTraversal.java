package problem;

import java.util.ArrayDeque;

public class levelOrderTraversal {
	
	static class Node {
		int val;
		Node left;
		Node right;
		Node(int val) {
			this.val = val;
		}
	}
	
	static Node root;
	
	static void insertBST(int val) {
		if (root == null) {
			root = new Node(val);
			return;
		}
		
		if (root.val < val) {
			root.right = insertBSTRecurse(root.right, val);
		} else {
			root.left = insertBSTRecurse(root.left, val);
		}
	}
	
	static Node insertBSTRecurse(Node n, int val) {
		if (n == null) {
			n = new Node(val);
			return n;
		}
		
		if (n.val < val) {
			n.right = insertBSTRecurse(n.right, val);
		} else {
			n.left = insertBSTRecurse(n.left, val);
		}
		
		return n;
	}
	
	static void inOrder(Node n) {
		if (n == null) {
			return;
		}
		
		inOrder(n.left);
		System.out.println(n.val);
		inOrder(n.right);
		
	}

	static void printSpiral() {
		ArrayDeque<Node> st = new ArrayDeque<Node>();
		ArrayDeque<Node> st2 = new ArrayDeque<Node>();
		
		if (root == null) {
			return;
		}
		
		st.push(root);
		
		while (true) {
			while (!st2.isEmpty()) {
				Node curr = st2.pop();
				System.out.println("Removed " + curr.val);
				if (curr.left != null)
					st.push(curr.left);
				if (curr.right != null)
					st.push(curr.right);
				//System.out.println(curr.val);
			}
			
			while (!st.isEmpty()) {
				Node curr = st.pop();
				System.out.println("Popped " + curr.val);
				if (curr.right != null)
					st2.push(curr.right);
				if (curr.left != null)
					st2.push(curr.left);
				//System.out.println(curr.val);
			}
			
			if (st.isEmpty() && st2.isEmpty()) {
				break;
			}
		}
		
	}
	
	
	static class MaxMin {
		int max;
		int min;
		MaxMin() {}
	}
	static private MaxMin heightDifferenceRecurse(Node n) {
		if (n == null) {
			return new MaxMin();
		}
		
		MaxMin m1= heightDifferenceRecurse(n.left);
		MaxMin m2 = heightDifferenceRecurse(n.right);
		MaxMin ans = new MaxMin();
		
		if (m1.min < m2.min) {
			ans.min = m1.min + 1;
		} else {
			ans.min = m2.min + 1;
		}
		
		if (m1.max > m2.max) {
			ans.max = m1.max + 1;
		} else {
			ans.max = m2.max + 1;
		}
		
		return ans;
		
	}
	
	static public void heightDifference() {
		MaxMin temp = heightDifferenceRecurse(root);
		System.out.println("Height difference is " +  (temp.max - temp.min));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		insertBST(20);
		insertBST(10);
		insertBST(5);
		insertBST(1);
		insertBST(15);
		insertBST(2);
		//insertBST(12);
		//insertBST(20);
		//insertBST(25);
		//inOrder(root);
		System.out.println("-------------");
		//printSpiral();
		heightDifference();

	}

}
