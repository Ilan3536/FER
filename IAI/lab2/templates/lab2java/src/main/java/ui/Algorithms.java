package ui;

import java.util.HashSet;
import java.util.Set;

public class Algorithms {
	
	public static int COUNT;
	
	public static void RefutationResolution(String clausesPath) {
		Set<Clause> clauses = Problem.parseClauses(clausesPath);
		COUNT = clauses.size();
		
		Set<Clause> newClauses = new HashSet<>();
		Set<Clause> clausesToBeAddedToNewClasses = new HashSet<>();
		Set<Clause> resolvents = new HashSet<>();
		
		newClauses.add(getGoalClauseNegated(clauses));
		
		removeRedundantAndIrrelevant(clauses);
		
		System.out.println(clauses);
		
		Clause resolvedClause = null;
		Boolean resolved = false;
		do {
			
			for (var c1 : clauses) {
				for(var c2 : newClauses) {
					
					if ((resolvedClause = plResolve(c1, c2)) != null) {
						
						
						if (resolvedClause.clauses.contains("NIL")) resolved = true;
						
						clausesToBeAddedToNewClasses.add(resolvedClause);
					}
				}
			}
			if (resolved) break;
			
			removeRedundantAndIrrelevant(clausesToBeAddedToNewClasses);
			
			if (thereAreNoNewClauses(clauses, clausesToBeAddedToNewClasses)) break;
			
			newClauses.addAll(clausesToBeAddedToNewClasses);
			removeRedundantAndIrrelevant(newClauses);;
			
		} while(!resolved);
		
		System.out.println(newClauses);
		
	}

	
	private static boolean thereAreNoNewClauses(Set<Clause> clauses, Set<Clause> clausesToBeAddedToNewClauses) {
		
		
		Clause subsumedClause;
		Set<Clause> clausesToBeRemoved = new HashSet<>();
		for (var clause1 : clausesToBeAddedToNewClauses) {
			for (var clause2 : clauses) {
				if ((subsumedClause = clause1.isSubsumedBy(clause2)) != null) {
					clausesToBeRemoved.add(subsumedClause);
					break;
				}
			}
		}
		clausesToBeAddedToNewClauses.removeAll(clausesToBeRemoved);

		return clausesToBeAddedToNewClauses.size() == 0 ;
	}


	private static void removeRedundantAndIrrelevant(Set<Clause> clauses) {
		removeRedundant(clauses);
		removeIrrelevant(clauses);
	}


	private static void removeRedundant(Set<Clause> clauses) {
		
		Set<Clause> clausesToBeRemoved = new HashSet<>();
		Clause subsumedClause;
		for (var clause1 : clauses) {
			for (var clause2 : clauses) {
				if (clause1.equals(clause2)) continue;
				
				if ((subsumedClause = clause1.isSubsumedBy(clause2)) != null) {
					clausesToBeRemoved.add(subsumedClause);
				}
			}
			
		}
		
		clauses.removeAll(clausesToBeRemoved);
			
	}



	private static void removeIrrelevant(Set<Clause> clauses) {
		
		Set<Clause> clausesToBeRemoved = new HashSet<>();
		for (var clause : clauses) {
			
			for(var literal1 : clause.clauses) {
				for(var literal2 : clause.clauses) {
					
					if (complementaryLiterals(literal1, literal2)){  // ~A V A, or A v ~A
						clausesToBeRemoved.add(clause);
					} 
					
				}
			}
		}
		clauses.removeAll(clausesToBeRemoved);
		
	}



	private static boolean complementaryLiterals(String literal1, String literal2) {
		return (literal1.startsWith("~") && literal1.substring(1).equals(literal2) || 
		   (literal2.startsWith("~") && literal2.substring(1).equals(literal1))  );
	}


	private static Clause plResolve(Clause c1, Clause c2) {
		
		if (clausesResolveNIL(c1,c2)) {
			Set<String> resolvents = new HashSet<>();
			resolvents.add("NIL");
			return new Clause(resolvents, ++COUNT, c1, c2);
		}
		
		for (var literal1 : c1.clauses) {
			for (var literal2 : c2.clauses) {
				 if (complementaryLiterals(literal1, literal2)) {
					 return extractResolventsFromTwoClauses(c1, c2, literal1, literal2);
				 }
			} 
		}
		return null;
	}
	
	private static boolean clausesResolveNIL(Clause c1, Clause c2) {
		return c1.clauses.size()==1 && c2.clauses.size()==1 && 
			   complementaryLiterals(c1.clauses.iterator().next(), c2.clauses.iterator().next());
	}


	private static Clause extractResolventsFromTwoClauses(Clause c1, Clause c2, String literal1, String literal2) {
		Set<String> resolvents = new HashSet<>();
		for (var literal : c1.clauses) {
			if (literal.equals(literal1)) continue;
			resolvents.add(literal);
		}
		
		for (var literal : c2.clauses) {
			if (literal.equals(literal2)) continue;
			resolvents.add(literal);
		}
			
		return new Clause(resolvents, ++COUNT, c1, c2);
	}


	private static Clause getGoalClauseNegated(Set<Clause> clauses) {
		for (var clause : clauses) {
			if (clause.number == clauses.size()) {
				return clause;
			}
		}
		return null;
	}
}
