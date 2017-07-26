package datastructure;

public class BSTRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(10);
		bst.insert(20);
		bst.insert(5);
		bst.insert(30);
		bst.insert(25);
		bst.insert(7);
		bst.preOrder();
		System.out.println();
		bst.inOrder();
		System.out.println();
		bst.postOrder();

	}

}
