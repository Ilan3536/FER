package ui.checks;

import java.util.ArrayList;
import java.util.List;

import ui.State;
import ui.StateSpaceDescriptor;
import ui.algorithms.AlgorithmResult;
import ui.algorithms.UCS;

public class OptimisticCheck extends CheckResult {
	private OptimisticCheck(String checkState, String finalState, double heuristicStart, double cost, boolean checkPassed) {
		super(checkState, finalState, heuristicStart, cost, checkPassed);
	}

	public static List<CheckResult> check(StateSpaceDescriptor ssd) {
		List<CheckResult> results = new ArrayList<>();
		for (State state : ssd.getStates().values()) {
			ssd.setStart(state.getName());
			AlgorithmResult result = UCS.search(ssd);
			double cost = result.getTotalCost();
			int heuristic = state.getHeuristic();
			
			results.add(new OptimisticCheck(String.format("h(%s)", state.getName()), "h*", heuristic, cost, heuristic <= cost));
		}
		
		return results;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[CONDITION]: [").append(isCheckPassed() ? "OK] " : "ERR] ")
		  .append(getCheckState()).append(" <= ").append(getFinalState()).append(": ")
		  .append(String.format("%.1f <= %.1f\n", getHeuristicStart(), getCost()));
		return sb.toString();
	}
}
