package primsAlgoMinSpanTree;

public class Edge {

	int weight;
	Edge next; // next edge in adjacency list
	Vertex end; // the other end node
	
	public Edge(int w, Edge e, Vertex v) {
		weight = w;
		end = v;
		next = e;
	}//end constructor

}
