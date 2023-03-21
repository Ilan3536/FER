package ui;


public class Solution {

	public static void main(String ... args) {		
		int i = 0;
		
		String algorithm="";
		String pathToSSDescriptor="";
		String pathToHDescriptor="";
		Boolean optimistic = false;
		Boolean consistent = false;
		
		for(String arg : args) {			
			if (arg.equals("--alg")) {
				algorithm = args[i+1];
			}
			if (arg.equals("--ss")) {
				pathToSSDescriptor = args[i+1];
			}
			if (arg.equals("--h")) {
				pathToHDescriptor = args[i+1];
			}
			if (arg.equals("--check-optimistic")) {
				optimistic = true;
			}
			if (arg.equals("--check-consistent")) {
				consistent = true;
			}
			
			i++;
			
		}
		SolutionInfo solution;
		switch (algorithm) {
			case "astar": 
				solution =  StateSpaceSearchAlgorithms.AStar(pathToSSDescriptor, pathToHDescriptor, "");
				System.out.println("# A-STAR " + pathToHDescriptor);
				System.out.println(solution.toString());
				break;
			
			case "bfs": 
				solution =  StateSpaceSearchAlgorithms.BFS(pathToSSDescriptor, ""); 
				System.out.println("# BFS" );
				System.out.println(solution.toString());
				break;
			
			case "ucs": 
				solution =  StateSpaceSearchAlgorithms.UCS(pathToSSDescriptor, "");
				System.out.println("# UCS" );
				System.out.println(solution.toString());
				break;
			
		}
		
		
		if (optimistic) {
			System.out.println("# HEURISTIC-OPTIMISTIC " + pathToHDescriptor);
			optimistic = HeuristicsChecker.checkOptimistic(pathToSSDescriptor, pathToHDescriptor);
			System.out.println("[CONCLUSION]: Heuristic is " + (optimistic ? "" : "not") + " optimistic.");
		}
		
		if (consistent) {
			System.out.println("# HEURISTIC-CONSISTENT " + pathToHDescriptor);
			consistent = HeuristicsChecker.checkConsistent(pathToSSDescriptor, pathToHDescriptor);
			System.out.println("[CONCLUSION]: Heuristic is " + (consistent ? "" : "not") + " consistent.");
		}
		
		
		
	}

}
