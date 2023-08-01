package ui.algorithms;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import ui.Node;
import ui.State;
import ui.StateSpaceDescriptor;
import ui.Successor;

public class UCS {
	public static AlgorithmResult search(StateSpaceDescriptor ssd) {
		State startState = ssd.getStates().get(ssd.getStart());
		Set<State> finishStates = new HashSet<>();
		finishStates.addAll(ssd.getFinish()
							   .stream()
							   .map(state -> ssd.getStates().get(state))
							   .collect(Collectors.toList()));
		int statesVisited = 0;
		double totalCost = 0;
		
		PriorityQueue<Node> openQueue = new PriorityQueue<>(Node.NON_INFORMED_COMPARATOR);
		Set<String> visited = new HashSet<>();
		
		Node startNode = new Node(null, startState, totalCost);
		openQueue.add(startNode);
		
		while (!openQueue.isEmpty()) {
			Node node = openQueue.remove();
			while (visited.contains(node.getState().getName())) {
				node = openQueue.remove();
			}

			visited.add(node.getState().getName());
			totalCost = node.getCost();
			statesVisited += 1;
			
			if (finishStates.contains(node.getState())) {
				return new AlgorithmResult(node, totalCost, statesVisited);
			}
			for (Successor successor : node.getState().getSuccessors()) {
				if (successor.getNextState() == null) continue;
				if (visited.contains(successor.getNextState())) continue;
				Node newNode = new Node(node, 
										ssd.getStates().get(successor.getNextState()), 
										totalCost+successor.getCost());
				
				openQueue.add(newNode);
			}
		}
		return null;
	}
}
