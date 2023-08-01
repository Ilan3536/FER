package ui.logic;

import java.util.LinkedHashSet;
import java.util.Set;

import ui.Resolution;

public class ExpertSystem {
	Set<Clause> clauses;

	public ExpertSystem(Set<Clause> clauses) {
		this.clauses = clauses;
	}
	
	public void addClause(String cnf) {
		Clause c = createClause(cnf);
		if(c != null) {
			clauses.add(c);
		}
	}
	
	public void removeClause(String cnf) {
		Clause c = createClause(cnf);
		clauses.remove(c);
	}
	
	public String showSystem() {
		StringBuilder sb = new StringBuilder();
		sb.append("Constructed with knowledge:\n");
		for (Clause c : clauses) {
			sb.append(c.toString()).append("\n");
		}
		return sb.toString();
	}
	
	public static Clause createClause(String cnf) {
		Set<Literal> literalSet = new LinkedHashSet<>();
		String[] literals = cnf.split("\\s+v\\s+");
		
		for (String literalStr : literals) {
			literalSet.add(literalStr.startsWith("~") 
						? new Literal(literalStr.substring(1), true) 
						: new Literal(literalStr, false));
		}
		Clause c = new Clause(literalSet);
		return c.containsTautology() ? null : c;
	}
	
	public Resolution resolve(String goal) {
		Set<Clause> clauses = new LinkedHashSet<>(this.clauses);
		Set<Clause> sos = new LinkedHashSet<>();
		String[] literals = goal.split("\\s+v\\s+");
		for (String literalStr : literals) {
			Clause goalClause = new Clause();
			goalClause.add(literalStr.startsWith("~") 
						? new Literal(literalStr.substring(1), false) 
						: new Literal(literalStr, true));
			sos.add(goalClause);
			clauses.add(goalClause);
		}
		
		return new Resolution(clauses, sos, goal);
	}
}
