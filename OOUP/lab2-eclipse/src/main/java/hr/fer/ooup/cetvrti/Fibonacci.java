package hr.fer.ooup.cetvrti;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci implements NumberGenerator{

	private int size;
	private List<Integer> sequence;

	public Fibonacci(int size) {
		super();
		this.size = size;
	}

	

	@Override
	public void generate() {
		sequence = new ArrayList<>();
		int current = 1;
		int previous = 0;
		
		for (int i=0; i<size; i++) {
			sequence.add(current);
			
			int next = current + previous;
			previous = current;
			current = next;
		}
		
		
	}
	
	@Override
	public List<Integer> getSequence() {
		return sequence;
	}

	

}
