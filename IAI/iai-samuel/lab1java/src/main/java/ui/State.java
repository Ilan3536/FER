package ui;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class State {
	private String name;
	private int heuristic;
	private Set<Successor> successors;
	
	public State(String name, int heuristic) {
		this.name = name;
		this.heuristic = heuristic;
		this.successors = new TreeSet<>(Comparator.comparing(Successor::getNextState));
	}
	
	public State(String name) {
		this(name, 0);
	}

	public void addSuccessor(Successor successor) {
		successors.add(successor);
	}
	
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public String getName() {
		return name;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public Set<Successor> getSuccessors() {
		return successors;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)  return false;
		if (this == obj) return true;
		if (!(obj instanceof State)) return false;
		State other = (State)obj;
		return this.name.equals(other.name);
	}
	
	@Override
	public String toString() {
		return String.format("%s: %d", name, heuristic);
	}
	
	
}
