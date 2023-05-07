package maxHeapsYoungTableaux;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class YoungT {
	
	/**********************************************PRIVATE METHODS**************************************************/
	private int[][] storeIntArray; // reference to a 2D array storing the integers
	private int intCount = 0; // stores the number of finite integers in the tableau
	private int inf; // integer representing infinity
	
	private int findMax(int [][] a) {
		
		int max = a[0][0]; // set the first integer to be max
		
		// loop through and compare, update max once a larger integer is found
		for (int r = 0; r < a.length; r++) {
			for (int c = 1; c < a[r].length; c++) {
				if (a[r][c] > max) {
					max = a[r][c];
				}
			}
		}
		
		return max;
	}
	
	private void zigzagDown(int x) {
		// x is the first integer y(0,0) it needs to be placed in the right location
		int r = 0, c = 0;
		int i = storeIntArray.length-1; // k - 1
		int j = storeIntArray[i].length-1; // n - 1 
		while (r != i && c != j) { // we haven't hit an edge yet
			if (x < storeIntArray[r][c+1] && x < storeIntArray[r+1][c]) { // x is actual the minimum compared to its "parents", stop here
				break;
			} else {
				if (storeIntArray[r][c+1] < storeIntArray[r+1][c]) { // if the integer to the right is smaller than the integer below
					swap(r,c,r,c+1);
					c++;
				} else if (storeIntArray[r][c+1] > storeIntArray[r+1][c]) { // if the integer below is smaller than the integer to the right
					swap(r,c,r+1,c);
					r++;
				} else {
					swap(r,c,r+1,c);
					r++;
				}
			}
		}
		if (r == i) { // if we have hit an edge below (we are on row k-1)
			while (c != j) {
				if (x < storeIntArray[r][c+1]) { // stop here
					break;
				} else {
					swap(r,c,r,c+1);
					c++;
				}
			}
		} else if (c == j) { // if we have hit an edge to the right (we are on col n-1)
			while (r != i) {
				if (x < storeIntArray[r+1][c]) { // stop here
					break;
				} else {
					swap(r,c,r+1,c);
					r++;
				}
			}
		}
	}
	
	private void zigzagUp(int x) {
		// x is the last integer y(k-1,n-1) it needs to be placed in the right location
		int r = storeIntArray.length-1, c = storeIntArray[0].length-1;
		while (r != 0 && c != 0) { // while not at an edge
			if (x > storeIntArray[r][c-1] && x > storeIntArray[r-1][c]) { // x is actual the maximum compared to its "parents", stop here
				break;
			} else {
				if (storeIntArray[r][c-1] > storeIntArray[r-1][c]) { // if the integer to the left is bigger than the integer above
					swap(r,c,r,c-1);
					c--;
				} else if (storeIntArray[r][c-1] < storeIntArray[r-1][c]) { // if the integer above is bigger than the integer to the left
					swap(r,c,r-1,c);
					r--;
				} else {
					swap(r,c,r-1,c);
					r--;
				}
			}
				
		}
		
		if (r == 0) { // if we have hit an edge above (we are on row 0)
			while (c != 0) {
				if (x > storeIntArray[0][c-1]) { // stop here
					break;
				} else {
					swap(r,c,r,c-1);
					c--;
				}
			}
		} else if (c == 0) { // if we have hit an edge to the left (we are on col 0)
			while (r != 0) {
				if (x > storeIntArray[r-1][0]) { // stop here
					break;
				} else {
					swap(r,c,r-1,c);
					r--;
				}
			}
		} 
	}
	
	// swap function
	private void swap(int r1, int c1, int r2, int c2) {
		int temp = storeIntArray[r1][c1];
		storeIntArray[r1][c1] = storeIntArray[r2][c2];
		storeIntArray[r2][c2] = temp; 
	}
	
	private boolean compare(int r, int c, int x) {
		System.out.print("Comparing " + x + " with: ");
		if (r == 0) { // top right corner
			while (true) {
				if (storeIntArray[r][c] == x) { // have found x
					System.out.print(storeIntArray[r][c] + ", ");
					return true; // function exits
				} else if (storeIntArray[r][c] > x){ // it's bigger than x, go left 
					System.out.print(storeIntArray[r][c] + ", "); // haven't found it yet
					if (c == 0) {
						break; //  x not found
					} else {
						c--; // go left
					}
					
				} else if (storeIntArray[r][c] < x) { // it's smaller than x, go down
					System.out.print(storeIntArray[r][c] + ", ");
					if (r == storeIntArray.length-1) {
						break; // x not found
					} else {
						r++; // go down
					}
				}
			}
		} else if (r == storeIntArray.length-1) { // bottom left corner
			while (true) {
				if (storeIntArray[r][c] == x) {
					System.out.print(storeIntArray[r][c] + ", ");
					return true;
				} else if (storeIntArray[r][c] > x){ // it's bigger than x, go up
					System.out.print(storeIntArray[r][c] + ", ");
					if (r == 0) {
						break; // x not found
					} else {
						r--; // go up
					}
					
				} else if (storeIntArray[r][c] < x) { // it's smaller than x, go right
					System.out.print(storeIntArray[r][c] + ", ");
					if (c == storeIntArray[0].length-1) {
						break; // x not found
					} else {
						c++; // go right
					}
				}

			}
		}
		return false;
	}
	
	/**********************************************PUBLIC METHODS**************************************************/

	public YoungT(int k, int n, int infinity) {
		
		if (k < 10) {
			k = 10; // reset to 10
		} 
		
		if (n < 10) {
			n = 10; // reset to 10
		}

		if (infinity < 100) {
			infinity = 100; // set to 100
		}
		
		inf = infinity; // assign inf to be infinity
		
		storeIntArray = new int[k][n]; // constructs an empty tableau
		
		// fills tableau with infinities
		for (int r = 0; r < storeIntArray.length; r++) {
			for (int c = 0; c < storeIntArray[r].length; c++) {
				storeIntArray[r][c] = inf;
			}
		}
		
	}
	
	public YoungT(int[][] a) {
		
		int x = a.length;
		int y = a[0].length;
		inf = 10*findMax(a); // set infinity to 10 times the largest number in array a
		
		storeIntArray = new int[x][y]; // empty tableau
		
		// fill with infinities
		for (int r = 0; r < storeIntArray.length; r++) {
			for (int c = 0; c < storeIntArray[r].length; c++) {
				storeIntArray[r][c] = inf;
			}
		}
		
		// begin to insert integers of array a in the order as they come from left to right
		for (int r = 0; r < a.length; r++) {
			for (int c = 0; c < a[r].length; c++) {
				insert(a[r][c]);
			}
		}
				
	}
	
	public int getNumElem() {
		return intCount;
	}
	
	public int getInfinity() {
		return inf;
	}
	
	public boolean isEmpty() {
		
		if (intCount == 0) { // if intCount is 0
			return true; // tableau is empty
		}
		return false; // otherwise
	}
	
	public boolean isFull() {
		int r = storeIntArray.length; // get k
		int c = storeIntArray[0].length; // get n
		if (intCount == r*c) { // if intCount is k*n
			return true; // tableau is full
		}
		return false; // otherwise, it's empty
	}
	
	public boolean insert(int x) {
		
		if (isFull() || x >= inf) { // if full or larger or equal to value representing infinity
			return false;
		} else {
			int r = storeIntArray.length - 1;
			int c = storeIntArray[r].length - 1;
			storeIntArray[r][c] = x; // place x in the last tableau location (replace infinity)
			zigzagUp(storeIntArray[r][c]); // move x to the up/left to it's proper location
			intCount++; // increment 
		}
		return true;
	}
	
	public int readMin() throws RuntimeException{
		if (isEmpty()) {
			throw new RuntimeException("This Young Tableau is empty!");
		}
		
		return storeIntArray[0][0]; // return the first integer in the tableau
	}
	
	public int deleteMin() throws RuntimeException{
		if (isEmpty()) { // if empty
			throw new RuntimeException("This Young Tableau is empty!");
		}
		
		int x = storeIntArray[0][0]; // get the minimum integer to return
		
		int r = storeIntArray.length - 1;
		int c = storeIntArray[r].length - 1;
		storeIntArray[0][0] = storeIntArray[r][c];// promote last integer to location y(0,0)
		storeIntArray[r][c] = inf;
		intCount--;//decrease finite integer count
		
		zigzagDown(storeIntArray[0][0]); // move this key down/right to it's proper location 
		
		return x; // return the minimum key that was deleted
	}
	
	public boolean find(int x) throws RuntimeException{
		if (isEmpty()) {
			throw new RuntimeException("This Young Tableau is empty!");
		} else if (x >= inf) {
			throw new RuntimeException("x is larger or equal to 'infinity'.");
		}
		
		int a = storeIntArray.length;
		int b = storeIntArray[0].length;
		
		// pick the top right or bottom left corner
		if (storeIntArray[0][b-1] < storeIntArray[a-1][0]) {
			if (compare(0, b-1, x)) { // top right
				return true;
			}
		} else {
			if (compare(a-1, 0, x)) { // bottom left
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		
		String youngT = ""; // empty string
		
		// loop through tableau and append integers
		for (int r = 0; r < storeIntArray.length; r++) {
			for (int c = 0; c < storeIntArray[r].length; c++) {
				youngT += storeIntArray[r][c] + ", ";
			}
		}
		
		return youngT;
	}
	
	public static void main(String[] args) {
		
		int[][] d1 = {{1, 2, 20}, {5, 6, 7}, {9, 10, 11}};
		YoungT t1 = new YoungT(2, 2, 50);
		YoungT t2= new YoungT(d1); 
		
		
		System.out.println("Printing items stored in the tableau: "+ t1.toString()); 
		System.out.println(" is the tableau Full: " + t1.isFull());
		System.out.println(" is the tableaux empty: " + t1.isEmpty());
		System.out.println(" #finite integers: " + t1.getNumElem());

		//System.out.println(t1.find(t1.getInfinity()));
		
		try {
			System.out.println(t1.find(t1.getInfinity()));
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		System.out.println("************************************************************************");	
		System.out.println("Printing items stored in the tableau: "+ t2.toString()); 
		System.out.println(t2.find(5));
		System.out.println(t2.find(12));
		System.out.println(" min value in the tableau: " + t2.readMin());
		System.out.println(" #finite integers: " + t2.getNumElem());
		System.out.println(" is the tableaux empty: " + t2.isEmpty());
		System.out.println("smallest value extracted using deleteMin: "+ t2.deleteMin()); // printing the min value
		System.out.println(" \nprinting items stored in the tableaux after deleteMin: \n" + t2.toString());  
		System.out.println(" #finite integers: " + t2.getNumElem());
		System.out.println(" is the tableaux empty: " + t2.isEmpty());
		System.out.println("smallest value extracted using deleteMin: "+ t2.deleteMin());
		System.out.println("smallest value extracted using deleteMin: "+ t2.deleteMin());
		System.out.println(" \nprinting items stored in the tableaux after deleteMin: \n" + t2.toString());  
		System.out.println("smallest value extracted using deleteMin: "+ t2.deleteMin());
		System.out.println(" is the tableau Full: " + t2.isFull());
		System.out.println(" is the tableaux empty: " + t2.isEmpty());
		try {
			System.out.println("smallest value extracted using deleteMin: "+ t2.deleteMin());
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		try {
			System.out.println(" min value in the tableau: " + t2.readMin());
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		
	}

}
