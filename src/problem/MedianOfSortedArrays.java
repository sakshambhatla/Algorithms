package problem;

public class MedianOfSortedArrays {

	public static void main(String[] args) {
		int [] array1 = {1,3,5,7,9};
		int [] array2 = {2, 11, 12};
		System.out.println("Median is " + findMedian(array1, array2, array1.length/2, array2.length/2));
	}
	
	static int findMedian(int []a1, int[] a2, int mid1, int mid2) {
		//int newMid1, newMid2;
		int ans;
		int interval;
		int aVal, bVal;
		
		System.out.println("mid1 " + a1[mid1] + " and mid2 " + a2[mid2]);
		
		if (mid1 < 0) {
			aVal = Integer.MIN_VALUE;
		} else if (mid1 > a1.length-1) {
			aVal = Integer.MAX_VALUE;
		} else {
			aVal = a1[mid1];
		}
		
		if (mid2 < 0) {
			bVal = Integer.MIN_VALUE;
		} else if (mid2 > a2.length-1) {
			bVal = Integer.MAX_VALUE;
		} else {
			bVal = a2[mid2];
		}
		
		
		
		if (a1[mid1] > a2[mid2 + 1]) {
			System.out.println("a1 mid > a2 mid+1");
			interval = (Math.min(mid1+1, a2.length - mid2))/2;
			ans = findMedian(a1, a2, mid1-interval, mid2+interval);
		} else if (a2[mid2] > a1[mid1 + 1]) {
			System.out.println("a2 mid: " + a2[mid2]  + "> a1 mid+1: " + a1[mid1 + 1] );
			interval = (Math.min(a1.length - mid1, mid2+1))/2;
			ans = findMedian(a1, a2, mid1+interval, mid2 - interval);
		} else {
			if ((a1.length + a2.length) % 2 == 0) {
				int max1 = Math.max(a1[mid1], a2[mid2]);
				int max2 = Math.min(Math.max(a1[mid1], a2[mid2-1]), Math.max(a2[mid2], a1[mid1-1]));
				ans = (max1 + max2)/2;
				System.out.println("ans is - " + ans + " with max1 " + max1 + " and max2 " + max2);
			} else {
				ans = Math.max(a1[mid1], a2[mid2]);
				System.out.println("ans is - " + ans + " with mid1 " + a1[mid1] + " and mid2 " + a2[mid2]);
			}
		}
		
		return ans;
	}

}
