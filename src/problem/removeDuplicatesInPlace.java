package problem;

import java.util.Arrays;

/*
 * Remove duplicates in an array In Place, without any extra memory.
 * 
 * Brute Force: For every element, find any duplicates, and shift array to remove it each time it's found.
 * Better Method: Sort array (O(nlogn) time) and then traverse through array once, shifting array when we reach end of duplicates.
 */

public class removeDuplicatesInPlace {
	static int []arr;
	
	/*
	 * Remove duplicates. Returns length of final array.
	 */
	private static int removeDup() {
		Arrays.sort(arr);
		int range = 0;
		/* This may appear to be O(n*n), but it's O(n) */
		for (int i=0; i<arr.length - range; i++) {
			int j;
			for (j=i+1; j<arr.length && arr[i] == arr[j]; j++) {
				continue;
			}
			resizeArr(i+1, j);
			range += j - i - 1;
		}
		return (arr.length - range);
	}
	
	private static void resizeArr(int start, int end) {
		for (int i=end; i<arr.length; i++) {
			arr[i-end+start] = arr[i];
		}
	}
	
	public static void main(String[] args) {
		arr = new int[]{1,2,3,3,3,3,3,4,5,6,7,8,2,4,5,2,3,4};
		int len = removeDup();
		
		for (int i=len; i<arr.length; i++) {
			arr[i] = 0;
		}
		
		System.out.println("array is ");
		for (int i=0; i<len; i++) {
			System.out.print(" " + arr[i]);
		}
	}

}
