package ui;

import java.util.Map;

public class HeuristicsChecker {
	
	public static boolean checkOptimistic(String pathToSSDescriptor, String pathToHDescriptor) {
		Map<String, Double> heuristicDescriptor = StateSpaceDescriptor.parseHDescriptor(pathToHDescriptor);
		
		double trueCost;
		boolean optimistic = true;
		for (var state : heuristicDescriptor.entrySet()) {
			trueCost = StateSpaceSearchAlgorithms.UCS(pathToSSDescriptor, state.getKey()).getTotalCost(); 

			//trueCost = StateSpaceSearchAlgorithms.AStar(pathToSSDescriptor, pathToHDescriptor, state.getKey()).getTotalCost(); 
			System.out.print("[CONDITION]: ");
			
			if (state.getValue() <= trueCost) {				
				System.out.print("[OK]");
			} else {
				optimistic = false;
				System.out.print("[ERR]");

			}
			System.out.print(" h(" + state.getKey() + ") <= h*: ");
			System.out.print(state.getValue() + " <= " + trueCost + "\n");
		}
		
		return optimistic;
		
	}
	
	public static boolean checkConsistent(String pathToSSDescriptor, String pathToHDescriptor) {
		StateSpaceDescriptor descriptor = StateSpaceDescriptor.parseSSandH(pathToSSDescriptor, pathToHDescriptor);
		Map<String, Double> heuristicDescriptor = StateSpaceDescriptor.parseHDescriptor(pathToHDescriptor);

		boolean consistent = true;
		
		for (var state : descriptor.successorFunction.entrySet()) {
			for (var nextState : state.getValue()) {
				String destination = nextState.split(",")[0];
				Double costToDestination = Double.parseDouble(nextState.split(",")[1]);
				System.out.print("[CONDITION]: ");
				
				Double srcHeuristic = heuristicDescriptor.get(state.getKey());
				Double dstHeuristic = heuristicDescriptor.get(destination);
				
				if (srcHeuristic <= dstHeuristic + costToDestination) {				
					System.out.print("[OK]");
				} else {
					consistent = false;
					System.out.print("[ERR]");

				}
				
				System.out.print(" h(" + state.getKey() + ") <= h(" + destination + ") + c: "  );
				System.out.print(srcHeuristic + " <= " + dstHeuristic + " + " + costToDestination + "\n");
			}
			
			
			
		}
		

		return consistent;
	}


}
