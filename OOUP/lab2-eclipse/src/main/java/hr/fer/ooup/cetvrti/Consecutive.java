package hr.fer.ooup.cetvrti;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consecutive implements NumberGenerator{
	private int lowerBound;
	private int upperBound;
	private int step;
	private List<Integer> sequence;

	
	public Consecutive(int lowerBound, int upperBound, int step) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.step = step;
	}

	public void generate() {
		
		sequence = new ArrayList<>();
		
		for (int i = lowerBound; i <= upperBound; i += step) {
			sequence.add(i);
		}

	}

	
	@Override
	public List<Integer> getSequence() {
		return sequence;
	}

}
