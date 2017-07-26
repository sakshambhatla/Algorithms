package datastructure;

public class BinarySearchTree {
	
	class node {
		int val;
		node left;
		node right;
	}
	
	node root;
	
	public void insert(int value) {
		if (root == null) {
			root = new node();
			root.val = value;
			return;
		}
		
		insertRecurse(root, value);
	}
	
	private void insertRecurse(node n, int value) {
		if (n == null) {
			n = new node();
			n.val = value;
			return;
		}
		
		if (n.val > value) {
			if (n.left == null) {
				n.left = new node();
				n.left.val = value;
			} else {
				insertRecurse(n.left, value);
			}
		} else if (n.val < value) {
			if (n.right == null) {
				n.right = new node();
				n.right.val = value;
			} else {
				insertRecurse(n.right, value);
			}
		}
	}

	public void preOrder() {
		preOrderRecurse(root);
	}
	
	public void inOrder() {
		inOrderRecurse(root);
	}
	
	void postOrder() {
		postOrderRecurse(root);
	}
	
	private void preOrderRecurse(node n) {
		if (n == null) {
			System.out.print("- ");
			return;
		}
		
		System.out.print(n.val + " ");
		preOrderRecurse(n.left);
		preOrderRecurse(n.right);
	}
	
	private void inOrderRecurse(node n) {
		if (n == null) {
			System.out.print("- ");
			return;
		}
		
		inOrderRecurse(n.left);
		System.out.print(n.val + " ");
		inOrderRecurse(n.right);
	}
	
	private void postOrderRecurse(node n) {
		if (n == null) {
			System.out.print("- ");
			return;
		}
		
		postOrderRecurse(n.left);
		postOrderRecurse(n.right);
		System.out.print(n.val + " ");
	}
}
