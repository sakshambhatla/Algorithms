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
 * smaller chain ). This alternative approach can be used with some optimizations to get even better
 * runtime complexity, as mentioned below.
 * 
 * Weighted QuickUnion
 * We introduce a new function to find root.
 * We introduce a new array of size n to store weights.
 * For union, we always assign the smaller tree to the larger tree by checking weights. This makes
 * time complexity log(n) since at any moment in the union, the two trees joining will be 
 * atleast double the size of the individual trees. So the max times we'd need to do a union 
 * would be log(n). We also update the root in the weight array to reflect depth of tree.
 * For find, no change from before.
 * 
 * Weighted QuickUnion with Path Compression
 * While finding root, we also compress the path. For every node we visit, we make it point to 
 * its grandparent instead of parent. Path length halves.
 * 
 * Weighted QuickUnion with Extreme Path Compression
 * While finding root, we also compress the path. For every node we visit, we make it point to 
 * the root.
 */

package algorithms;

import java.util.Arrays;

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
		return (union[a] == union[b]);
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

/*
 * An even faster method for UnionFind where every index points to the parent index instead of
 * 1 global group, and during a union, we always join the smaller tree to the root of larger tree.
 */
class weightedQuickUnion extends UnionFind {
	int []weightArr;
	
	weightedQuickUnion(int n) {
		super(n);
		weightArr = new int[n];
		Arrays.fill(weightArr, 1);
	}
	
	int getRoot(int i) {
		while (union[i] != i) {
			i = union[i];
		}
		
		return i;
	}
	
	/*
	 * Tree grows logarithmically, since the smaller tree joins the root of the larger tree.
	 */
	public void union(int a, int b) {
		int aRoot = getRoot(a);
		int bRoot = getRoot(b);
		if (weightArr[aRoot] >= weightArr[bRoot]) {
			union[b] = aRoot;
			weightArr[aRoot] += weightArr[bRoot];
		} else {
			union[a] = bRoot;
			weightArr[bRoot] += weightArr[aRoot];
		}
	}

	boolean find(int a, int b) {
		return (getRoot(a) == getRoot(b));
	}

	boolean connectedComponents() {
		return false;
	}
}

/*
 * Even faster with Path Compression. Time Complexity: O(lg*n)
 */
class weightedQuickUnionPC extends weightedQuickUnion {
	
	weightedQuickUnionPC(int n) {
		super(n);
	}

	int getRoot(int i) {
		while (union[i] != i) {
			union[i] = union[union[i]];
			i = union[i];
		}
		
		return i;
	}
}

/*
 * Even faster with Extreme Path Compression. Time Complexity: O(lg*n)
 */
class weightedQuickUnionEPC extends weightedQuickUnion {

	weightedQuickUnionEPC(int n) {
		super(n);
	}
	
	int getRoot(int i) {
		while (union[i] != i) {
			int j = union[i];
			
			while (union[j] != j) {
				j = union[j];
			}

			i = union[i];
			union[i] = j;
		}
		
		return i;
	}
}