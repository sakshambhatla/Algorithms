package datastructure;

import java.util.Iterator;

public class CustomStackUsingLinkedList<Type> implements CustomList<Type> {
	
	int n;
	Node head;
	
	class Node {
		Type val;
		Node next;
	}
	
	CustomStackUsingLinkedList() {
		n = 0;
	}
	
	public Iterator<Type> iterator() {
		return (new ListIterator());
	}
	
	private class ListIterator implements Iterator<Type>{
		Node n;
		
		ListIterator() {
			n = head;
		}
		
		@Override
		public boolean hasNext() {
			return (n != null);
		}

		@Override
		public Type next() {
			Type t = n.val;
			n = n.next;
			return (t);
		}
	}
	
	
	public void push(Type val) {
		Node temp = new Node();
		temp.val = val;
		temp.next = head;
		head = temp;
		n++;
	}
	
	public Type pop() {
		if (n == 0) {
			return null;
		}
		
		Type temp = head.val;
		head = head.next;
		n--;
		return temp;
	}
	
	public boolean isEmpty() {
		return (head == null);
	}

	public void print() {
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.val);
			temp = temp.next;
		}
	}
	
	public int size() {
		return (n);
	}
}
