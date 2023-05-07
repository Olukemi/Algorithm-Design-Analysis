package shortPathAlgo;

public class Test {
	public static void main(String[] args) {
		
		String aGraph1 = "7 5 6 8 0 5 10 6 0 1 1 6 30 0 1 2 2 0 4 2 1 5 2 3 6 0 3 12 0 4 7 4 3 3 4 5 25"; // has 1 cycle
		String aGraph2 = "4 0 2 10 1 0 10 2 1 5 1 3 -20 2 3 -5"; // neg
		String aGraph3 = "3 1 2 36 2 0 24 1 0 50"; // no cycle
		String aGraph4 = "7 3 4 4 2 3 2 6 4 2 1 6 1 5 1 4 6 5 50 4 5 3 3 6 1 1 2 1 2 6 50"; // has cycle
		String aGraph5 = "4 1 2 1 3 2 3 0 3 2 0 1 -10 2 0 1"; // neg weight cycle
		String aGraph6 = "6 4 5 3 2 1 -50 3 2 3 0 3 2 0 1 -10 0 2 10"; // neg
		String aGraph7 = "7 5 6 8 0 5 10 1 6 30 0 1 2 2 0 4 2 1 5 2 3 6 0 3 12 0 4 7 4 3 3 4 5 25"; // no cycle
		String aGraph8 = "3 1 2 36 2 0 24 0 1 50"; // has cycle
		String aGraph9 = "5 1 2 36 2 3 24 3 1 50 1 0 43 0 2 3 2 4 5 0 4 1"; // has cycle
		
		
		Graph g = new Graph(aGraph1);
	
		System.out.println("Testing Dijkstra");
		/*
		System.out.println("Graph 4");
		g = new Graph(aGraph4);
		System.out.println(g.dijkstraSP(4,3));
		System.out.println(g.dijkstraSP(0,3));
		System.out.println(g.dijkstraSP(1,2));
		System.out.println(g.dijkstraSP(2,1));
		System.out.println(g.dijkstraSP(3,2));
		System.out.println(g.dijkstraSP(1,4));
		System.out.println(g.dijkstraSP(6,0));
		*/
		g = new Graph(aGraph1);
		System.out.println(g.dijkstraSP(3, 1));
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Testing Bellman Ford");
		/*
		System.out.println("Graph 4");
		g = new Graph(aGraph4);
		System.out.println(g.bellmanFordSP(2,1));
		System.out.println("Graph 5");
		g = new Graph(aGraph5);
		System.out.println(g.bellmanFordSP(3,2));
		System.out.println("Graph 6");
		g = new Graph(aGraph6);
		System.out.println(g.bellmanFordSP(0,1));
		System.out.println(g.bellmanFordSP(3,2));
		System.out.println(g.bellmanFordSP(1,0));
		System.out.println(g.bellmanFordSP(5,4));
		System.out.println(g.bellmanFordSP(4,1));
		*/ 
		g = new Graph(aGraph2);
		System.out.println(g.bellmanFordSP(1,1));
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Testing Dag");
		g = new Graph(aGraph7);
		System.out.println(g.dagSP(2, 5));
		
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Testing Shortest Path");
		g = new Graph(aGraph1);
		System.out.println(g.shortestPath(4, 5));
		System.out.println("");
		g = new Graph(aGraph2);
		System.out.println(g.shortestPath(0, 3));
		System.out.println("");
		g = new Graph(aGraph3);
		System.out.println(g.shortestPath(2, 0));
		System.out.println("");
		g = new Graph(aGraph4);
		System.out.println(g.shortestPath(6, 0));
		System.out.println("");
		g = new Graph(aGraph5);
		System.out.println(g.shortestPath(2, 1));
		System.out.println("");
		g = new Graph(aGraph6);
		System.out.println(g.shortestPath(5, 1));
		System.out.println("");
		g = new Graph(aGraph7);
		System.out.println(g.shortestPath(3, 2));
		System.out.println("");
		g = new Graph(aGraph8);
		System.out.println(g.shortestPath(0, 1));
		System.out.println("");
		g = new Graph(aGraph9);
		System.out.println(g.shortestPath(1, 4));
		System.out.println("");
		
		
		
		/*
		System.out.println("Number of vertices in aGraph1:" + g.size);
		
		System.out.println("Edges of aGraph1: " );
		System.out.println(g.adjListString());
		
		System.out.print("\n");
		System.out.println("Adjacency matrix of aGraph1: \n" );
		int[][] a = g.adjMatrix();
		for(int i=0; i < a.length; i++) {
				for(int j=0; j<a[0].length; j++)
					System.out.print(" " + a[i][j]);
				System.out.print("\n");
		}//end for
		
		g = new Graph(aGraph2);
		
		System.out.println("\n \nNumber of vertices in aGraph2:" + g.size);
		
		System.out.println("Edges of aGraph2: " );
		System.out.println(g.adjListString());
		
		System.out.print("\n");
		System.out.println("Adjacency matrix of aGraph2: \n" );
		a = g.adjMatrix();
		for(int i=0; i < a.length; i++) {
				for(int j=0; j<a[0].length; j++)
					System.out.print(" " + a[i][j]);
				System.out.print("\n");
		}//end for
		
		
		g = new Graph(aGraph3);
		
		System.out.println("\n \nNumber of vertices in aGraph3:" + g.size);
		
		System.out.println("Edges of aGraph3: " );
		System.out.println(g.adjListString());
		
		System.out.print("\n");
		System.out.println("Adjacency matrix of aGraph3: \n" );
		a = g.adjMatrix();
		for(int i=0; i < a.length; i++) {
				for(int j=0; j<a[0].length; j++)
					System.out.print(" " + a[i][j]);
				System.out.print("\n");
		}//end for
		*/

		
		
	}//end main
	
}//end class