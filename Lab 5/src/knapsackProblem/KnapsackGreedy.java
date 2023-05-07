package knapsackProblem;

import java.util.Scanner;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class KnapsackGreedy {
	
	private int n = 0; // number of elements in set
	private int W; // weight
	private int [][] S; // stores the set of all elements
	private double [] ind;
	
	/**********************************************PRIVATE METHODS**************************************************/
	private boolean isWLargest() {
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			if (W > S[i][1]) {
				count++;
			}
		}
		
		if (count == n) { // W is larger than all weights
			return true;
		} else {
			return false;
		}
		
	}

	/**********************************************PUBLIC METHODS**************************************************/
	
	// create constructor to initialize the instance field of the input string
	public KnapsackGreedy(String inputString) {
		Scanner input = new Scanner(inputString);
		n = input.nextInt();
		W = input.nextInt();
		S = new int[n][2]; // space for vi, wi
		ind = new double[n];
		for (int i = 0; i < n; i++) {
			S[i][0] = input.nextInt(); // vi
			S[i][1] = input.nextInt(); // wi
		}
		
		for (int i = 0; i < n; i++) {
			ind[i] = 0;
		}
		// note all Si elements are stored at location i-1
		
	}
	
	public static String fractional(String input) {
		
		String output = "";
		double totalValue = 0;
		double totalWeight = 0;
		KnapsackGreedy k = new KnapsackGreedy(input);
		
		if (k.n == 1 && k.W > k.S[0][1]) {
			return "1.0, total value = " + k.S[0][0] + ".0, total weight = " + k.S[0][1] + ".0";
		}
		
		
		double[][] valuePerWeight = new double[k.n][2];
		

		// store the value per weight results in array to sort
		for (int r = 0; r < k.n; r++) {
			//System.out.println("v" + (r+1) + ": " + k.S[r][0] + ", w" + (r+1) + ": " + k.S[r][1]);
			valuePerWeight[r][0] = (double) k.S[r][0]/k.S[r][1]; //vi/wi
			valuePerWeight[r][1] = r+1; // element i associated with its ratio
		}
		
		// sort array in DECREASING order - bubble sort
		int n = valuePerWeight.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (valuePerWeight[j][0] < valuePerWeight[j + 1][0]) {
                	//swap ratio
                    double temp_r = valuePerWeight[j][0];
                    valuePerWeight[j][0] = valuePerWeight[j + 1][0];
                    valuePerWeight[j + 1][0] = temp_r;
                    //swap element i
                    double temp_i = valuePerWeight[j][1];
                    valuePerWeight[j][1] = valuePerWeight[j + 1][1];
                    valuePerWeight[j + 1][1] = temp_i;
                }
			}
		}
        
        
        // perform division
        int w = k.W;
        int m = 0;
        double xi = 1;
        int wi;
        int vi;
        while (w >= 0) {
        	if (m == k.n) {
        		break;
        	}
        	int i = (int) valuePerWeight[m][1]; // element i
        	xi = (double) w/k.S[i-1][1];
        	wi = k.S[i-1][1];
        	vi = k.S[i-1][0];
        	if (xi < 1) { // is w/wi = xi < 1
        		totalValue += xi*vi; //xi * vi
        		totalWeight += xi*wi; //xi * wi
        		k.ind[i-1] = (double) xi; // element i is store in location i-1
        	} else {
        		totalValue += vi; // add to total value sum
            	totalWeight += wi; // add to total weight sum
            	k.ind[i-1] = 1; // element i is store in location i-1
        	}
        	w = w - wi; //w-wi
        	m++;
        }
        
        for (int i = 0; i < k.n; i++) {
        	output += k.ind[i] + ", ";
        }
        
        
 		return output + "total value = " + totalValue + ", total weight = " + totalWeight;
		
	}
	
	public static String greedy01(String input) {
		
		String output = "";
		double totalValue = 0;
		double totalWeight = 0;
		
		KnapsackGreedy k = new KnapsackGreedy(input);
		
		// if W is largest???
		/*
		if (k.isWLargest()) {
			for (int i = 0; i < k.n; i++) {
	        	output += k.ind[i] + ", ";
	        }
			return output += "total value = 0.0, total weight = 0.0";
		}*/
			
		
		if (k.n == 1 && k.W > k.S[0][1]) {
			return "1.0, total value = " + k.S[0][0] + ".0, total weight = " + k.S[0][1] + ".0";
		}
		
		
		double[][] valuePerWeight = new double[k.n][2];
		

		// store the value per weight results in array to sort
		for (int r = 0; r < k.n; r++) {
			//System.out.println("v" + (r+1) + ": " + k.S[r][0] + ", w" + (r+1) + ": " + k.S[r][1]);
			valuePerWeight[r][0] = (double) k.S[r][0]/k.S[r][1]; //vi/wi
			valuePerWeight[r][1] = r+1; // element i associated with its ratio
		}
		
		// sort array in DECREASING order - bubble sort
		int n = valuePerWeight.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (valuePerWeight[j][0] < valuePerWeight[j + 1][0]) {
                	//swap ratio
                    double temp_r = valuePerWeight[j][0];
                    valuePerWeight[j][0] = valuePerWeight[j + 1][0];
                    valuePerWeight[j + 1][0] = temp_r;
                    //swap element i
                    double temp_i = valuePerWeight[j][1];
                    valuePerWeight[j][1] = valuePerWeight[j + 1][1];
                    valuePerWeight[j + 1][1] = temp_i;
                }
			}
		}
        
        
        // perform division
        int w = k.W;
        int m = 0;
        double xi = k.W;
        int wi;
        int vi;
        for (int a = 0; a < valuePerWeight.length; a++) {
        	int i = (int) valuePerWeight[m][1]; // element i	
        	xi = (double) w/k.S[i-1][1];
        	wi = k.S[i-1][1];
        	vi = k.S[i-1][0];
        	if (xi < 1) { // is w/wi = xi < 1
        		k.ind[i-1] = 0; // element i is store in location i-1
        	} else {
        		totalValue += vi; // add to total value sum
            	totalWeight += wi; // add to total weight sum
            	k.ind[i-1] = 1; // element i is store in location i-1
            	w = w - wi; //w-wi
        	}

        	m++;
        }
		
		
		
		for (int i = 0; i < k.n; i++) {
        	output += k.ind[i] + ", ";
        }
		
		return output + "total value = " + totalValue + ", total weight = " + totalWeight;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s1= "4 8 7 10 2 3 1 5 9 2";
		System.out.println("Input: " + s1);
		System.out.println(KnapsackGreedy.fractional(s1));
		System.out.println(KnapsackGreedy.greedy01(s1));
		String s2= "11 67 2 1 4 5 6 8 8 2 10 10 12 11 14 6 16 7 18 3 20 4 22 9";
		System.out.println("Input: " + s2);
		System.out.println(KnapsackGreedy.fractional(s2));
		System.out.println(KnapsackGreedy.greedy01(s2));
		

	}

}
