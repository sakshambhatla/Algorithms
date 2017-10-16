package problem;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MediaAlphaCoding {
	
	static char[] precede = {'^', '/', '*', '+', '-'};
	
	public static void main(String[] args) {
		//String str = "3+4*1";
		//String str2 = "()3*(4+ 5)-(6+ 8)";
		//System.out.println("Input is " + str2);
		
		Scanner scanner = new Scanner(System.in);
		String raw_input = scanner.nextLine().trim();
		
		String converted = infixToPostfix(raw_input);
		
		if (converted != null) {
			String ans = postfixToInfix(converted);
			System.out.println("Expression without unecessary parenthesis: " + ans);
		} else {
			System.out.println("Unexpected error in infix to postfix conversion");
		}
		
		scanner.close();
	}

	private static String postfixToInfix(String str) {
		ArrayDeque<Expression> ad = new ArrayDeque<Expression>();
		
		for (int i=0; i<str.length(); i++) {
			char curr = str.charAt(i);
			if (Character.isDigit(curr) || Character.isLetter(curr)) {
				ad.push(new Expression(String.valueOf(curr)));
			} else if (curr == '+' || curr == '-'){
				if (ad.size() < 2) {
					System.out.println("Unexpected state.");
					continue;
				}
				Expression rightExpr = ad.pop();
				Expression leftExpr = ad.pop();
				
				String newStr = leftExpr.getInfix_expr() + String.valueOf(curr) + rightExpr.getInfix_expr();
				ad.push(new Expression(newStr, curr));
			} else if (curr == '*' || curr == '/'){		
				if (ad.size() < 2) {
					System.out.println("Unexpected state.");
					continue;
				}
				
				Expression rightExpr = ad.pop();
				Expression leftExpr = ad.pop();
				String rightStr = rightExpr.getInfix_expr();
				String leftStr = leftExpr.getInfix_expr();
				
				if (rightExpr.getOperator() == '+' || rightExpr.getOperator() == '-') {
					rightStr = "(" + rightExpr.getInfix_expr() + ")";
				}
				
				if (leftExpr.getOperator() == '+' || leftExpr.getOperator() == '-') {
					leftStr = "(" + leftExpr.getInfix_expr() + ")"; 
				}
				
				String newStr = leftStr + String.valueOf(curr) + rightStr;
				ad.push(new Expression(newStr, curr));
				
			} else {
				//System.out.println("Unexpected character encountered.");
				continue;
			}
		}
		
		if (ad.size() > 0) {
			return ad.pop().getInfix_expr();
		} else {
			return "";
		}
	}

	private static String infixToPostfix(String str) {
		ArrayDeque<Character> ad = new ArrayDeque<Character>();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<str.length(); i++) {
			char curr = str.charAt(i);
			if (Character.isDigit(curr) || Character.isLetter(curr)) {
				sb.append(curr);
			} else if (curr == '(') {
				ad.push(curr);
			} else if (curr == ')') {
				while (!ad.isEmpty()) {
					if (ad.peek() == '(') {
						ad.pop();
						break;
					} else {
						sb.append(ad.pop());
					}
				}
			} else {
				if (ad.isEmpty()) {
					ad.push(curr);
				} else {
					while (!ad.isEmpty()) {
						int retVal = higherPrecedence(curr, ad.peek());
						if (retVal <= 0) {
							sb.append(ad.pop());
						} else {
							break;
						}
					}
					ad.push(curr);
				}
			}
		}
		
		if (!ad.isEmpty()) {
			sb.append(ad.pop());
		}
		
		return sb.toString();
	}
	
	/*
	 * Returns the precedence
	 */
	private static int higherPrecedence(char a, char b) {
		int a_index = -1, b_index = -1;
		for (int i=0; i<precede.length; i++) {
			if (precede[i] == a) {
				a_index = i; 
			}
			if (precede[i] == b) {
				b_index = i;
			}
		}
		
		if (a_index == -1 || b_index == -1) {
			return 2;
		}
		
		if (a_index < b_index) {
			//a precedes b
			return 1;
		} else if (a_index == b_index) {
			//a == b
			return 0;
		} else {
			return -1;
		}
	}
}

class Expression {
	String infix_expr;
	char operator;
	
	public Expression(String str, char oper) {
		this.infix_expr = str;
		this.operator = oper;
	}

	public Expression(String operand) {
		this.infix_expr = operand;
	}

	public String getInfix_expr() {
		return infix_expr;
	}

	public void setInfix_expr(String infix_expr) {
		this.infix_expr = infix_expr;
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}
}
