package ui.logic;

import java.util.Objects;

public class Literal {
	private String name;
	private boolean negated;
	
	public Literal(String name, boolean negated) {
		this.name = name;
		this.negated = negated;
	}
	
	public Literal() {}
	
	public Literal getComplement() {
		return new Literal(name, !negated);
	}
	
	public boolean isComplement(Literal other) {
		return name.equals(other.name) && negated == !other.negated;
	}

	public String getName() {
		return name;
	}

	public boolean isNegated() {
		return negated;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNegated(boolean negated) {
		this.negated = negated;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, negated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Literal)) {
			return false;
		}
		Literal other = (Literal) obj;
		return Objects.equals(name, other.name) && negated == other.negated;
	}
	
	@Override
	public String toString() {
		return (negated ? "~" : "") + name; 
	}
}
