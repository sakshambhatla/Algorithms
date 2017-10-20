package problem;

public class squareRoot {
	
	public static int squareRoot(int num) {
		
		if (num < 0) {
			System.out.println("invalid");
			return 0;
		} else if (num == 0) {
			return 0;
		} else if (num <= 3) {
			return 1;
		}
		
		int d1 = 2;
		int d2 = 2;

		while(true) {
			int temp = d2;
			d2 = num/d1;
			System.out.println("d1 is " + d1 + ". d2 is " + d2);
			
			if (temp == d2) {
				return Math.min(d1, d2);
			}
			
			d1 = (d1 + d2)/2;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(squareRoot(64));
		System.out.println(squareRoot(9));
		System.out.println(squareRoot(20));
		System.out.println(squareRoot(50));

	}

}
