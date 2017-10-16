package problem;

public class LinkedListInsertionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}


class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; next = null; }
}
class Solution {
	ListNode insertionSortList(ListNode a) {
	    if (a == null || a.next == null) {
	        return a;
	    }
	    
	    ListNode nPrev = a;
	    ListNode n;

        for (n = a.next ; n!= null;) {
            
            ListNode prevSorted = null;
            ListNode sorted;
            
            for (sorted = a; sorted != n && sorted.val < n.val; sorted = sorted.next) {
                prevSorted = sorted;
            }
            
            if (sorted != n) {
                ListNode temp = new ListNode(n.val);
                if (prevSorted != null) {
                    temp.next = prevSorted.next;
                    prevSorted.next = temp;
                } else {
                    temp.next = a;
                    a = temp;
                }
                
                nPrev.next = n.next;
                n= n.next;
            } else {
                nPrev = n;
                n= n.next;
            }
            
        }

	    return a;
	}
}

