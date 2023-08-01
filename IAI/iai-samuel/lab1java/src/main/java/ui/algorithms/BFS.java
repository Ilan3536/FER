package ui.algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import ui.Node;
import ui.State;
import ui.StateSpaceDescriptor;
import ui.Successor;

public class BFS {
	private static final int BFS_COST = 0;

	public static AlgorithmResult search(StateSpaceDescriptor ssd) {
		State startState = ssd.getStates().get(ssd.getStart());
		Set<State> finishStates = new HashSet<>();
		finishStates.addAll(ssd.getFinish()
							   .stream()
							   .map(state -> ssd.getStates().get(state))
							   .collect(Collectors.toList()));
		int statesVisited = 0;
		int totalCost = 0;
		
		Queue<Node> openQueue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		
		Node startNode = new Node(null, startState, BFS_COST);
		openQueue.add(startNode);
		visited.add(startNode.getState().getName());
		
		while (!openQueue.isEmpty()) {
			Node node = openQueue.remove();
			statesVisited += 1;
			
			if (finishStates.contains(node.getState())) {
				return new AlgorithmResult(node, totalCost, statesVisited);
			}
			for (Successor successor : node.getState().getSuccessors()) {
				if (successor.getNextState() == null) continue;
				if (visited.contains(successor.getNextState())) continue;
				Node newNode = new Node(node, 
										ssd.getStates().get(successor.getNextState()), 
										BFS_COST);
				
				openQueue.add(newNode);
				visited.add(successor.getNextState());
			}
		}
		return null;
	}
}
