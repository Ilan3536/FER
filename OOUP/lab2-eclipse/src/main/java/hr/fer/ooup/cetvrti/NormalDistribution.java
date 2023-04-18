package hr.fer.ooup.cetvrti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NormalDistribution implements NumberGenerator{
	private int mean;
	private int stddev;
	private int size;
	List<Integer> sequence;
	
	public NormalDistribution(int mean, int stddev, int size) {
		super();
		this.mean = mean;
		this.stddev = stddev;
		this.size = size;
	}

	public void generate() {
		sequence = new ArrayList<>();
		
		for (int i = 0; i < size; i++) {
			sequence.add(generateRandomNumber(mean, stddev));
		}
		
		Collections.sort(sequence);

	}

	
	public static int generateRandomNumber(int mean, int stddev) {
	    double u1 = Math.random(); 
	    double u2 = Math.random(); 
	    double z = Math.sqrt(-2.0 * Math.log(u1)) * Math.cos(2 * Math.PI * u2); // Normally distributed random number
	    double value = mean + stddev * z;
	    return (int) Math.round(value);
	}
	
	@Override
	public List<Integer> getSequence() {
		return sequence;
	}

}
