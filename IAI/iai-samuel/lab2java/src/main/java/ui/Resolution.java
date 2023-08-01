package ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ui.logic.Clause;
import ui.logic.ClausePair;
import ui.logic.ClausePairIterator;
import ui.logic.Literal;
import ui.result.ResolutionResult;

public class Resolution {
	private Set<Clause> premises = new LinkedHashSet<>();
	private Set<Clause> clauses;
	private Set<Clause> sos;
	private String goal;
	private Set<ClausePair> resolved = new HashSet<>();

	public Resolution(Set<Clause> clauses, Set<Clause> sos, String goal) {
		this.clauses = clauses;
		this.sos = sos;
		this.goal = goal;
		for (Clause c : clauses) {
			premises.add(c);
		}
	}

	public ResolutionResult resolution() {		
		while (true) {
			removeRedundant(clauses);
			removeRedundant(sos);
			for (ClausePair cp : ClausePairIterator.getIterator(clauses, sos)) {
				if (resolved.contains(cp)) {
					continue;
				}
				resolved.add(cp);

				Set<Clause> resolvents = resolve(cp);
				if (resolvents.contains(Clause.NIL)) {
					Clause.NIL.setFirst(cp.getC1());
					Clause.NIL.setSecond(cp.getC2());
					return new ResolutionResult(premises, Clause.NIL, goal, true);
				}
				sos.addAll(resolvents);
			}
			if (clauses.containsAll(sos)) return new ResolutionResult(goal, false);
			clauses.addAll(sos);
		}
	}
	

	private void removeRedundant(Set<Clause> clauses) {
		List<Clause> toRemove = new ArrayList<>();
		
		for (Clause c1 : clauses) {
			for (Clause c2 : clauses) {
				switch(Clause.isRedundant(c1, c2)) {
					case -1 -> toRemove.add(c1);
					case 1 -> toRemove.add(c2);
				}
			}
		}
		clauses.removeAll(toRemove);
	}

	private Set<Clause> resolve(ClausePair cp) {
		Set<Clause> resolvents = new LinkedHashSet<>();
		Literal[] literals = findComplements(cp);
		if (literals != null) {
			Clause clause;
								
			clause = new Clause(cp.getC1(), cp.getC2());
			clause.addAll(cp.getC1());
			clause.addAll(cp.getC2());
			clause.remove(literals[0]);
			clause.remove(literals[1]);
			
			if (clause.isEmpty()) {
				clause = Clause.NIL;
			}
			if (!clause.containsTautology() 
					&&!sos.contains(clause) 
					&&!clauses.contains(clause)) {
				resolvents.add(clause);											
			}
		}
		return resolvents;
	}
	
	private Literal[] findComplements(ClausePair cp) {
		Clause c1 = cp.getC1();
		Clause c2 = cp.getC2();
		
		for (Literal l : c1.getClause()) {
			Literal complement = l.getComplement();
			if (c2.contains(complement)) {
				return new Literal[] {l, complement};
			}
		}
		return null;
	}
}
