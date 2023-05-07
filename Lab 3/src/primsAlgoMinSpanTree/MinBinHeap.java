package primsAlgoMinSpanTree;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/


public class MinBinHeap {
	
	/**********************************************PRIVATE METHODS**************************************************/
	
	private Vertex[] heap;
	private int size = 0;
	
	private void percolateDown(int i) {
		int child = 2*i; 
		Vertex v = heap[i];
		int vKey = heap[i].key;
		
		while (child <= size) {
			if (child < size && heap[child+1].key < heap[child].key) {
				child++; // child with the smallest vertex key
			}
			if (heap[child].key < vKey) {
				heap[i] = heap[child]; // move vertex key of child up at parent
				heap[i].heapIndex = i; // update heapIndex
				i = child; // move hole down to child
				child = 2*i; //  update child
			} else {
				break;
			}
		}
		
		heap[i] = v;
		heap[i].heapIndex = i;
	}
	
	/**********************************************PUBLIC METHODS**************************************************/
	
	//Constructor: set the key of vertex with label r to 0 and place that vertex
	//at the root; set the keys of the remaining vertices
	//to logical infinity and copy them in the heap;
	//for each vertex initialize heapIndex appropriately
	public MinBinHeap(Graph g, int r) {
		
		size = g.size; // number of vertices
		g.vArray[r].key = 0; // r.key = 0
		g.vArray[r].isInQ = true;
		g.vArray[r].prev = null;
		
		//allocate size for heap
		heap = new Vertex[size+1];
		
		//add to heap
		heap[1] = g.vArray[r]; // initialize root
		heap[1].heapIndex = 1; // initialize heapIndex r.heapIndex = 1
		int j = 2;
		
		// for all v in V initialize
		for (int i = 0; i < g.size; i++) {
			if (i != r) {
				g.vArray[i].key = Graph.infinity; // v.key = infinity
				g.vArray[i].prev = null; // v.prev = null
				g.vArray[i].isInQ = true; // v.isInQ = true
				//add to heap
				heap[j] = g.vArray[i];
				heap[j].heapIndex = j; // update it's heapIndex
				j++;
			}
			//skip vArray[r]
		}
		
		
	}//end constructor
	//extractMin: return the vertex with the smallest key and remove it from
	//the heap; note that every time a change is made in the heap,
	//the heapIndex of any vertex involved in the change has to be updated
	Vertex extractMin() {
		if (size == 0) {
			return null;
		}
		Vertex v = heap[1]; // get the max key to return
		heap[1] = heap[size--];// promote last leaf to root and decrease size

		percolateDown(1); // percolate root down to it's correct location
		
		return v; // return the max key that was deleted
	}//end method
	//decreasesKey: decrease the key of Vertex at index i in the heap;
	//newKey is the new value of the key; newKey is smaller than the old key
	//note that the heap may need to be reorganized
	void decreaseKey(int i, int newKey) { // i = heapIndex, newKey = key
		
		int j = i; // get the vertex at heapIndex
		heap[0] = heap[i];
		
		//percolate the hole up
		while (newKey < heap[j/2].key) { // while the value of newKey is less than parent
			heap[j] = heap[j/2]; //parent moves to child
			heap[j].heapIndex = j; // update heapIndex
			j = j/2; // promote child to parent (hole moves up)
		}
		heap[j] = heap[0]; // place vertex in its correct location
		heap[j].heapIndex = j; // update heapIndex
		
	}//end method
	
	public String toString(){
		String s = "\n The heap size is " + size + "\n The items’ labels are: \n";
		for(int i=1; i < size+1; i++) {
			s += heap[i].label + " key: ";
			s += heap[i].key + "\n";
		} //end for
		return s;
	}//end method

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
