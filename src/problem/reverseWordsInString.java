package problem;

import java.util.ArrayList;
import java.util.LinkedList;

public class reverseWordsInString {
    static LinkedList<ArrayList<Character>> q1;
    static LinkedList<ArrayList<Character>> q2;
    static char []arr;
	static int lStart, lEnd, rStart, rEnd;
    
    static String reverseWords(String str) throws InterruptedException {
    	ArrayList<Character> leftWord;
    	ArrayList<Character> rightWord;
    	int i;
    	arr = str.toCharArray();
    	lStart = lEnd = 0;
    	rStart = rEnd = str.length() - 1;
    	q1 = new LinkedList<ArrayList<Character>>();
    	q2 = new LinkedList<ArrayList<Character>>();

    	while (lEnd < rEnd) {
    		if (addWordLeft() == false) {
				break;
			}
    		if (addWordRight() == false) {
    			break;
    		}
    		
    		while (lEnd - lStart < q2.getLast().size() + 1) {
    			if (addWordLeft() == false) {
    				break;
    			}
    		}
    		
    		while (rStart - rEnd < q1.getLast().size() + 1) {
    			if (addWordRight() == false) {
    				break;
    			}
    		}
    		
    		leftWord = removeWord(q1);
    		//System.out.println("Left word to swap is " + leftWord.toString());
    		rightWord = removeWord(q2);
    		
    		//System.out.println("Right word to swap is " + rightWord.toString());
    		
    		for (i=0; i<rightWord.size(); i++) {
    			arr[lStart + rightWord.size() - i - 1] = rightWord.get(i);
    		}
    		arr[lStart + rightWord.size()] = ' ';
    		lStart = lStart + rightWord.size() + 1;
    		
    		for (i=0; i<leftWord.size(); i++) {
    			arr[rStart - leftWord.size() + i + 1] = leftWord.get(i);
    		}
    		arr[rStart - leftWord.size()] = ' ';
    		rStart = rStart - leftWord.size() - 1;
    		
    	}
    	
    	while (!q2.isEmpty() && lStart < lEnd) {
    		rightWord = removeWord(q2);
    		//System.out.println("Right word to swap is " + rightWord.toString());
    		Thread.sleep(100);
    		for (i=0; i<rightWord.size(); i++) {
    			arr[lStart + rightWord.size() - i - 1] = rightWord.get(i);
    		}
    		arr[lStart + rightWord.size()] = ' ';
    		lStart = lStart + rightWord.size() + 1;
    		
    		
    	}
    	
    	while (!q1.isEmpty()  && rStart > rEnd) {
    		leftWord = removeWord(q1);
    		//System.out.println("Left word to swap is " + leftWord.toString());
    		Thread.sleep(100);
    		for (i=0; i<leftWord.size(); i++) {
    			arr[rStart - leftWord.size() + i + 1] = leftWord.get(i);
    		}
    		arr[rStart - leftWord.size()] = ' ';
    		rStart = rStart - leftWord.size() - 1;
    		
    	}
    	
    	return new String(arr);
    	
    }

    static boolean addWordLeft() {
    	ArrayList<Character> temp = new ArrayList<Character>();
    	int i, flag = 0;
    	for (i=0; (lEnd+i) < arr.length; i++) {
    		if (arr[lEnd+i] == ' ') {
    			flag = 1;
    			break;
    		}
    		temp.add(arr[lEnd+i]);
    	}
    	if (flag == 0) {
    		return false;
    	}
    	q1.addFirst(temp);
    	//System.out.print("temp length is " + temp.size());
    	lEnd += temp.size() + 1;
    	//System.out.println("Added " + temp.toString() + ". lEnd is " + lEnd);
    	return true;
    }
    
    static boolean addWordRight() {
    	ArrayList<Character> temp = new ArrayList<Character>();
    	int i, flag = 0;
    	for (i=0; (rEnd-i) >= 0; i++) {
    		if (arr[rEnd-i] == ' ') {
    			flag = 1;
    			break;
    		}
    		temp.add(arr[rEnd-i]);
    	}
    	if (flag == 0) {
    		return false;
    	}
    	q2.addFirst(temp);
    	rEnd = rEnd - temp.size() - 1;
    	//System.out.println("Added " + temp.toString() + ". rEnd is " + rEnd);
    	return true;
    }
    
    static ArrayList<Character> removeWord(LinkedList<ArrayList<Character>> q) {
    	return(q.removeLast());
    }
    
	public static void main(String[] args) throws InterruptedException {
		String str = "This is a string with several words";
		String ans = reverseWords(str);
		System.out.println("Reversed words in string:\n" + ans);

	}
}