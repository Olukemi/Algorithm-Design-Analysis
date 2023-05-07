package maxHeapsYoungTableaux;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class MaxMHeap {
	/***INDEX AT 1***/
	/**********************************************PRIVATE METHODS**************************************************/
	private int[] heap; // reference to the array that stores the keys
	private int size = 0; // number of keys in MaxMHeap
	private int m; // max number of children each parent can have
	
	private void buildHeap(int[] a) {
		
		for(int i = (a.length-1)/2; i > 0; i--) {
			percolateDown(i);
		}
	
	}
	
	private void percolateDown(int i) {
		int key = heap[i];
		int child = m*(i-1) + 2; // first child of key is at location m*i
		
 		while (child <= size) {
			if (child != size) { // only child
				int s = child + 1;
				while (s <= m*(i-1)+m+1 && s <= size) {
					if (heap[s] > heap[child]) {
						child = s;
					}
					s++;
				}
			}
			if (heap[child] > key) {
				heap[i] = heap[child]; // promote child to parent
				i = child; // impede hole to child
				child = m*(i-1) + 2; // update child
 			} else {
 				break;
 			}
		}
		
		heap[i] = key; // place key in the hole
	}
	
	private void doubleArray() {
		int[] doubleHeap = new int[2*heap.length]; // create a new array with double the size 
		for (int i=1; i < size+1; i++) {
			doubleHeap[i] = heap[i]; // copy contents over
		}
		heap = doubleHeap;
	}
	
	/**********************************************PUBLIC METHODS**************************************************/
	public MaxMHeap(int k, int n) { // creates an empty MaxMHeap
		
		if (k < 2) { 
			k = 3; // reset to 3
		}
		
		if (n < 10) {
			n = 10; // reset to 10
		}
		
		m = k; // assign m to k
		
		heap = new int[n+1]; // constructs an empty MaxMHeap with one extra spot
		
	}
	
	public MaxMHeap(int k, int[] a) {
		if (k < 2) {
			k = 3; // reset to 3
		}
		m = k;
		
		size = a.length;
		heap = new int[size+1]; // constructs an empty MaxMHeap
		
		int i = 1;
		for (int n = 0; n < a.length; n++) {// copy contents of a into heap starting at i = 1
			heap[i] = a[n];
			i++;
		}
		
		buildHeap(a); // passes to method to build MaxMHeap
		
	}
	
	public int getM() {
		return m;
	}
	
	public int getSize() {
		return size;
	}
	
	public void insert(int x) {
		if (size == heap.length-1) {
			doubleArray(); // move MaxMheap in array an array of mple size
		}
		
		int i = ++size; // index of the hole
		heap[0] = x; // the store key 
		
		//percolate the hole up
		while (x > heap[(i + m - 2)/m]) { // while the value of x is greater parent
			heap[i] = heap[(i + m - 2)/m]; // impede the key of parent to hole
			i = (i + m - 2)/m; // promote hole to parent
		}
		
		heap[i] = x; // place new key in the hole
		
	}
	
	public int readMax() throws RuntimeException {
		if (size == 0) { // if the MaxMHeap is empty
			throw new RuntimeException("This MaxMHeap is empty!");
		}
		return heap[1]; // else return the first key (max key)
	}
	
	public int deleteMax() throws RuntimeException {
		if (size == 0) { // if the MaxMHeap is empty
			throw new RuntimeException("This MaxMHeap is empty!");
		}
		
		int x = heap[1]; // get the max key to return
		heap[1] = heap[size--];// promote last leaf to root and decrease size
		
		percolateDown(1); // percolate root down to it's correct location
		
		return x; // return the max key that was deleted
	}
	
	public String toString() {
		
		String heapString = ""; // empty string to append to 
		
		if (size == 0) { // if the heap is empty
			return "";
		}
		
		for (int i = 1; i < size+1; i++) { // loop through heap, adding each number to the string
			heapString += heap[i] + ", ";
		}
		return heapString;
	}
	
	public static void sortArray(int k, int[] a) {
		MaxMHeap h = new MaxMHeap(k, a); // build a heap out of this input array with k children
		
		// sort from array a starting at h[1]
		while (h.size > 0) { 
			a[h.size-1] = h.deleteMax(); // stores the integers in increasing order
		}
	}

	public static void main(String[] args) {
		int[] arr1= new int[] { 1, 5, 4, 3,20, 18, 16, 14, 10, 12};
		int[] arr2= new int[] {5, 4, 3, 11, 35, 56};
		
		
		MaxMHeap a1= new MaxMHeap(3,arr1);   
		System.out.println(" Value of m in the heap: "+ a1.getM()); 
		System.out.println(" #items stored  in the heap: "+ a1.getSize()); 
		System.out.println("Output: "+ a1.toString());
		System.out.println("max value extracted using deleteMax: "+  a1.deleteMax());
		System.out.println(" #items stored  in the heap: "+ a1.getSize()); 
		System.out.println("Output: "+ a1.toString());
		MaxMHeap.sortArray(3, arr1);
		for (int n : arr1)
			System.out.print(n + ", ");
		System.out.println("\n");
		System.out.println("************************************************************************");	
		MaxMHeap a2= new MaxMHeap(4,arr2);
		a2.insert(30); 
		System.out.println("Output: "+ a2.toString());
		System.out.println(" Value of m in the heap: "+ a2.getM()); 
		System.out.println(" #items stored  in the heap: "+ a2.getSize()); 
		System.out.println(" max value in the heap: " + a2.readMax());
		System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		System.out.println("Output: "+ a2.toString());
		MaxMHeap.sortArray(3, arr2);
		for (int n : arr2)
			System.out.print(n + ", ");
		System.out.println("\n");
		System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		try {
			System.out.println("max value extracted using deleteMax: "+  a2.deleteMax());
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		}
		try {
			System.out.println(" max value in the heap: " + a2.readMax());
		}
		catch (Exception e) {
			System.out.println("Message: "+e.getMessage());
		
		}
		System.out.println("************************************************************************");
		MaxMHeap a3= new MaxMHeap(1, 3);
		System.out.println(" Value of m in the heap: "+ a3.getM());
		System.out.println(" #items stored  in the heap: "+ a3.getSize()); 
		System.out.println("Output: "+ a3.toString());
		

	}

}
