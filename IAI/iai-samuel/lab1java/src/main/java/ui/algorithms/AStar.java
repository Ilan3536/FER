package ui.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import ui.Node;
import ui.State;
import ui.StateSpaceDescriptor;
import ui.Successor;

public class AStar {
	public static AlgorithmResult search(StateSpaceDescriptor ssd) {
		State startState = ssd.getStates().get(ssd.getStart());
		Set<State> finishStates = new HashSet<>();
		finishStates.addAll(ssd.getFinish()
							   .stream()
							   .map(state -> ssd.getStates().get(state))
							   .collect(Collectors.toList()));
		int statesVisited = 0;
		double totalCost = 0;
		
		PriorityQueue<Node> openQueue = new PriorityQueue<>(Node.INFORMED_COMPARATOR);
		Map<String, Node> open = new HashMap<>();
		Map<String, Node> closed = new HashMap<>();

		Node startNode = new Node(null, startState, totalCost);
		openQueue.add(startNode);
		open.put(startNode.getState().getName(), startNode);
		
		while (!openQueue.isEmpty()) {
			Node node = openQueue.remove();
			String nodeName = node.getState().getName();
			open.remove(nodeName);
			closed.put(nodeName, node);
			statesVisited += 1;
			totalCost = node.getCost();
			
			if (finishStates.contains(node.getState())) {
				return new AlgorithmResult(node, totalCost, statesVisited);
			}
			for (Successor successor : node.getState().getSuccessors()) {
				if (successor.getNextState() == null) continue;
				Node nextNode = new Node(node, 
									     ssd.getStates().get(successor.getNextState()),
									     totalCost+successor.getCost());
				String nextNodeName = nextNode.getState().getName();
	
				Node equalStateNode = open.get(nextNodeName);
				if (equalStateNode != null) {
					if (equalStateNode.getCost() < nextNode.getCost()) continue;
					openQueue.remove(equalStateNode);
					open.remove(equalStateNode.getState().getName());
				}		
				equalStateNode = closed.get(nextNodeName);								
				if (equalStateNode != null) {
					if (equalStateNode.getCost() < nextNode.getCost()) continue;
					closed.remove(equalStateNode.getState().getName());
				}				
				openQueue.add(nextNode);
				open.put(nextNodeName, nextNode);
			}
		}
		return null;
	}
}
