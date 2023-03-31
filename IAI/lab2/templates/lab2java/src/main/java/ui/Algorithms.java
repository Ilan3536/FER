package ui;

import java.util.HashSet;
import java.util.Set;

public class Algorithms {
	
	public static void RefutationResolution(String clausesPath) {
		Set<Clause> clauses = Problem.parseClauses(clausesPath);
		System.out.println(clauses);
		
		Set<Clause> newClauses = new HashSet<>();
		Set<Clause> resolvents = new HashSet<>();
		
		do {
			for (var c1 : clauses) {
				for(var c2 : newClauses) {
					if ((resolvents = plResolve(c1, c2)) != null) {
						
					}
				}
			}
			
			
		} while(true);
		
		
	}

	private static Set<Clause> plResolve(Clause c1, Clause c12) {
		// TODO Auto-generated method stub
		return null;
	}
}
