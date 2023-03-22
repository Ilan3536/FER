package ui;

import java.util.LinkedList;
import java.util.List;


public class Node implements Comparable<Node>{

	protected Node parent;
	
	protected String state;
	
	protected Double cost;

	

	public Node(Node parent, String state, Double cost) {
		super();
		this.parent = parent;
		this.state = state;
		this.cost = cost;
	}

	public Node getParent() {
		return parent;
	}
	
	public String getState() {
		return state;
	}
	
	public Double getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return String.format("(%s)", state);
	}

	public static List<Node> nodePath(Node node) { 
		List<Node> path = new LinkedList<>();
		nodePathRecursive(path, node);
		return path;
	}

	private static void nodePathRecursive(List<Node> path, Node node) {
		if(node.getParent()!=null) {
			nodePathRecursive(path, node.getParent());
	
		}
		path.add(node);
		
		
	}

	@Override
	public int compareTo(Node o) {

		return (int) (this.cost - o.cost);
	}
}
