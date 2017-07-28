/*
 * Find the second largest item in a BST
 */

package problem;

import datastructure.BinarySearchTree;
import datastructure.CustomStackUsingLinkedList;

public class SecondLargestBSTElement {
	static BinarySearchTree bst;

	public static void main(String[] args) {
		bst = new BinarySearchTree();
		bst.insert(10);
		bst.insert(30);
		bst.insert(5);
		bst.insert(25);
		bst.insert(40);
		bst.insert(50);
		bst.insert(45);
		System.out.println("Second largest element is " + SecondLargestElement());
	}

	static int SecondLargestElement() {
		CustomStackUsingLinkedList<Integer> st = new CustomStackUsingLinkedList<Integer>();
		
		BinarySearchTree.node n = bst.getRoot();
		
		if (n != null) {
			st.push(n.getVal());
		}
		
		/*
		 * Push the elements in the stack. First push left, then current, then right.
		 * Move to right and repeat until end is reached.
		 */
		while (n != null) {
			
			BinarySearchTree.node left = n.getLeft();
			if (left != null) {
				st.push(left.getVal());
			}
			
			st.push(n.getVal());
			
			BinarySearchTree.node right = n.getRight();
			if (right != null) {
				st.push(right.getVal());
			}
			
			n = n.getRight();
		}
		
		//Pop the largest element out of the stack
		st.pop();
		
		return st.pop();
	}
}
