package ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Algorithms {
	
	public static int COUNT;
	public static Boolean RESOLVED;
	public static Boolean ADDED_NEW_CLAUSE_THIS_ITERATION;
	
	public static void RefutationResolution(String clausesPath) { //current problem is that set of initial clauses has to be also updated at the output
		Set<Clause> clauses = Problem.parseClauses(clausesPath);
		
		COUNT = clauses.size();
		
		Set<Clause> newClauses = new HashSet<>();
		Set<Clause> clausesToBeAddedToNewClasses = new HashSet<>();
		List<Clause> path = new LinkedList<>();
		
		Set<Clause> negatedGoalClauses = negateGoalClause(clauses);
		clauses.addAll(negatedGoalClauses);
		newClauses.addAll(negatedGoalClauses);
		removeRedundantAndIrrelevant(clauses);
		Set<Clause> clausesCopy = new HashSet<>(clauses);
		
		
		Clause resolvedClause = null;
		RESOLVED = false;
		
		do {
			ADDED_NEW_CLAUSE_THIS_ITERATION = false;
			for (var c1 : clauses) {
				for(var c2 : newClauses) {
					if (RESOLVED) break;
					if (c1.equals(c2)) continue;
					
					if ((resolvedClause = plResolve(c1, c2)) != null) {
						if (clauses.contains(resolvedClause) || newClauses.contains(resolvedClause)) continue;
						ADDED_NEW_CLAUSE_THIS_ITERATION = true;
						if (resolvedClause.clauses.contains("NIL")) {
							RESOLVED = true;
							path = Clause.refutationPath(resolvedClause);
							path.remove(0);
							break;
						}
						
						clausesToBeAddedToNewClasses.add(resolvedClause);
						
					}
				}
			}
			if (RESOLVED) break;
			
			removeRedundantAndIrrelevant(newClauses); 
			removeRedundantAndIrrelevant(clauses);
			if (thereAreNoNewClauses(clauses, clausesToBeAddedToNewClasses)) break;
			
			newClauses.addAll(clausesToBeAddedToNewClasses);
			clauses.addAll(clausesToBeAddedToNewClasses);
			
			clausesToBeAddedToNewClasses.clear();
			removeRedundantAndIrrelevant(newClauses); 
			removeRedundantAndIrrelevant(clauses);
			
		} while(!RESOLVED && ADDED_NEW_CLAUSE_THIS_ITERATION);
		
		
		printSolution(clausesCopy, path);
		
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
					if (literal1.equals(literal2)) continue;
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
				//GOAL_CLAUSE = clause;
				return clause;
			}
		}
		return null;
	}

	private static Set<Clause> negateGoalClause(Set<Clause> clauses) {
		for (var clause : clauses) {
			if (clause.number == clauses.size()) {

				Set<Clause> set = new HashSet<>();
				for (String literal : clause.clauses) {
					Set<String> clauseSet = new HashSet<>();
					if (literal.startsWith("~")) {
						literal = literal.substring(1);
					} else {
						literal = "~" + literal;
					}
					clauseSet.add(literal);
					set.add(new Clause(clauseSet, ++COUNT));
				}
				clauses.remove(clause);
				return set;
			}
		}
		return null;
		
	}
	private static void printSolution(Set<Clause> clauses, List<Clause> path) {
		List<Clause> sortedList = new ArrayList<>(clauses);
		Collections.sort(sortedList);

		Clause goalClause = sortedList.get(sortedList.size()-1);
		String goalClauseLiteral = goalClause.clauses.iterator().next();
		
		
		if (goalClauseLiteral.startsWith("~")) {
			goalClauseLiteral = goalClauseLiteral.substring(1);
		} else {
			goalClauseLiteral = "~" + goalClauseLiteral;
		}
		
		if (!RESOLVED) {
			System.out.println("[CONCLUSION]: " + Problem.GOAL_CLAUSE + " is unknown");
			return;
		}
        Collections.sort(sortedList, Comparator.comparingInt(Clause::getNumber));
        Map<Integer, Integer> clauseMap = new HashMap<>();
        
        int clauseCount;
        // Add starting clauses to the map (oldNumber, newNumber)
        for (clauseCount = 0; clauseCount < sortedList.size(); clauseCount++) {
        	clauseMap.put(sortedList.get(clauseCount).number, clauseCount+1);
        	System.out.println(clauseCount+1 +". "+ sortedList.get(clauseCount));
        }

        System.out.println("============");

        // Add derived clauses to the map
        for (int premiseCount = 0; clauseCount < sortedList.size() + path.size(); clauseCount++, premiseCount++) {
        	clauseMap.put(path.get(premiseCount).number, clauseCount+1);
        	Clause currentClause = path.get(premiseCount);
        	System.out.println(clauseCount+1 + ". " + currentClause + " (" + 
        						clauseMap.get(currentClause.resolvedFrom1.number) + "," + 
        						clauseMap.get(currentClause.resolvedFrom2.number) + ")");
        }
        System.out.println("============");
        System.out.println("[CONCLUSION]: " + Problem.GOAL_CLAUSE + " is true");
       
//        System.out.println("---------------------");
//        
//		for (var clause : sortedList) {
//			System.out.println(clause.toStringCustom());
//		}
//		System.out.println("============");
//		for (var clause : path) {
//			System.out.println(clause.toStringCustom());
//		}
		
	}
}
