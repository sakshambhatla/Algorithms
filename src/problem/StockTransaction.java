package problem;

import java.util.ArrayList;

public class StockTransaction {

	public static void main(String[] args) {
		
		ArrayList <Integer>testList = new ArrayList <Integer>();
		testList.add(2);
		testList.add(10);
		testList.add(4);
		testList.add(6);
		int ans = findMaxProfit(testList, 3);
		System.out.println("Answer is " + ans);

	}
	
	static public int findMaxProfit(ArrayList<Integer> prices, int sellCost) {
		if (prices == null || prices.size() == 1) {
			return 0;
		}
		
		if (prices.size() == 2) {
			if (prices.get(0) < prices.get(1) - sellCost) {
				return (prices.get(1) - sellCost);
			} else {
				return 0;
			}
		}
		
		ArrayList<Integer> locals = new ArrayList<Integer>();
		
		locals.add(prices.get(0));
		
		for (int i=1; i<prices.size()-1; i++) {		
			if (prices.get(i) < prices.get(i-1 ) && prices.get(i) < prices.get(i+1) ) {
				locals.add(prices.get(i));
			}
			if (prices.get(i) > prices.get(i-1 ) && prices.get(i) > prices.get(i+1) ) {
				locals.add(prices.get(i));
			}
		}
		
		locals.add(prices.get(prices.size()-1));
		
		return findMax(locals, sellCost);

	}
	
	static private int findMax(ArrayList<Integer> locals, int sellCost) {
		if (locals == null || locals.size() < 2) {
			return 0;
		}
		
		boolean reverse;
		int sum=0;
		ArrayList<Integer> diffs = new ArrayList<Integer>();
		if (locals.get(0) > locals.get(1)) {
			reverse = true;
		} else {
			reverse = false;
		}
		
		for (int i=0; i<locals.size()-1; i = i+2) {
			if (reverse == true) {
				reverse = false;
				continue;
			}
			
			//sum += locals.get(i+1) - locals.get(i);
			diffs.add(locals.get(i+1) - locals.get(i));
			
		}
		
		if (diffs.size()< 3) {
			
		}
		return sum;
	}

}
