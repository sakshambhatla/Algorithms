package algorithms;

public class StackRun {

	public static void main(String[] args) {
		CustomStackUsingArray<Integer> myList = new CustomStackUsingArray<Integer>();
		myList.push(10);
		myList.push(20);
		myList.push(30);
		myList.push(40);
		myList.print();
		System.out.println("Popped one element");
		myList.pop();
		myList.print();
		System.out.println("Popped one element");
		myList.pop();
		myList.print();
	}
}
