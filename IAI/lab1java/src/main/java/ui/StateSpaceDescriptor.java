package ui;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StateSpaceDescriptor {
	
	String initialState;
	List<String> goalStates;
	Map<String, List<String>> successorFunction;
	Map<String, Double> heuristicDescriptor;
	
	
	
	public StateSpaceDescriptor(String initialState, List<String> goalStates,
			Map<String, List<String>> successorFunction) {
		super();
		this.initialState = initialState;
		this.goalStates = goalStates;
		this.successorFunction = new TreeMap<>(successorFunction);
	}
	
	
	

	public Map<String, Double> getHeuristicDescriptor() {
		return heuristicDescriptor;
	}




	public void setHeuristicDescriptor(Map<String, Double> heuristicDescriptor) {
		this.heuristicDescriptor = new HashMap<>(heuristicDescriptor);
	}




	public static StateSpaceDescriptor parseSSDescriptor(String pathToDescriptor) {
		
		List<String> allLines;
		
		try {
			allLines = Files.readAllLines(Paths.get("C:\\FER\\6. semestar\\IAI\\autograder\\data\\lab1\\files\\" + pathToDescriptor));
			
			Map<String, List<String>> successorFunction = new TreeMap<>();
			
			int i = 0;
			while(allLines.get(i).startsWith("#")) {
				i++;
			}
			String initialState = allLines.get(i++);
			
			while(allLines.get(i).startsWith("#")) {
				i++;
			}
			List<String> goalStates = Arrays.asList(allLines.get(i++).split(" "));
			
			while ( i < allLines.size()) {
				if (allLines.get(i).startsWith("#")) continue;
				String[] succ = allLines.get(i++).split(": ");
				String node = succ[0];
				
				List<String> nextNodes = new ArrayList<>();
				if (succ.length==1) {
					successorFunction.put(node, nextNodes);
					continue;
				}
				
				nextNodes = Arrays.asList(succ[1].split(" "));
				
				Collections.sort(nextNodes);
				successorFunction.put(node, nextNodes);
				
			}
			
			return new StateSpaceDescriptor(initialState, goalStates, successorFunction);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return null;
		
	}


	public static StateSpaceDescriptor parseSSandH(String pathToSSDescriptor, String pathToHDescriptor) {
		StateSpaceDescriptor descriptor =  parseSSDescriptor(pathToSSDescriptor);
		Map<String, Double> heuristicDescriptor = parseHDescriptor(pathToHDescriptor);
		
		
		descriptor.setHeuristicDescriptor(heuristicDescriptor);
			
		
		return descriptor;
	}




	public static Map<String, Double> parseHDescriptor(String pathToHDescriptor) {
		Map<String, Double> heuristicDescriptor = new HashMap<>();
		List<String> allLines;

		try {
			allLines = Files.readAllLines(Paths.get("C:\\FER\\6. semestar\\IAI\\autograder\\data\\lab1\\files\\" + pathToHDescriptor));
			
			int i=0;
			while ( i < allLines.size()) {
				if (allLines.get(i).startsWith("#")) continue;
				String[] heuristic = allLines.get(i++).split(": ");
				
				heuristicDescriptor.put(heuristic[0], Double.parseDouble(heuristic[1]));
			}
			return heuristicDescriptor;

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
}

}
