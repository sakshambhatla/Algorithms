package problem;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AllPermutations {
	
	static Set<String> allPerms;
	
	static Set<String> getAllPermutations(String str) {
		allPerms = new HashSet<String>();
		getAllPermutationsChild(str, 0);
		return allPerms;
		
	}
	
	static void getAllPermutationsChild(String str, int i) {
		System.out.println("str len is " + str.length());
		if (str.length() >= (i+1)) {
			String s1, s2;
			System.out.println("inside " + i);
			String done = str.substring(0, i);
			String curr = str.substring(i, i+1);
			System.out.println("done is " + done + " and curr is " + curr);
			s1 = done + curr;
			s2 = curr + done;
			if (str.length() >= i+2) {
				String remain = str.substring(i+1, str.length());
				s1 = s1 + remain;
				s2 = s2 + remain;
			}
			System.out.println("s1 is " + s1);
			System.out.println("s2 is " + s2);
			allPerms.add(s1);
			allPerms.add(s2);
			getAllPermutationsChild(s1, i+1);
			getAllPermutationsChild(s2, i+1);
		}
	}

	public static void main(String[] args) {
		String str = "abc";
		Set<String> permutations = getAllPermutations(str);
		
		Iterator<String> iter = permutations.iterator();
		
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

	}

}
