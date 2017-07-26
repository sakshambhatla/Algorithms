package datastructure;

public interface CustomList<Type> {
	void push(Type val);
	Type pop();
	boolean isEmpty();
	void print();
	int size();
}

