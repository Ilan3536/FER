package ui;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class StateSpaceSearchAlgorithms {		
	
	public static SolutionInfo BFS(String pathToSSDescriptor, String initialState) {
		StateSpaceDescriptor descriptor = StateSpaceDescriptor.parseSSDescriptor(pathToSSDescriptor);
		if (initialState!="") descriptor.initialState = initialState;
		
		List<Node> open = new LinkedList<>();
		List<Node> path = new LinkedList<>();
		Map<String,Node> visited = new HashMap<>();
		Boolean foundSolution = false;
		
		open.add(new Node(null, descriptor.initialState, 0.0));
		
		while (!open.isEmpty()) {
			Node current = open.remove(0);
			String currentStateName = current.getState();
			
			visited.put(currentStateName, current);
			if (descriptor.goalStates.contains(currentStateName)) {
				foundSolution = true;
				path = Node.nodePath(current);
				break;
			}
			
			for (String state : descriptor.successorFunction.get(currentStateName)) {
				
				Node nodeInVisited = visited.get(state.split(",")[0]);
				if (nodeInVisited == null) {					
					open.add(new Node(current, state.split(",")[0], Double.parseDouble(state.split(",")[1]) + current.getCost()));
				}
			}
		}
		
		SolutionInfo solution = new SolutionInfo(foundSolution, visited.size(), path);
		
		
		
		return solution;


	}
	
	public static SolutionInfo UCS(String pathToSSDescriptor, String initialState) {
		StateSpaceDescriptor descriptor = StateSpaceDescriptor.parseSSDescriptor(pathToSSDescriptor);
		if (initialState!="") descriptor.initialState = initialState;

		Queue<Node> open = new PriorityQueue<>();
		List<Node> path = new LinkedList<>();
		Map<String,Node> visited = new HashMap<>();

		Boolean foundSolution = false;
		
		open.add(new Node(null, descriptor.initialState, 0.0));
		
		while (!open.isEmpty()) {
			Node current = open.remove();
			String currentStateName = current.getState();
			
			visited.put(currentStateName, current);
			if (descriptor.goalStates.contains(currentStateName)) {
				foundSolution = true;
				path = Node.nodePath(current);
				break;
			}
			
			for (String state : descriptor.successorFunction.get(currentStateName)) {
				
				Node nodeInVisited = visited.get(state.split(",")[0]);
				if (nodeInVisited == null) {					 
					open.add(new Node(current, state.split(",")[0], Double.parseDouble(state.split(",")[1]) + current.getCost()));
				}
			}
		}
		
		SolutionInfo solution = new SolutionInfo(foundSolution, visited.size(), path);

		
		return solution;



	}
		
	
	public static SolutionInfo AStar(String pathToSSDescriptor, String pathToHDescriptor, String initialState) {
		StateSpaceDescriptor descriptor = StateSpaceDescriptor.parseSSandH(pathToSSDescriptor, pathToHDescriptor);
		if (initialState!="") descriptor.initialState = initialState;

		Queue<HNode> open = new PriorityQueue<HNode>(HNode.COMP);
		Map<String,HNode> openMap = new HashMap<>();

		List<Node> path = new LinkedList<>();
		Map<String,HNode> visited = new HashMap<>();
		Boolean foundSolution = false;
		
		HNode initialNode = new HNode(null, descriptor.initialState, 0.0, descriptor.heuristicDescriptor.get(descriptor.initialState));
		open.add(initialNode);
		openMap.put(initialNode.getState(), initialNode);
		while (!open.isEmpty()) {
			HNode current = open.remove();
			
			visited.put(current.getState(), current);
			if (descriptor.goalStates.contains(current.getState())) {
				foundSolution = true;
				path = Node.nodePath(current);
				break;
			}
			
			for (String state : descriptor.successorFunction.get(current.getState())) {
				
				String stateName = state.split(",")[0];
				Double stateCost = Double.parseDouble(state.split(",")[1]);
			
				HNode nextNode = new HNode(
						current, 
						stateName, 
						current.getCost() + stateCost, 
						current.getCost() + stateCost + descriptor.heuristicDescriptor.get(stateName) );
				
				HNode nodeInOpenMap  = openMap.get(nextNode.getState());
				if(nodeInOpenMap!=null && nodeInOpenMap.getCost() < nextNode.getCost()) {
					continue;
				}
				
				HNode nodeInVisited = visited.get(nextNode.getState());
				if(nodeInVisited!=null && nodeInVisited.getCost() < nextNode.getCost()) {
					continue;
				}
				
				if(nodeInOpenMap!=null) {
					openMap.remove(nodeInOpenMap.getState());
					open.remove(nodeInOpenMap);
				}
				
				if(nodeInVisited!=null) {
					visited.remove(nodeInVisited.getState());
				}
				
				open.add(nextNode);
				openMap.put(nextNode.getState(), nextNode);
				
			}
		}
		
		SolutionInfo solution = new SolutionInfo(foundSolution, visited.size(), path);

		
		return solution;

		
	}
		
	

	
	
}
