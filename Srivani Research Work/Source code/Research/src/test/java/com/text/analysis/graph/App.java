package com.text.analysis.graph;

class StackX {
	private final int SIZE = 20;
	private int[] st;
	private int top;

	public StackX() {
		st = new int[SIZE]; // make array
		top = -1;
	}
	public void push(int j)
	{
		st[++top] = j;
	}
	public int pop() {
		return st[top--];
	}
	public int peek() {
		return st[top];
	}
	public boolean isEmpty() {
		return (top == -1);
	}
}

class GraphA {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // array of vertices
	public int adjMat[][]; // adjacency matrix
	public int nVerts; // current number of vertices
	@SuppressWarnings("unused")
	private StackX theStack;

	public GraphA() // constructor       
	{
		vertexList = new Vertex[MAX_VERTS];
		// adjacency matrix
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for (int y = 0; y < MAX_VERTS; y++) // set adjacency
			for (int x = 0; x < MAX_VERTS; x++) //    matrix to 0
				adjMat[x][y] = 0;
		theStack = new StackX();
	}
	public void addVertex(char lab) {
		vertexList[nVerts++] = new Vertex(lab);
	}
	public void addEdge(int start, int end) {
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	public void displayVertex(int v) {
		System.out.print(vertexList[v].label);
	}
}

class Vertex {
	public char label; // label (e.g. 'A')
	public boolean wasVisited;
	public Vertex(char lab) // constructor
	{
		label = lab;
		wasVisited = false;
	}
}

class App {
	public static void main(String[] args) {
		GraphA theGraph = new GraphA();
		theGraph.addVertex('A'); // 0
		theGraph.addVertex('B'); // 1
		theGraph.addVertex('C'); // 2
		theGraph.addVertex('D'); // 3
		theGraph.addVertex('E'); // 4
		theGraph.addEdge(0, 1); // AB
		theGraph.addEdge(1, 2); // BC
		theGraph.addEdge(0, 3); // AD
		theGraph.addEdge(3, 4); // DE

		////////////////// Print Vertex //////////////////
		System.out.print( " List of Vertex: ");
		for (int i = 0; i < theGraph.nVerts; i++)
			theGraph.displayVertex(i);
		System.out.println();
		System.out.println();

		//////////////////////////////////////////////////
		//////////////// Print Adjency Matrix ////////////
		System.out.println( " Adjency Matrix: ");
		for (int j = 0; j < theGraph.nVerts; j++) {
			for (int k = 0; k < theGraph.nVerts; k++)
				System.out.print(theGraph.adjMat[j][k] + " ");
			System.out.println();
		}
		//////////////////////////////////////////////////
	}
}