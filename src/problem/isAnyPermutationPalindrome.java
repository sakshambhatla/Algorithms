/*
 * Write an efficient function that checks whether any permutation of an input string is 
 * a palindrome.
 */

package problem;

import java.util.HashSet;

public class isAnyPermutationPalindrome {
	
	static boolean isPermutationPalindrome(String str) {
		
		HashSet<Character> hm = new HashSet<Character>();
		int i;
		
		/*
		 * For a palindrome, string must have a pair for each character.
		 */
		for (i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if (hm.contains(c)) {
				hm.remove(c);
			} else {
				hm.add(c);
			}
		}
		
		/*
		 * For odd length of string, one character won't be in a pair.
		 */
		if (str.length()%2 == 0 && hm.size() == 0) {
			return true;
		} else if (str.length()%2 == 1 && hm.size() == 1){
			return true;
		}
		
		return false;
		
	}

	public static void main(String[] args) {
		
		String str = "tests";
		if (isPermutationPalindrome(str) == true) {
			System.out.println("String " + str + " has a permutation that is a palindrome");
		} else {
			System.out.println("String " + str + " does NOT have any permutation that is a palindrome");
		}
	}

}
