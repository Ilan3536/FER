package ui;

import java.util.Comparator;

public class Node {
	private static final Comparator<Node> BY_NAME = 
			((n1, n2) -> n1.getState().getName().compareTo(n2.getState().getName()));
	public static final Comparator<Node> NON_INFORMED_COMPARATOR = Comparator.comparing(Node::getCost)
																		     .thenComparing(BY_NAME);
	public static final Comparator<Node> INFORMED_COMPARATOR = Comparator.comparingDouble((Node n) -> n.getCost() + n.getState().getHeuristic())
																	     .thenComparing(BY_NAME);
	private Node parent;
	private State state;
	private double cost;
	
	public Node(Node parent, State state, double cost) {
		this.parent = parent;
		this.state = state;
		this.cost = cost;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public State getState() {
		return state;
	}
	
	public double getCost() {
		return cost;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)  return false;
		if (!(obj instanceof Node)) return false;
		Node other = (Node)obj;
		
		return this.state.getName().equals(other.state.getName()) && this.cost == other.cost;
	}
	
	@Override
	public String toString() {
		return String.format("Node: %s\n\tParent: %s\n\tCost: %f", 
							 state.getName(), 
							 parent != null ? parent.getState().getName() : null, 
							 cost);
	}
}
