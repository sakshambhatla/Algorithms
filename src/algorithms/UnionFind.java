/*
 * UnionFind-
 * Union: Assign components to be part of one group.
 * Find: Are the components part of the same group?
 * 
 * QuickFind- 
 * For union, set every single group member holding 1st ID to 2nd. O(n)
 * For find, just see if group ID's are same. O(1)
 * A different way to visualize this is to imagine this as a tree, in which during every union,
 * we traverse the array to ensure that each element of a group updates itself to point to the root
 * directly, making it flat, and thus making find O(1).
 * 
 * QuickUnion-
 * For union, do the lazy approach. Set group 1 to group 2. (Essentially, group 1 points to group 2
 * as its parent). O(1)
 * For find, traverse the array as a tree. Consider that each member points to its parent's group
 * ID. If roots of both elements is same, they're in the same group. O(n)
 * 
 * An alternative approach is to find root, and assign 1st group to 2nd group's root during union().
 * With this, union() time complexity can be O(n) worst case, but find() will be faster
 * (still O(n) find worst case when it consistently chooses to make the larger chain child of 
 * smaller chain )
 */

package algorithms;

abstract class UnionFind {
	int []union;
	
	/*
	 * Initialize the union so that everyone has a different group ID. Best to assign index value
	 * Time Complexity: O(n)
	 */
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

class quickFind extends UnionFind {

	quickFind(int n) {
		super(n);
	}

	/*
	 * Run through the array, replacing 1st groupID to 2nd.
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

/*
 * A faster method for UnionFind where every index points to the parent index instead of
 * 1 global group.  
 */
class quickUnion extends UnionFind {
	
	quickUnion(int n) {
		super(n);
	}

	/*
	 * Lazy approach - Just assign 1st group to 2nd. O(1) complexity.
	 */
	public void union(int a, int b) {
		union[a] = union[b];
	}

	public boolean isUnionComplete() {
		return false;
	}

	/*
	 * Keep traversing tree till you reach parent. If parents of a and b are same, they're in 
	 * same group.
	 * Also, if you encounter the other peer while traversing, you don't need to go all the way till
	 * the parent to confirm.
	 * Time Complexity: O(n)
	 */
	public boolean find(int a, int b) {
		int i = a;
		int j = b;
		while (union[i] != i) {
			i = union[i];
			if (i == b) {
				return true;
			}
		}
		
		while (union[j] != j) {
			j = union[j];
			if (j == a) {
				return true;
			}
		}
		
		if (i == j) {
			return true;
		}
		
		return false;
	}

	public boolean connectedComponents() {
		return false;
	}
	
}