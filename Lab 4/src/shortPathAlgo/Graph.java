package shortPathAlgo;
import java.util.Scanner;

/**** Written by OLUKEMI ODUJINRIN ****/
/**** COMPENG 3SM4 Winter 2023 ****/

public class Graph {
	
	/**********************************************PRIVATE METHODS**************************************************/
	private boolean hasNegWeights() {
		Edge p;
		
		for (int i = 0; i < size; i++) { // go through each edge
			p = vArray[i].adjList;
			while (p != null) {
				if (p.weight < 0) { // if the weight of this edge is negative
					return true;
				}
				p = p.next;
			}
		}
		return false;
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
				p = p.next; //move to next edge
			}//end while
		}// end for
		
		
		return adjTrix;
	}//end method
	
	public String dijkstraSP(int i, int j) {
		
		if (i == j) {
			return "path: v" + i + ", weight: 0";
		} else if (vArray[i].adjList == null) {
			return null;
		}
		
		String path = "";
		String weight = "weight: ";
		
		Vertex u;
		Edge p;

		MinBinHeap Q = new MinBinHeap (this, i); // use for priority queue like prim's
		
		for (int k = 0; k < size; k++) {
			u = Q.extractMin(); // extract from Q the vertex with smallest key
			u.isInQ = false; // update the information of the vertices in Q
			p = u.adjList; //p points to first edge in adjList of u
			while (p != null) {
				Vertex v = p.end;
				if (v.isInQ && (u.key + p.weight) < v.key) {
					v.key = u.key + p.weight;
					v.prev = u;
					Q.decreaseKey(v.heapIndex, u.key + p.weight); // v.d = u.d + c(u,v)
				}
				p = p.next; //move to next edge
			}
		}
		
		if (vArray[j].prev == null) {  // there is no path from vi to vj
			return null;
		}
		
		Vertex v = vArray[j]; // start at vj and work back works until you get to i
		while (v.prev != null) {
			path = "v" + v.label + ", " + path;
			v = v.prev;
		}
		if (v.label == i) {
			path = "path: v" + v.label + ", " + path; // add vi
			weight += vArray[j].key;
		} else {
			return null;
		}
		
		return path + weight;
	}
	
	public String bellmanFordSP(int i, int j) {
		
		if (i == j) {
			return "path: v" + i + ", weight: 0";
		} else if (vArray[i].adjList == null) {
			return null;
		}
		
		String path = "";
		String weight = "weight: ";
		
		//initialization
		for (int x = 0; x < size; x++) {
			if (x != i) {
				vArray[x].key = infinity; // d(v) = infinity
				vArray[x].prev = null; //prev(v) = null
			}
		}
		vArray[i].key = 0; // s.key = 0 or d(s) = 0
		vArray[i].prev = null; // s.prev = null or prev(s) = null
		
		
		Edge p;
		for (int x = 0; x < size; x++) { // x iterations (|V|-1)
			for (int k = 0; k < size; k++) { // for edge in u
				Vertex u = vArray[k];
				p = u.adjList; //p points to first edge in adjList of u
				while (p != null) {
					Vertex v = p.end;
					if ((u.key + p.weight) < v.key) {
						v.key = u.key + p.weight;
						v.prev = u;
					}
					p = p.next; //move to next edge
				}
			}
		}
		
		//check if there are any negative cycles
		for (int k = 0; k < size; k++) { // for edge in u
			Vertex u = vArray[k];
			p = u.adjList; //p points to first edge in adjList of u
			while (p != null) {
				Vertex v = p.end;
				if ((u.key + p.weight) < v.key) {
					return "negative-weight cycle!";
				}
				p = p.next; //move to next edge
			}
		}
		
		// there is no path from vi to vj
		if (vArray[j].prev == null) {  
			return null;
		}
		
		Vertex v = vArray[j]; // start at vj and work back works until you get to i
		while (v.prev != null) {
			path = "v" + v.label + ", " + path;
			v = v.prev;
		}
		
		if (v.label == i) {
			path = "path: v" + v.label + ", " + path; // add vi
			weight += vArray[j].key;
		} else {
			return null; // there is no path from vi to vj
		}
		
		return path + weight;
		
	}
	
	public String dagSP(int i, int j) {
		
		if (i == j) {
			return "path: v" + i + ", weight: 0";
		} else if (vArray[i].adjList == null) {
			return null;
		}
		
		String path = "";
		String weight = "weight: ";
		
		Edge p;
		int k = 0; // index of vi
		int e = 0; // index of vj
			
		//sort in topological order
		Vertex[] topo = topologicalSort();
		if (topo == null) {
			System.out.println("try another shortest path algo");
			return null;
		}
		
		//initialization
		for (int x = 0; x < topo.length; x++) { // v1, v2, v0 i = 2, j = 0
			if (topo[x].label != i) {
				if (topo[x].label == j) {
					e = x; // find index of vj
				}
				topo[x].key = infinity; // d(v) = infinity
				topo[x].prev = null; //prev(v) = null
			} else{
				k = x; // find index of vi
				topo[x].key = 0; // s.key = 0 or d(s) = 0
				topo[x].prev = null; // s.prev = null or prev(s) = null
			}
		}	
		
		for (int x = k; x < size; x++) {
			Vertex u = topo[x];
			p = u.adjList; //p points to first edge in adjList of v[i]
			while (p != null) {
				Vertex v = p.end; //v[j]
				if ((u.key + p.weight) < v.key) {
					v.key = u.key + p.weight;
					v.prev = u;
				}
				p = p.next; //move to next edge
			}
		}
		
		// there is no path from vi to vj
		if (topo[e].prev == null) {  
			return null;
		}
		
		Vertex v = topo[e]; // start at vj and work back works until you get to i
		while (v.prev != null) {
			path = "v" + v.label + ", " + path;
			v = v.prev;
		}
		
		if (v.label == i) {
			path = "path: v" + v.label + ", " + path; // add vi
			weight += topo[e].key;
		} else {
			return null; // there is no path from vi to vj
		}
		
		return path + weight;
	}
	
	Vertex[] topologicalSort() {
		
		// create empty queue with |V| capacity 
		Queue vQueue = new Queue(size);
		Vertex[] Z = new Vertex[size]; // an empty list Z
		
		// compute in degree, use v.key to store
		for (int i = 0; i < size; i++) {
			vArray[i].key = 0;
		}
		
		
		Edge p1;
		for (int j = 0; j < size; j++) {
			p1 = vArray[j].adjList; // u.adjList 
			while (p1 != null) {
				p1.end.key++; // v.key++ for all v adjacent to u
				p1 = p1.next;
			}
		}
		
		// enqueue all vertices with in degree 0 (v.key = 0)
		for (int k = 0; k < size; k++) {
			if (vArray[k].key == 0) {
				vQueue.enqueue(vArray[k]);
			}
		}

		Vertex u;
		Edge p2;
		for (int i = 0; i < size; i++) {
			if (vQueue.isEmpty()) {
				System.out.println("The graph has a cycle!");
				return null;
			} else {
				u = vQueue.dequeue();
				Z[i] = u;// insert u at the end of Z
				p2 = u.adjList; // u.adjList 
				while (p2 != null) {
					p2.end.key--; // remove edge (u,v) from graph
					if (p2.end.key == 0) {
						vQueue.enqueue(p2.end);
					}
					p2 = p2.next;
				}
			}
		}
		
		return Z; // vertices an can be in topological order
	}
	
	public String shortestPath(int i, int j) {
		
		String sp = "";
		
		if (hasNegWeights()){ // if negative weights - bellmanFordSP
			sp += "bellmanFordSP, " + bellmanFordSP(i,j);
		} else if (topologicalSort() != null) { // if positive and acyclic - dagSP
			sp += "dagSP, " + dagSP(i, j); 
		} else { // if non-negative and cyclic - dijkatraSP
			sp += "dijkstraSP, " + dijkstraSP(i,j);
		}
		
		return sp;
	}
	
	
	public static void main(String[] args) {
		String aGraph = "7 3 4 4 2 3 2 6 4 2 1 6 1 5 1 4 6 5 50 4 5 3 3 6 1 1 2 1 2 6 50";
		String aGraph1 = "4 1 2 1 3 2 3 0 3 2 0 1 -10 2 0 1";
		String aGraph2 = "6 4 5 3 2 1 -50 3 2 3 0 3 2 0 1 -10 0 2 10";
		
		
		Graph g = new Graph(aGraph);
		
		System.out.println("Number of vertices in aGraph:" + g.size);
		
		System.out.println("Edges of aGraph: " );
		System.out.println(g.adjListString());
		
		System.out.print("\n");
		System.out.println("Adjacency matrix of aGraph: \n" );
		int[][] a = g.adjMatrix();
		for(int i=0; i < a.length; i++) {
				for(int j=0; j<a[0].length; j++)
					System.out.print(" " + a[i][j]);
				System.out.print("\n");
		}//end for
			
		
		System.out.println("\n Testing dijkstraSP \n" );
		System.out.println(g.dijkstraSP(4,3));
		System.out.println();
		System.out.println(g.dijkstraSP(0,3));
		System.out.println();
		System.out.println(g.dijkstraSP(1,2));
		System.out.println();
		System.out.println(g.dijkstraSP(2,1));
		System.out.println();
		System.out.println(g.dijkstraSP(3,2));
		System.out.println();
		System.out.println(g.dijkstraSP(1,4));
		System.out.println();
		System.out.println(g.dijkstraSP(6,0));
		
		System.out.println("\n Testing bellmanFordSP \n" );
		System.out.println(g.bellmanFordSP(2,1));
		System.out.println();
		System.out.println(g.dijkstraSP(6,0));
		System.out.println();
		
		System.out.println("\n Testing bellmanFordSP with negative-weight cycle\n" );
		Graph g1 = new Graph(aGraph1);
		
		System.out.println("Number of vertices in aGraph1:" + g1.size);
		
		System.out.println("Edges of aGraph1: " );
		System.out.println(g1.adjListString());
		
		System.out.print("\n");
		System.out.println("Adjacency matrix of aGraph1: \n" );
		a = g1.adjMatrix();
		for(int i=0; i < a.length; i++) {
				for(int j=0; j<a[0].length; j++)
					System.out.print(" " + a[i][j]);
				System.out.print("\n");
		}//end for
		System.out.println();	
		

		System.out.println(g1.bellmanFordSP(3,2));
		System.out.println();
		
		
		System.out.println("\n Testing bellmanFordSP \n" );
		Graph g2 = new Graph(aGraph2);
		
		System.out.println("Number of vertices in aGraph2:" + g2.size);
		
		System.out.println("Edges of aGraph2: " );
		System.out.println(g2.adjListString());
		
		System.out.print("\n");
		System.out.println("Adjacency matrix of aGraph2: \n" );
		a = g2.adjMatrix();
		for(int i=0; i < a.length; i++) {
				for(int j=0; j<a[0].length; j++)
					System.out.print(" " + a[i][j]);
				System.out.print("\n");
		}//end for
		System.out.println();
	
		System.out.println(g2.bellmanFordSP(0,1));
		System.out.println();
		System.out.println(g2.bellmanFordSP(3,2));
		System.out.println();
		System.out.println(g2.bellmanFordSP(1,0));
		System.out.println();
		System.out.println(g2.bellmanFordSP(5,4));
		System.out.println();
		System.out.println(g2.bellmanFordSP(4,1));
		

	}
}
