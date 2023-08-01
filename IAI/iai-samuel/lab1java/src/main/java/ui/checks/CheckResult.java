package ui.checks;

public abstract class CheckResult {
	private String checkState;
	private String finalState;
	private double heuristicStart;
	private double cost;
	boolean checkPassed;
	
	public CheckResult(String checkState, String finalState, double heuristicStart, double cost, boolean checkPassed) {
		this.checkState = checkState;
		this.finalState = finalState;
		this.heuristicStart = heuristicStart;
		this.cost = cost;
		this.checkPassed = checkPassed;
	}

	public String getCheckState() {
		return checkState;
	}

	public String getFinalState() {
		return finalState;
	}

	public double getHeuristicStart() {
		return heuristicStart;
	}

	public double getCost() {
		return cost;
	}

	public boolean isCheckPassed() {
		return checkPassed;
	}
	
	@Override
	public abstract String toString();
}
