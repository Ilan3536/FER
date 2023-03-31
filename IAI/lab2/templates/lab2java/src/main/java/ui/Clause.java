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
	public boolean equals(Object o) {
		Clause second = (Clause) o;
		return this.clauses.equals(second.clauses);
	}
	@Override
	public String toString() {
		String output="\n"+number + ". ";
		
		for (var clause : clauses) {
			output+= clause+" V ";
		}
		output = output.substring(0, output.length()-3);
		return output;
	}
	
	
    

}
