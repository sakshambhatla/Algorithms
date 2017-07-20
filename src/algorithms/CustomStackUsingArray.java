package algorithms;

public class CustomStackUsingArray<Type> implements CustomList<Type> {
	
	Type []arr;
	int head;
	
	@SuppressWarnings("unchecked")
	CustomStackUsingArray() {
		arr = (Type[]) new Object[10];
	}
	
	@SuppressWarnings("unchecked")
	CustomStackUsingArray(int capacity) {
		arr = (Type[]) new Object[capacity];
	}
	
	public void push(Type val) {
		arr[++head] = val;
	}
	
	public Type pop() {
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
