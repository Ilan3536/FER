package ui;


import java.util.List;

public class SolutionInfo {

	private Boolean foundSolution;
	private int statesVisited;
	private int pathLength;
	private double totalCost;
	private String path;
	
	public SolutionInfo(Boolean foundSolution) {
		this.foundSolution = foundSolution;
	}
	
	public SolutionInfo(Boolean foundSolution, int statesVisited, List<Node> path) {
		super();
		if (!foundSolution) { new SolutionInfo(false); return; }
		this.foundSolution = foundSolution;
		this.statesVisited = statesVisited;
		this.pathLength = path.size();
		this.totalCost =  path.get(path.size()-1).cost;
		this.path = toString(path);
	}

	@Override
	public String toString() {
		
		if (foundSolution) {
			return "[FOUND_SOLUTION]: yes\n[STATES_VISITED]: " + statesVisited + "\n[PATH_LENGTH]: "
				+ pathLength + "\n[TOTAL_COST]: " + totalCost + "\n[PATH]: " + path;
		} 
		
		return "[FOUND_SOLUTION]: no";
	}


	private String toString(List<Node> path) {
		String pathAsString="";
		for (Node state : path) {
			pathAsString += state.state + " => ";
		}
		if (pathAsString.length() < 4) return "";
		pathAsString = pathAsString.substring(0, pathAsString.length()-4);
		return pathAsString;
	}

	public double getTotalCost() {
		return totalCost;
	}

	
	
	
	
	
	

}
