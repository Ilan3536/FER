package ui.result;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ui.logic.Clause;

public class ResolutionResult {
	private Set<ClauseLog> premisesLogs;
	private Clause last;
	private String goal;
	private boolean conclusion;
	
	public ResolutionResult(Set<Clause> premises, Clause last, String goal, boolean conclusion) {
		this.premisesLogs = new LinkedHashSet<>();
		for (Clause c : premises) {
			premisesLogs.add(new ClauseLog(null, null, c, 0));
		}
		this.last = last;
		this.goal = goal;
		this.conclusion = conclusion;
	}

	public ResolutionResult(String goal, boolean conclusion) {
		this(new LinkedHashSet<>(), null, goal, conclusion);
	}

	public boolean isConclusion() {
		return conclusion;
	}
	
	public List<ClauseLog> generateClausesPath() {
		Map<String, ClauseLog> allLogs = new HashMap<>();
		for (ClauseLog cl : premisesLogs) {
			allLogs.put(cl.getClause().toString(), cl);
		}
		List<ClauseLog> clauseLogs = new LinkedList<>();
		Deque<ClauseLog> searchList = new LinkedList<>();
		List<ClauseLog> outputList = new LinkedList<>(premisesLogs);
		ClauseLog clauseLog;
		Clause tempClause;
		
		clauseLog = new ClauseLog(null, null, last, 0);
		allLogs.put(clauseLog.getClause().toString(), clauseLog);
		searchList.add(clauseLog);
		
		while (!searchList.isEmpty()) {
			ClauseLog tempCL;
			
			clauseLog = searchList.pop();
			clauseLogs.add(0, clauseLog);
			
			tempClause = clauseLog.getClause().getFirst();
			if (tempClause != null) {
				tempCL = allLogs.get(tempClause.toString());
				if (tempCL != null) {					
					clauseLog.setFirst(tempCL);				
				} else {
					tempCL = new ClauseLog(null, null, tempClause, 0);
					clauseLog.setFirst(tempCL);
					searchList.add(tempCL);
					allLogs.put(tempClause.toString(), tempCL);
				}
				
				tempClause = clauseLog.getClause().getSecond();
				tempCL = allLogs.get(tempClause.toString());
				if (tempCL != null) {					
					clauseLog.setSecond(tempCL);				
				} else {
					tempCL = new ClauseLog(null, null, tempClause, 0);
					clauseLog.setSecond(tempCL);
					searchList.add(tempCL);
					allLogs.put(tempClause.toString(), tempCL);
				}
			}
		}
		
		while (!clauseLogs.isEmpty()) {
			for (int i = 0; i < clauseLogs.size(); i++) {
				ClauseLog cl = clauseLogs.get(i);
				if (outputList.contains(cl.getFirst()) && outputList.contains(cl.getSecond())) {
					outputList.add(clauseLogs.remove(i));
					break;
				}
			}
		}
		
		outputList.removeAll(premisesLogs);
		return outputList;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (conclusion) {
			int num = 1;
			for (ClauseLog cl : premisesLogs) {
				sb.append(num).append(". ").append(cl.getClause().toString()).append("\n");
				cl.setNum(num++);
			}
			sb.append("===============\n");
			for (ClauseLog cl: generateClausesPath()) {
				Clause c = cl.getClause();
				sb.append(num).append(". ").append(c != Clause.NIL ? c.toString() : "NIL");
				sb.append(" (").append(cl.getFirst().getNum()).append(", ").append(cl.getSecond().getNum()).append(")");
				sb.append("\n");
				cl.setNum(num++);
			}
			sb.append("===============\n");
		}
		sb.append("[CONCLUSION]: ").append(goal).append(" is ").append(!conclusion ? "unknown" : "true");
		return sb.toString();
	}
}
