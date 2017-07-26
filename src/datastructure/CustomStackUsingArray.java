package datastructure;

import java.util.Iterator;

public class CustomStackUsingArray<Type> implements CustomList<Type>, Iterable<Type> {
	
	Type []arr;
	int head;
	
	public Iterator<Type> iterator() {
		return (new ListIterator());
	}
	
	private class ListIterator implements Iterator<Type>{
		int n;
		
		ListIterator() {
			n = head;
		}
		
		@Override
		public boolean hasNext() {
			return (n >= 1);
		}

		@Override
		public Type next() {
			if (n >= 1) {
				return (arr[n--]);
			} else {
				return null;
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	CustomStackUsingArray() {
		arr = (Type[]) new Object[2];
	}
	
	@SuppressWarnings("unchecked")
	CustomStackUsingArray(int capacity) {
		arr = (Type[]) new Object[capacity];
	}
	
	private void changeCapacity(int capacity) {
		@SuppressWarnings("unchecked")
		Type []temp = (Type[]) new Object[capacity];
		for (int i=0; i<arr.length; i++) {
			temp[i] = arr[i];
		}
		
		arr = temp;
	}
	
	public void push(Type val) {
		head++;
		
		if(head >= arr.length*3/4) {
			changeCapacity(arr.length*2);
		}
		
		arr[head] = val;
	}
	
	public Type pop() {
		if (head == 0) {
			return null;
		}
		
		return (arr[head--]);
	}
	
	public boolean isEmpty() {
		return (head == 0);
	}

	public void print() {
		for (int i=head; i>=1; i--) {
			System.out.println(arr[i]);
		}
	}
	
	public int size() {
		return (head);
	}
}
