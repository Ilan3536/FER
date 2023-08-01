package ui.ga;

import ui.nn.NeuralNetwork;

public class Chromosome implements Comparable<Chromosome> {

	private NeuralNetwork nn;
	private double fitness = - 1;
	
	public Chromosome(NeuralNetwork nn) {
		this.nn = nn;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public NeuralNetwork getNn() {
		return nn;
	}

	@Override
	public int compareTo(Chromosome o) {
		return Double.compare(fitness, o.fitness);
	}
	
	
}
