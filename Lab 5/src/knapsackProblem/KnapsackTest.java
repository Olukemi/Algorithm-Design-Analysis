package knapsackProblem;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class KnapsackTest {
	
	public static void main( String[] args) {
		
		String s1= "4 8 7 10 2 3 1 5 9 2";
		System.out.println("Input: " + s1);
		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(s1));
		System.out.println();
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(s1));
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(s1));
		System.out.println();
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(s1));
		
		String s2= "1 8 7 3";
		System.out.println();
		System.out.println("Input: " + s2);
		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(s2));
		System.out.println();
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(s2));
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(s2));
		System.out.println();
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(s2));
		
		String s3= "5 12 9 10 1 4 5 6 5 6 1 5";
		System.out.println();
		System.out.println("Input: " + s3);
		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(s3));
		System.out.println();
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(s3));
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(s3));
		System.out.println();
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(s3));
		System.out.println("---------------------------------------------------------------------------------");
		
		// NEW TEST CASES
		System.out.println("For n >= 10...");
		System.out.println("1. The greedy algorithm for the 0-1 variant gives the optimal solution");
		String s4 = "11 67 2 1 4 5 6 8 8 2 10 10 12 11 14 6 16 7 18 3 20 4 22 9"; 
		System.out.println();
		System.out.println("Input: " + s4);
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(s4));
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(s4));
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("2. The greedy algorithm for the 0-1 variant gives a suboptimal solution");
		String s5 = "11 54 2 1 4 5 6 8 8 2 10 10 12 11 14 6 16 7 18 3 20 4 22 9"; 
		System.out.println();
		System.out.println("Input: " + s5);
		System.out.println();
		System.out.println("Solution to the fractional variant:");
		System.out.println(KnapsackGreedy.fractional(s5));
		System.out.println("Result of the greedy algorithm for the 0-1 variant:");
		System.out.println(KnapsackGreedy.greedy01(s4));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("3. The optimal solution to the 0-1 variant uses all available target weight W");
		String s6 = "10 50 3 5 5 3 10 1 1 7 6 2 8 8 2 6 4 11 7 4 9 3"; 
		System.out.println();
		System.out.println("Input: " + s6);
		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(s6));
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(s6));
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("4. the optimal solution to the 0-1 variant does not use all available target weight W");
		String s7 = "10 13 3 14 5 16 10 1 1 7 6 11 8 31 2 5 4 20 7 12 9 8"; 
		System.out.println();
		System.out.println("Input: " + s7);
		System.out.println();
		System.out.println("Solution using recursion with memoization:");
		System.out.println(KnapsackDP.recMemo(s7));
		System.out.println("Solution based on bottom-up dynamic programming:");
		System.out.println(KnapsackDP.nonRec(s7));
	}
}
