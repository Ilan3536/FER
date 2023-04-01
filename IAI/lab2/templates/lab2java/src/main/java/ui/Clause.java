package ui;

import java.util.HashSet;
import java.util.Set;

public class Clause {
	public Set<String> clauses = new HashSet<>();
    public int number;
    public Clause reslovedFrom1;
    public Clause reslovedFrom2;
    
	public Clause(Set<String> clauses, int number, Clause reslovedFrom1, Clause reslovedFrom2) {
		super();
		this.clauses = clauses;
		this.number = number;
		this.reslovedFrom1 = reslovedFrom1;
		this.reslovedFrom2 = reslovedFrom2;
	}
	public Clause(Set<String> clauses, int number) {
		super();
		this.clauses = clauses;
		this.number = number;
	}
	public Clause() {
		super();
	}
    
	
	@Override
	public boolean equals(Object other) {
		Clause second = (Clause) other;
		return this.clauses.equals(second.clauses);
	}
	@Override
	public String toString() {
		String output="\n"+number + ". ";
		
		for (var clause : clauses) {
			output+= clause+" v ";
		}
		output = output.substring(0, output.length()-3);
		return output;
	}
	
	public Clause isSubsumedBy(Clause clause) {
		if (clause.clauses.containsAll(this.clauses)){
			return clause;
		} else if (this.clauses.containsAll(clause.clauses)){
			return this;
		}
		
		return null;
	}
	
	
    

}
