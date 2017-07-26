/*
 * Test Stack data structures implemented.
 */

package datastructure;

import java.util.Iterator;

public class StackRun {

	public static void main(String[] args) {
		CustomStackUsingLinkedList<Integer> myList = new CustomStackUsingLinkedList<Integer>();
		Iterator<Integer> stIter = myList.iterator();
		myList.push(20);
		myList.push(30);
		myList.push(40);
		while (stIter.hasNext()) {
			System.out.print(stIter.next() + ", ");
		}
		System.out.println();
		myList.print();
		System.out.println("Popped one element");
		myList.pop();
		myList.print();
		System.out.println("Popped one element");
		myList.pop();
		myList.print();
	}
}
