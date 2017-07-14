package algorithms;

import java.util.*;

/* Union-Find algorithm
 * https://docs.google.com/document/d/1KJbxRsmcgv4XEJkP9c844phTS3U2_dmq46VfIP2g0CQ/edit# 
 * https://www.safaribooksonline.com/library/view/algorithms-24-part-lecture/9780134384528/ALGS_algs4partI_L1-S2.html
 *
 * Important Lessons:
 * Union size will be equal to number of components. Since the component itself is represented by
 * the INDEX of the union, note that when specifying which components connect, it can only range
 * from 0 to n-1. In more complex representations, this index will be mapped to an ID (like
 * friend's name) using a Hash Map.
 * 
 * Number of connections can be anything. Note that number of unique connections = 
 * number of ways to pick N components 2 times (combination, order of picking doesn't matter) =
 * n!/((n-2)!*2) = n*(n-1)/2
 *
 */

public class UnionFindRun {

	public static void main (String[] args)
	{
		System.out.println("Enter number of components");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		quickUnion uf = new quickUnion(n);
		
		System.out.println("Enter number of connections");
		int m = sc.nextInt();
		System.out.println("Enter the 2 numbers (seperated by a space) " + m + " times");
		System.out.println("The numbers can only be from 0 to " + (n-1));
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (!uf.find(a, b)) {
				uf.union(a, b);
			}
		}
		
		System.out.println("Enter the 2 numbers to compare (seperated by a space)- ");
		int a = sc.nextInt();
		int b = sc.nextInt();
		if (uf.find(a,b)) {
			System.out.println(a + " and " + b + " are connected");
		} else {
			System.out.println(a + " and " + b + " are NOT connected");
		}
	}
}