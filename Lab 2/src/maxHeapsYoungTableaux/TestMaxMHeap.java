package maxHeapsYoungTableaux;

public class TestMaxMHeap {

	public static void main(String[] args) {
		
		//data for testing:
		int[] arr1= new int[] {1, 5, 4, 3,20, 18, 16, 14, 10, 12};   //int array arr1
		int[] arr2= new int[] {5, 4, 3, 11, 35, 56};
		int[] arr3={11, 91, 40, 5, 111, 14, 8, 68, 27, 1, 22, 44, 28, 555, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 101, 232, 115, 81, 187, 333, 191, 4, 26, 439, 100, 63, 2, 568};  //int array arr2

                //data for testing YoungT:
		int[][] d1 = {{1, 2, 20}, {5, 6, 7}, {9, 10, 11}};
		int[][] d2 = {{110, 2, 3, 4}, {20, 6, 7, 8}, {9, 10, 11, 12}};

		
		//----------------------------------- Test Starts Here------------------------------------
		System.out.println("---------------------**TEST BEGINS**--------------------------------" );
		System.out.println("\n--------------Testing MaxMHeap class--------------------------------");  
		System.out.println('\n');     //new line
		System.out.println("--------Test 1------------" );
		
		System.out.println("Testing MaxMHeap constructor 1 for empty Heap:");  
		MaxMHeap a1= new MaxMHeap(3,10);
		System.out.println(" #keys stored  in the empty heap: " + a1.getSize()); 
		System.out.println(" Expected #keys stored in the empty heap = 0" );
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 2.1------------" );
		
		System.out.println("Testing MaxMHeap constructor2 for 4-ary using arr1: "); 
		MaxMHeap a2= new MaxMHeap(4,arr1);    //creating MaxMHeap object using arr1
		
		System.out.println(" #items stored  in the heap: "+ a2.getSize());      
		System.out.println(" Expected #keys stored in the heap = 10" );
		
		
		System.out.println(" Value of m in the heap: "+ a2.getM());  
		System.out.println(" Value of m expected to be 4" );
		
		System.out.println("Output  : "+ a2.toString());  
		String exp1="20, 18, 12, 3, 1, 5, 16, 14, 10, 4, ";
		System.out.println("Expected: "+exp1);
		
		System.out.println("--------Test 2.2------------" );
		
		System.out.println("Testing MaxMHeap constructor2 for 3-ary using arr1: "); 
		MaxMHeap a22= new MaxMHeap(3,arr1);    //creating MaxMHeap object using arr1
		
		System.out.println(" #items stored  in the heap: "+ a22.getSize());      
		System.out.println(" Expected #keys stored in the heap = 10" );
		
		
		System.out.println(" Value of m in the heap: "+ a22.getM());  
		System.out.println(" Value of m expected to be 3" );
		
		System.out.println("Output  : "+ a22.toString());  
		String exp22="20, 18, 14, 3, 5, 1, 16, 4, 10, 12 ";
		System.out.println("Expected: "+exp22);
		
		
		System.out.println("--------Test 2.3------------" );
		
		System.out.println("Testing MaxMHeap constructor2 for 4-ary using arr3: "); 
		MaxMHeap a33= new MaxMHeap(4,arr3);    //creating MaxMHeap object using arr1
		
		System.out.println(" #items stored  in the heap: "+ a33.getSize());      
		System.out.println(" Expected #keys stored in the heap = 38" );
		
		System.out.println(" Value of m in the heap: "+ a33.getM());  
		System.out.println(" Value of m expected to be 4" );
		
		System.out.println("Output  : "+ a33.toString());  
		String exp33="568, 439, 44, 555, 111, 101, 232, 333, 100, 40, 22, 11, 28, 5, 99, 33, 69, 3, 7, 56, 78, 49, 12, 9, 14, 8, 115, 81, 187, 68, 191, 4, 26, 27, 91, 63, 2, 1, ";
		System.out.println("Expected: "+exp33);
		
		System.out.println("--------Test 2.4------------" );
		
		System.out.println("Testing MaxMHeap constructor2 for 3-ary using arr3: "); 
		MaxMHeap a34= new MaxMHeap(3,arr3);    //creating MaxMHeap object using arr1
		
		System.out.println(" #items stored  in the heap: "+ a34.getSize());      
		System.out.println(" Expected #keys stored in the heap = 38" );
		
		
		System.out.println(" Value of m in the heap: "+ a34.getM());  
		System.out.println(" Value of m expected to be 3" );
		
		System.out.println("Output  : "+ a34.toString());  
		String exp34="568, 555, 333, 439, 111, 69, 78, 101, 232, 191, 26, 100, 28, 91, 99, 33, 14, 3, 7, 56, 8, 49, 12, 9, 68, 27, 115, 81, 187, 1, 40, 4, 11, 22, 44, 63, 2, 5, ";
		System.out.println("Expected: "+exp34);
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 3------------" );
		
		System.out.println(" Testing MaxMHeap readMax method for full heap: "); 
		System.out.println(" max value in the heap: " + a2.readMax());
		System.out.println(" Expected max value = 20" );
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		System.out.println("--------Test 4------------" );
		
		System.out.println(" Testing MaxMHeap insert method");  
		a2.insert(30);                 //testing insert method
		
		System.out.println(" #items stored  in the heap after insertion: "+ a2.getSize()); 
		System.out.println(" Expected #keys stored in the heap = 11" );
		
		System.out.println(" \n printing items stored in the heap after insertion: "+ a2.toString()); 
		String exp2="30, 18, 20, 3, 1, 5, 16, 14, 10, 4, 12, ";
		System.out.println("Expected: "+exp2);
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		
		System.out.println("--------Test 5------------" );
		MaxMHeap a3= new MaxMHeap(3,arr2);                            //creating MaxMHeap object using arr2
		System.out.println("Testing MaxMHeap deleteMax method: "); 
		int val = a3.deleteMax();                                   // Extracting max value from heap using deleteMax method
		System.out.println("max value extracted using deleteMax: "+ val); // printing the max value
		System.out.println("Expected value :  56") ;
		
		System.out.println(" \n #items stored in the heap after deleteMax: "+ a3.getSize()); 
		System.out.println(" Expected #keys stored in the heap after deleteMax = 5" );
		
		System.out.println(" \n printing items stored in the heap after deleteMax: " + a3.toString());  
		String exp3="35, 5, 3, 11, 4,";
		System.out.println("Expected: "+exp3);
		
		
		System.out.println("************************************************************************");	
		System.out.println('\n');     //new line
		
		
		System.out.println("--------Test 6------------" );
		System.out.println(" Testing MaxMHeap sortArray method: ");                       
		
		//using arr1 to test sortArray method
		MaxMHeap.sortArray(3, arr2);
		// Display sorted array which is stored in arr1
		System.out.print("printing items in non decresing order using sortArray method: \nSorted array: ");
		for (int n : arr2)
		System.out.print(n + ", ");
		String exp4=" 3, 4, 5, 11, 35, 56, ";
		System.out.println("\nExpected sorted array: "+exp4);
		
		System.out.println('\n');     //new line
		System.out.println("\n-----------------------------Testing Done for MaxMHeap!-------------------------------------");
		System.out.println('\n');
		                  
				

	}

}

