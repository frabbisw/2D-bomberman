package spl;
import java.util.LinkedList;
import java.util.Queue;

public class Node {
	int x,y;
	boolean visited=false;
	Node parent;
	Queue <Node> neighbors;
	
	public Node(int x, int y)
	{
		this.x=x;
		this.y=y;
		neighbors = new LinkedList<Node>();
	}
	
	public void addNeighbor(Node newNeighbor)
	{
		neighbors.add(newNeighbor);
	}
}