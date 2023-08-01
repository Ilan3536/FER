package ui.result;

import java.util.Objects;

import ui.logic.Clause;

public class ClauseLog {
	private ClauseLog first;
	private ClauseLog second;
	private Clause clause;
	private int num;
	
	public ClauseLog(ClauseLog first, ClauseLog second, Clause clause, int num) {
		this.first = first;
		this.second = second;
		this.clause = clause;
		this.num = num;
	}
	
	public ClauseLog getFirst() {
		return first;
	}
	
	public ClauseLog getSecond() {
		return second;
	}
	
	public void setFirst(ClauseLog first) {
		this.first = first;
	}
	
	public void setSecond(ClauseLog second) {
		this.second = second;
	}
	
	public Clause getClause() {
		return clause;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
		if (!(obj instanceof ClauseLog)) {
			return false;
		}
		ClauseLog other = (ClauseLog) obj;
		return Objects.equals(clause, other.clause);
	}
	
}
