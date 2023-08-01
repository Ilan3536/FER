package ui;

public class Successor {
	private String nextState;
	private double cost;
	
	public Successor(String nextState, double cost) {
		this.nextState = nextState;
		this.cost = cost;
	}

	public String getNextState() {
		return nextState;
	}

	public double getCost() {
		return cost;
	}
}
