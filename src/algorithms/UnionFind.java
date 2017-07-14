package algorithms;

abstract class UnionFind {
	int []union;
	UnionFind(int n) {
		union = new int[n];
		for (int i=0; i<n; i++) {
			union[i]=i;
		}
	}
	abstract void union (int a, int b);
	abstract boolean find(int a, int b);
	abstract boolean connectedComponents();
}

class regularUnionFind extends UnionFind {
	/*
	 * Initialize the union so that everyone has a different group ID. Best to assign index value
	 * Time Complexity: O(n)
	 */
	/*regularUnionFind(int n) {
		union = new int[n];
		for (int i=0; i<n; i++) {
			union[i]=i;
		}
	}*/
	
	regularUnionFind(int n) {
		super(n);
	}

	/*
	 * Basic implementation of union.
	 * Time Complexity: O(n)
	 */
	public void union(int a, int b) {
		/*
		 * IMPORTANT: Store group ID before comparing it in each iteration, because it may change
		 */
		int group1 = union[a];
		for (int i=0; i<union.length; i++) {
			if (union[i] == group1) {			
				union[i] = union[b];
			}
			//System.out.print("--"+union[i]+"--");
		}
		//System.out.println();
	}
	
	/*
	 * Check when groups of all members is the same. They're all in one set then.
	 * Time Complexity: O(n)
	 */
	public boolean connectedComponents() {
		int i;
		for (i=0; i<union.length-1; i++) {
			if (union[i] != union[i+1]) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean find(int a, int b) {
		if (union[a] == union[b]) {
			return true;
		}
		
		return false;
	}
}

class quickUnionFind extends UnionFind {

	quickUnionFind(int n) {
		super(n);
	}

	public void union(int a, int b) {
		
	}

	public boolean isUnionComplete() {
		return false;
	}

	public boolean find(int a, int b) {
		return false;
	}

	public boolean connectedComponents() {
		return false;
	}
	
}