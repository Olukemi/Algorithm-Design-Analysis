package primsAlgoMinSpanTree;
import java.util.Scanner;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class Graph {
	
	/**********************************************PRIVATE METHODS**************************************************/
	private int weight(Vertex u, Vertex v) {
		Edge p; // pointer to scan adjacency list of u
		p = u.adjList; 
		
		while (p != null) {
			if (p.end == v) { // edge (vertex v) is found
				return p.weight; // return weight from u to v
			} else {
				p = p.next; // check the next edge of u (next adjacent vertex of u)
			}
		}
		return 0;
	}
	/**********************************************PUBLIC METHODS**************************************************/
	
	public static int infinity = 10000; //logical infinity
	Vertex[] vArray; //array of vertices
	int size=0; //number of vertices
	
	//Constructor: construct the graph described by inputString;
	//the string contains only non-negative
	//integers separated by white spaces; the first integer is
	//the number of vertices; each of the following triple of integers
	//specifies an edge, namely end1, end2 and weight; you may assume
	//that the input string respects the format
	public Graph(String inputString) {
		Scanner input = new Scanner(inputString);
		size = input.nextInt();
		vArray = new Vertex[size];
		// fill the array of vertices; create the Vertex objects
		for (int i = 0; i < size; i++) {
			vArray[i] = new Vertex(i);
		}
		//read the info from the string
		int end1;
		int end2; 
		int w;
		while(input.hasNext()) {
			//read next edge
			end1 = input.nextInt();
			end2 = input.nextInt();
			w = input.nextInt();
			//create two edge objects; insert them in adjList of
			//end1 and end2, at the beginning
			vArray[end1].adjList = new Edge (w, vArray[end1].adjList, vArray[end2]); // 9, v2.adjList, v3
			vArray[end2].adjList = new Edge (w, vArray[end2].adjList, vArray[end1]); // 9, v3.adjList, v2
		}//end while
	}// end constructor
	
	public String adjListString() {
		Edge p; //edge pointer
		String s = " ";
		for(int i=0; i<size; i++) {
			p = vArray[i].adjList; //p points to first edge in adjList of vertex i
			while(p != null) {
				s += " \n edge: (v" + i +", v" + p.end.label + "), weight: " + p.weight;
				p = p.next; //move to next edge
			}//end while
		}// end for
		return s;
	} // end method
	
	
	//minST: find an MST using Prim’s algorithm, where the starting vertex
	//has label r; return a string that lists all edges in the MST,
	//in the order they were found; see the output of the test class
	//for clarification on the format of the string
	public String minST(int r) {
		String minSpanTree = "MST: ";
		
		Vertex u;
		Edge p; // pointer to adjacency list
		
		MinBinHeap Q = new MinBinHeap(this, r);
		
		for(int i = 0; i< size; i++) {
			// extract from Q the vertex with smallest key
			u = Q.extractMin();
			u.isInQ = false;
			// update the information of the vertices in Q
			p = u.adjList; //p points to first edge in adjList of u
			while(p != null) { // scan adjacency list of u
				Vertex v = p.end;
				if (v.isInQ == true && weight(u,v) < v.key) {
					v.prev = u;
					v.key = weight(u,v);
					Q.decreaseKey(v.heapIndex,v.key); // restore the min-priority queue property
				}
				p = p.next; //move to next edge
			}//end while
			if (u.label != r) {
				minSpanTree += "(v" + u.label + ", v" + u.prev.label + "), ";
			}
		}// end for
		
		return minSpanTree;
		
	}//end method
		
	//return a two-dim array that represents the adjacency matrix of the graph
	public int[][] adjMatrix(){
		int[][] adjTrix = new int [size][size];
		
		//fill matrix with infinities 
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				adjTrix[r][c] = infinity;
			}
		}
		
		Edge p; //edge pointer
		for(int i=0; i<size; i++) {
			p = vArray[i].adjList; //p points to first edge in adjList of vertex i
			while(p != null) {
				adjTrix[i][p.end.label] = p.weight; // v1, v2 = 2
				adjTrix[p.end.label][i] = p.weight; // v2, v1 = 2
				p = p.next; //move to next edge
			}//end while
		}// end for
		
		
		return adjTrix;
	}//end method
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
