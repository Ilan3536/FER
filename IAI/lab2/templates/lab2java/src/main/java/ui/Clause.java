package ui;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Clause implements Comparable<Clause>{
	public Set<String> clauses = new HashSet<>();
    public int number;
    public Clause resolvedFrom1;
    public Clause resolvedFrom2;
    
	public Clause(Set<String> clauses, int number, Clause reslovedFrom1, Clause reslovedFrom2) {
		super();
		this.clauses = clauses;
		this.number = number;
		this.resolvedFrom1 = reslovedFrom1;
		this.resolvedFrom2 = reslovedFrom2;
	}
	public Clause(Set<String> clauses, int number) {
		super();
		this.clauses = clauses;
		this.number = number;
	}
	public Clause() {
		super();
	}
	
	public int getNumber() {
		return number;
	}
	public Clause isSubsumedBy(Clause clause) {
		if (clause.clauses.containsAll(this.clauses)){
			return clause;
		} else if (this.clauses.containsAll(clause.clauses)){
			return this;
		}
		
		return null;
	}
	
	public static List<Clause> refutationPath(Clause clause) { 
		List<Clause> path = new LinkedList<>();
		refutationPathRecursive(path, clause);
		return path;
	}

	private static void refutationPathRecursive(List<Clause> path, Clause clause) {
		if(clause.resolvedFrom2 != null) {
			refutationPathRecursive(path, clause.resolvedFrom2);
	
		}
		path.add(clause);
		
		
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Clause clause = (Clause) o;
	    return clauses.equals(clause.clauses);
		
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(clauses);
    }
	
	@Override
	public String toString() {
		String output="";
		
		for (var clause : clauses) {
			output+= clause+" v ";
		}
		output = output.substring(0, output.length()-3);
		
		return output;
	}
	public String toStringCustom() {
		String output=""+number + ". ";
		
		for (var clause : clauses) {
			output+= clause+" v ";
		}
		output = output.substring(0, output.length()-3);
		
		if (resolvedFrom1 != null && resolvedFrom2 != null) {
			output += " (" + resolvedFrom1.number + "," + resolvedFrom2.number + ")";
		}
		
		return output;
	}
	@Override
	public int compareTo(Clause o) {
		
		return Integer.compare(this.number, o.number);
	}
	
	
	
	
	
	
    

}
