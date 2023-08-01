package ui.checks;

import java.util.ArrayList;
import java.util.List;

import ui.State;
import ui.StateSpaceDescriptor;
import ui.Successor;

public class ConsistentCheck extends CheckResult {
	private double heuristicFinal;
	private ConsistentCheck(String checkState, String finalState, double heuristicStart, double heuristicFinal, double cost, boolean checkPassed) {
		super(checkState, finalState, heuristicStart, cost, checkPassed);
		this.heuristicFinal = heuristicFinal;
	}

	public static List<CheckResult> check(StateSpaceDescriptor ssd) {
		List<CheckResult> results = new ArrayList<>();
		for (State state : ssd.getStates().values()) {
			for (Successor successor : state.getSuccessors()) {
				State nextState = ssd.getStates().get(successor.getNextState());
				int heuristicStart = state.getHeuristic();
				int heuristicFinal = nextState.getHeuristic();
				double cost = successor.getCost();
				results.add(new ConsistentCheck(String.format("h(%s)", state.getName()),
												String.format("h(%s)", nextState.getName()),
											    heuristicStart,
											    heuristicFinal,
											    cost,
											    heuristicStart <= heuristicFinal + cost));
			}
		}
		
		return results;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[CONDITION]: [").append(isCheckPassed() ? "OK] " : "ERR] ")
		  .append(getCheckState()).append(" <= ").append(getFinalState()).append(" + c: ")
		  .append(String.format("%.1f <= %.1f + %.1f\n", getHeuristicStart(), heuristicFinal, getCost()));
		return sb.toString();
	}
}
