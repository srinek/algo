package algo.arrays;

import java.util.Stack;

/*Normally, the factorial of a positive integer n is the product of all positive integers less than or equal to n.  For example, factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1.

We instead make a clumsy factorial: using the integers in decreasing order, we swap out the multiply operations for a fixed rotation of operations: multiply (*), divide (/), add (+) and subtract (-) in this order.

For example, clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1.  However, these operations are still applied using the usual order of operations of arithmetic: we do all multiplication and division steps before any addition or subtraction steps, and multiplication and division steps are processed left to right.

Additionally, the division that we use is floor division such that 10 * 9 / 8 equals 11.  This guarantees the result is an integer.

Implement the clumsy function as defined above: given an integer N, it returns the clumsy factorial of N.

 

Example 1:



Input: 4
Output: 7
Explanation: 7 = 4 * 3 / 2 + 1
Example 2:

Input: 10
Output: 12
Explanation: 12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1*/

/*
 * 1-- 1
 * 2 -- 2*1
 * 3 -- 3*2/1
 * 4 -- 4*3/2+1
 * 5 -- 5*4/3+2-1
 * 6 - 6*5/4+3- ( 2*1)
 */
public class ClumsyFactorial {
	
	public static void main(String[] args) {
		ClumsyFactorial test = new ClumsyFactorial();
		
		System.out.println(test.clumsy(10));
	}
	
	/*public int clumsy(int n) {
		
		if(n == 0){
			return 0;
		}
		if(n ==1){
			return n;
		}
		if(n == 2){
			return n * (n-1);
		}
		if(n == 3){
			return n * (n-1) / (n-2);
		}
		if(n == 4){
			return n * (n-1) / (n-2) + (n-3);
		}
		return n * (n-1) / (n-2) + (n-3) - clumsy(n-4);
	}*/
	
	/*
	 * Input: 10
Output: 12
Explanation: 12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1*/

	public int clumsy(int n) {

		Stack<Integer> s = new Stack<>();
		char[] ops = new char[]{'*', '/', '+', '-'};
		int j = 0, top = n;
		for(int i=n-1; i > 0; i--, j++) {
			
			if(ops[j%4] == '*'){
				top *= i;
			}
			else if(ops[j%4] == '/'){
				top /= i;
			}
			else if(ops[j%4] == '+'){
				s.push(top);
				top = i;
			}
			else{ // op is -
				s.push(top);
				top = -i;
			}
		}
		s.push(top);
		int total = 0;
		while (!s.isEmpty()) {
			total += s.pop();
		}

		return total;

	}
}
