package ui;

import java.util.Comparator;

public class HNode extends Node{

	double totalCostEstimate;
	public HNode(Node parent, String state, Double cost, Double totalCostEstimate) {
		super(parent, state, cost);
		this.totalCostEstimate = totalCostEstimate;
		
	}
	
	final static Comparator<HNode> COMP = 
			(o1, o2) -> Double.compare(o1.totalCostEstimate, o2.totalCostEstimate);

	

}
