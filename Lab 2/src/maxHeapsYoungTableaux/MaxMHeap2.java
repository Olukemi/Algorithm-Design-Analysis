package maxHeapsYoungTableaux;

public class MaxMHeap2 {
	////INDEX AT 1
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
	public MaxMHeap2(int k, int n) { // creates an empty MaxMHeap
		
		if (k < 2) { 
			k = 3; // reset to 3
		}
		
		if (n < 10) {
			n = 10; // reset to 10
		}
		
		m = k; // assign m to k
		
		heap = new int[n+1]; // constructs an empty MaxMHeap
		
	}
	
	public MaxMHeap2(int k, int[] a) {
		if (k < 2) {
			k = 3; // reset to 3
		}
		m = k;
		
		size = a.length;
		heap = new int[size+1]; // constructs an empty MaxMHeap

		int i = 1;
		for (int n = 0; n < a.length; n++) {
			heap[i] = a[n];
			i++;
		}
		
		buildHeap(a);
		
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
		heap[0]= x; // the store key 
		
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
		
		percolateDown(1); // percolate
		
		return x; // return the max key that was deleted
	}
	
	public String toString() {
		
		String heapString = "";
		
		if (size == 0) {
			return "";
		}
		
		for (int i = 1; i < size+1; i++) {
			heapString += heap[i] + ", ";
		}
		return heapString;
	}
	
	public static void sortArray(int k, int[] a) {
		MaxMHeap2 h = new MaxMHeap2(k, a); // build a heap out of this input array with k children
		
		// sort from array a starting at h[1]
		while (h.size > 0) { 
			a[h.size-1] = h.deleteMax(); // stores the integers in increasing order
		}
		
		
	}
	public static void main(String[] args) {
		int[] arr1= new int[] { 1, 5, 4, 3,20, 18, 16, 14, 10, 12};
		int[] arr2= new int[] {5, 4, 3, 11, 35, 56};
		
		/*
		MaxMHeap2.sortArray(3, arr2);
		for (int n : arr2)
			System.out.print(n + ", ");
		
		
		System.out.println();
		MaxMHeap2 a2= new MaxMHeap2(3,arr1);    //creating MaxMHeap object using arr1
		
		System.out.println("Output  : "+ a2.toString());

		System.out.println("Expected m = 3 : "+"20, 18, 14, 3, 5, 1, 16, 4, 10, 12 ");
		System.out.println("-------------------------------------");
		System.out.println("Expected m = 4 : "+"20, 18, 12, 3, 1, 5, 16, 14, 10, 4, ");
		System.out.println("-------------------------------------");
		*/

		MaxMHeap2 a3= new MaxMHeap2(4,arr1);
		a3.insert(30); 
		System.out.println("Output  : "+ a3.toString());
	}

}
