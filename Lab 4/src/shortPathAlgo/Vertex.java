package shortPathAlgo;

public class Vertex {
	Edge adjList; //header of the list of adjacent vertices
	Vertex prev; //previous vertex in MST
	int label; //vertex label in array of vertices
	int heapIndex; //index in heap array
	int key; // key in min-heap
	boolean isInQ = true; //true when vertex is in min-heap
	
	public Vertex(int l){
		label = l;
		key = Graph.infinity;
	}//end constructor
}
