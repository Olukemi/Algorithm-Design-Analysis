package maxHeapsYoungTableaux;

public class TestYoungT {

	public static void main(String[] args) {
		
		//data for testing:
		int[] arr1= new int[] { 1, 5, 4, 3,20, 18, 16, 14, 10, 12};   //int array arr1
		int[] arr2= new int[] {5, 4, 3, 11, 35, 56};
		int[] arr3={11, 91, 40, 5, 111, 14, 8, 68, 27, 1, 22, 44, 28, 555, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 101, 232, 115, 81, 187, 333, 191, 4, 26, 439, 100, 63, 2, 568};  //int array arr2

                //data for testing YoungT:
		int[][] d1 = {{1, 2, 20}, {5, 6, 7}, {9, 10, 11}};
		int[][] d2 = {{110, 2, 3, 4}, {20, 6, 7, 8}, {9, 10, 11, 12}};

		
		//----------------------------------- Test Starts Here------------------------------------
		System.out.println("---------------------**TEST BEGINS**--------------------------------" );	                  
		
		System.out.println("\n--------------Testing YoungT  class--------------------------------");
		System.out.println('\n');
		System.out.println("--------Test 7------------" );
		
		System.out.println("Testing YoungT constructor 1 for empty YoungT:");  
		YoungT t1 = new YoungT(3, 3, 90);   
		//System.out.println(" \nprinting empty tableaux: \n"+ t1.toString());
		
		System.out.println(" \n Testing isEmpty method: "); 
		System.out.println(" is the tableaux empty: " + t1.isEmpty()); 
		System.out.println(" Expected : true" );
		
		System.out.println(" \n Testing getInfinity method: "); 
		System.out.println(" value of infinity: " + t1.getInfinity()); 
		System.out.println(" Expected value of infinity: = 100" );

		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 8------------" );
		
		System.out.println("Testing YoungT constructor 2 and insert method using d1: "); 
		YoungT t2= new YoungT(d1);  
		
		System.out.println(" #finite integers: " + t2.getNumElem()); 
		System.out.println(" Expected #finite integers = 9" );
		
		System.out.println(" value of infinity: " + t2.getInfinity()); 
		System.out.println(" Expected: 200" );
		
		System.out.println(" printing items stored in the tableau: \n"+ t2.toString());  
		String p1=" 1, 2, 7 , 5, 6, 11, 9, 10, 20";
		String p2=" 1, 6, 7, 2, 9, 10, 5, 11, 20";

		System.out.print(" This test assumes that your constructor scans the input array in raster scan order and inserts the current element in the tableau using the insert method).\n");
        System.out.print(" There are two possibilities for the correct output depending on how you move (in insert) the input value x - if you move it up (option1) or to the left - when both neighbours are infinity.\n");

		System.out.println("Expected option 1: "+p1);
		System.out.println("Expected option 2: "+p2);

		System.out.println(" \n Testing isFull method: "); 
		System.out.println(" is the tableau Full: " + t2.isFull()); 
		System.out.println(" Expected : true" );
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 9------------" );
		System.out.println(" Testing YoungT readMin method: "); 
		System.out.println(" min value in the tableau: " + t2.readMin());
		System.out.println(" Expected min value = 1" );
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
			
		System.out.println("--------Test 11------------" );
		System.out.println("Testing YoungT deleteMin method: "); 
		int min_val = t2.deleteMin();                      // Extracting min value
		System.out.println("smallest value extracted using deleteMin: "+ min_val); // printing the min value
		System.out.println("Expected value :  1") ;
		
		System.out.println(" \n #items stored in the tableau after deleteMin: "+ t2.getNumElem()); 
		System.out.println(" Expected #items stored in the tableau after deleteMin = 8" );
		
		//Put the inf key on the smallest key's position and fix the Young tableau property
		System.out.println(" \nprinting items stored in the tableaux after deleteMin: \n" + t2.toString());  
		String p3=" 2, 6, 7, 5, 10, 11, 9, 20, inf";
		String p31=" 2, 6, 7, 5, 9, 10, 11, 20, inf";
		System.out.println("Expected (if the initial tableau was 1, 2, 7 , 5, 6, 11, 9, 10, 20: "+p3);
		System.out.println("Expected (if the initial tableau was 1, 6, 7, 2, 9, 10, 5, 11, 20: "+p31);
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		
		System.out.println("--------Test 12.1------------" );
		System.out.println(" Testing YoungT find method: ");                        
		//using d1 to test find method
		System.out.print("Finding 5 in the tableau:" + t2.find(5));
		System.out.println("\nExpected find result: true");
		
		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 2, 7 , 5, 6, 11, 9, 10, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 2, 6, 5\n");
		System.out.println("Expected sequence when starting at bottom left corner: 9, 5\n");

		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 6, 7, 2, 9, 10, 5, 11, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 6, 1, 2, 5\n");
		System.out.println("Expected sequence when starting at bottom left corner: 5\n");
		
		
		
		
		System.out.println("--------Test 12.2------------\n" );
		System.out.print("Finding 12 in the tableau:" + t2.find(12));
		System.out.println("\nExpected find result: false");
		
		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 2, 7 , 5, 6, 11, 9, 10, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 11, 20, 10\n");
		System.out.println("Expected sequence when starting at bottom left corner: 9, 10, 20, 11\n");

		System.out.println('\n');     //new line
		System.out.println(" If the tableau is 1, 6, 7, 2, 9, 10, 5, 11, 20\n"); 
		System.out.println("Expected sequence when starting at top right corner: 7, 10, 20, 11\n");
		System.out.println("Expected sequence when starting at bottom left corner: 5, 11, 20, 10\n");
		
		
		System.out.println("\n-----------------------------Testing Done for YoungT!-------------------------------------");
		System.out.println('\n');
		
	}
}
