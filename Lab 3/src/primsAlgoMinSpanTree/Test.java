package primsAlgoMinSpanTree;

import java.io.*;

public class Test {
	public static void main(String[] args) {
		
		String aGraph1 = "7 5 6 8 0 5 10 6 0 1 1 6 30 0 1 2 2 0 4 2 1 5 2 3 6 0 3 12 0 4 7 4 3 3 4 5 25";
		String aGraph = "5 1 2 36 2 3 24 3 1 50 1 0 43 0 2 3 2 4 5 0 4 1";
		
		
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
		
		System.out.println("\nMinimum spanning tree in aGraph output by Prim's algorithm with different starting vertices: \n" );
		System.out.println(g.minST(0));
		
		g = new Graph(aGraph);
		System.out.println(g.minST(1));
		
		g = new Graph(aGraph);
		System.out.println(g.minST(2));
		
		g = new Graph(aGraph);
		System.out.println(g.minST(3));
		
		g = new Graph(aGraph);
		System.out.println(g.minST(4));
		
		
		g = new Graph(aGraph1);
		
		System.out.println("\n \nNumber of vertices in aGraph1:" + g.size);
		
		System.out.println("Edges of aGraph1: " );
		System.out.println(g.adjListString());
		
		System.out.print("\n");
		System.out.println("Adjacency matrix of aGraph1: \n" );
		a = g.adjMatrix();
		for(int i=0; i < a.length; i++) {
				for(int j=0; j<a[0].length; j++)
					System.out.print(" " + a[i][j]);
				System.out.print("\n");
		}//end for
		
		System.out.println("\nMinimum spanning tree in aGraph1 output by Prim's algorithm with different starting vertices: \n" );
		System.out.println(g.minST(0));
		
		g = new Graph(aGraph1);
		System.out.println(g.minST(1));
		
		g = new Graph(aGraph1);
		System.out.println(g.minST(2));
		
		g = new Graph(aGraph1);
		System.out.println(g.minST(3));
		
		g = new Graph(aGraph1);
		System.out.println(g.minST(4));
		
		
		g = new Graph(aGraph1);
		System.out.println(g.minST(5));
		
		g = new Graph(aGraph1);
		System.out.println(g.minST(6));
		
		
	}//end main
	
}//end class