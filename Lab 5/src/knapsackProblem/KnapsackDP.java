package knapsackProblem;

import java.util.Scanner;
import java.lang.*;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class KnapsackDP {
	
	private int num;
	private int targetWeight;
	private int[] value;
	private int[] weight;
	private int[][] x;
	private int[][] totalValue;
	private int[][] totalWeight;

	
	/**********************************************PRIVATE METHODS**************************************************/
		
	public int solPbRecMemo(int n, int t) {
		// base case
		if (n == 0 && t >= 0) {
			x[0][t] = 1;
			return x[0][t];
		} 
		
		if (x[n-1][t] == -2) {
			x[n-1][t] = solPbRecMemo(n-1, t);
		} 
		
		int v1 = totalValue[n-1][t];
		int w1 = totalWeight[n-1][t];
		if (t < weight[n]) { // si is not a possible solution
			x[n][t] = 0;
			totalValue[n][t] = v1;
			totalWeight[n][t] = w1;
		} else { //si is a possible solution
			if (x[n-1][t-weight[n]] == -2) {
				x[n-1][t-weight[n]] = solPbRecMemo(n-1, t-weight[n]);
			}
			int v2 = totalValue[n-1][t-weight[n]];
			int w2 = totalWeight[n-1][t-weight[n]];
			if (v2 + value[n] > v1) {
	        	x[n][t] = 1;
				totalValue[n][t] = value[n] + v2;
				totalWeight[n][t] = weight[n] + w2;
				return x[n][t];
			} else {
				x[n][t] = 0;
				totalValue[n][t] = v1;
				totalWeight[n][t] = w1;
				return x[n][t];
			}
      
		}
		return 0;
	}
	
	public void solPbNonRec() {
		int n1 = num;
		int t1 = targetWeight;
		
		for (int n = 1; n < n1+1; n++) {
			for (int t = 0; t < t1+1; t++) {
				int v1 = totalValue[n-1][t];
				int w1 = totalWeight[n-1][t];
				
				if (t < weight[n]) { // si cannot be in the solution
					x[n][t] = 0;
					totalValue[n][t] = v1;
					totalWeight[n][t] = w1;
				} else { // si could be a solution
					int v2 = totalValue[n-1][t-weight[n]];
					int w2 = totalWeight[n-1][t-weight[n]];
					if (v2 + value[n] > v1) {
			        	x[n][t] = 1;
						totalValue[n][t] = value[n] + v2;
						totalWeight[n][t] = weight[n] + w2;
					} else {
						x[n][t] = 0;
						totalValue[n][t] = v1;
						totalWeight[n][t] = w1;
					}
					
				}
			}
		}
	}
	
	
	/**********************************************PUBLIC METHODS**************************************************/
	
	// create constructor to initialize the instance field of the input string
		public KnapsackDP(String inputString) {
			Scanner input = new Scanner(inputString);
			num = input.nextInt();
			targetWeight = input.nextInt();
			value = new int[num + 1]; // space for vi
			weight = new int[num + 1]; // space for vi
			totalValue = new int[num+1][targetWeight+1];
			totalWeight = new int[num+1][targetWeight+1];
			x = new int[num+1][targetWeight+1];
			
			
;			for (int i = 1; i <= num; i++) {
				value[i] = input.nextInt(); // vi
				weight[i] = input.nextInt(); // wi	
			}

			for (int r = 0; r < totalValue.length; r++) {
				for (int c = 0; c < totalValue[r].length; c++) {
					totalValue[r][c] = 0;
					totalWeight[r][c] = 0;
					x[r][c] = -2;
				}
			}
		}
	
	
	public static String recMemo(String s) {
		
		String output = "";
		
		KnapsackDP k = new KnapsackDP(s);
		
		k.solPbRecMemo(k.num, k.targetWeight);
		
		int totalValue = k.totalValue[k.num][k.targetWeight];
		int totalWeight = k.totalWeight[k.num][k.targetWeight];
		
		int y = k.targetWeight;
		int i = k.num;
		while(i > 0) {
        	output = k.x[i][y] + ", " + output;
        	if(k.x[i][y] == 1) {
        		y -= k.weight[i];
        	}
        	i--;
        }
		
		return output + "total value = " + totalValue + ", total weight = " + totalWeight;
	}
	
	
	public static String nonRec(String s) {
		
		String output = "";

		KnapsackDP k = new KnapsackDP(s);
		
		k.solPbNonRec();
		
		int totalValue = k.totalValue[k.num][k.targetWeight];
		int totalWeight = k.totalWeight[k.num][k.targetWeight];
		
		int y = k.targetWeight;
		int i = k.num;
		while(i > 0) {
        	output = k.x[i][y] + ", " + output;
        	if(k.x[i][y] == 1) {
        		y -= k.weight[i];
        	}
        	i--;
        }
		
		return output + "total value = " + totalValue + ", total weight = " + totalWeight;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s1= "4 8 7 10 2 3 1 5 9 2";
		System.out.println(KnapsackDP.recMemo(s1));
		System.out.println(KnapsackDP.nonRec(s1));

	}
}
