package ui.algorithms;

import java.util.LinkedList;
import java.util.List;

import ui.Node;

public class AlgorithmResult {
	private Node finalNode;
	private double totalCost = 0;
	private int statesVisited = 0;
	private List<String> path;

	public AlgorithmResult(Node finalNode, double totalCost, int statesVisited) {
		this.finalNode = finalNode;
		this.totalCost = totalCost;
		this.statesVisited = statesVisited;
		this.path = new LinkedList<>();
		while (finalNode != null) {
			path.add(0, finalNode.getState().getName());
			finalNode = finalNode.getParent();
		}
	}

	public Node getFinalNode() {
		return finalNode;
	}
	
	public double getTotalCost() {
		return totalCost;
	}

	public int getStatesVisited() {
		return statesVisited;
	}
	
	public int getPathLength() {
		return path.size();
	}
	
	public String printPath() {
		return String.join(" => ", path);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[FOUND_SOLUTION]: yes")
		  .append("\n[STATES_VISITED]: ").append(statesVisited)
		  .append("\n[PATH_LENGTH]: ").append(path.size())
		  .append(String.format("\n[TOTAL_COST]: %.1f", totalCost))
		  .append("\n[PATH]: ").append(printPath()).append("\n");
		
		return sb.toString();
	}
}
