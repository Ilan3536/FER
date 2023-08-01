package ui.logic;

import java.util.Objects;

public class ClausePair {
	private Clause c1;
	private Clause c2;
	
	public ClausePair(Clause c1, Clause c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	@Override
	public String toString() {
		return "{" + c1.toString() + ", " + c2.toString() + "}";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(c1, c2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ClausePair)) {
			return false;
		}
		ClausePair other = (ClausePair) obj;
		return Objects.equals(c1, other.c1) && Objects.equals(c2, other.c2);
	}

	public Clause getC1() {
		return c1;
	}

	public Clause getC2() {
		return c2;
	}
}
