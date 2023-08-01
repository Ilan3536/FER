package ui.logic;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Clause {
	public static final Clause NIL = new Clause();
	private Set<Literal> clause;
	private Clause first;
	private Clause second;
	
	public Clause() {
		this(new LinkedHashSet<>());
	}
			
	public Clause(Set<Literal> clause) {
		this(clause, null, null);
	}
	
	public Clause(Set<Literal> clause, Clause first, Clause second) {
		this.clause = clause;
		this.first = first;
		this.second = second;
	}
	
	public Clause(Clause first, Clause second) {
		this(new LinkedHashSet<>(), first, second);
	}
	
	public boolean containsTautology() {
		for (Literal l : clause) {
			Literal complement = l.getComplement();
			if (clause.contains(complement)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns -1 if first value is redundant, 0 if they are not equivalent and 1 if second value is redundant
	 */
	public static int isRedundant(Clause c1, Clause c2) {
		if (c1 == c2) {
			return 0;
		}
		
		if (c1.containsAll(c2)) {
			return -1;
		} else if (c2.containsAll(c1)) {
			return 1;
		}
		return 0;
	}
		
	public Set<Literal> getClause() {
		return clause;
	}
	
	public Clause getFirst() {
		return first;
	}

	public Clause getSecond() {
		return second;
	}
	
	public void setFirst(Clause first) {
		this.first = first;
	}

	public void setSecond(Clause second) {
		this.second = second;
	}
	
	public boolean isEmpty() {
		return clause.isEmpty();
	}
	
	public int size() {
		return clause.size();
	}
	
	public boolean add(Literal e) {
		return clause.add(e);
	}
	
	public boolean remove(Object o) {
		return clause.remove(o);
	}
	
	public boolean addAll(Clause o) {
		return clause.addAll(o.clause);
	}
	
	public boolean contains(Object o) {
		return clause.contains(o);
	}
	
	public boolean containsAll(Clause o) {
		return clause.containsAll(o.clause);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(clause);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Clause)) {
			return false;
		}
		Clause other = (Clause) obj;
		return Objects.equals(clause, other.clause);
	}

	@Override
	public String toString() {
		String[] literals = clause.stream()
								  .map(Literal::toString)
								  .toArray(String[]::new);
		return String.join(" v ", literals);
	}
}
