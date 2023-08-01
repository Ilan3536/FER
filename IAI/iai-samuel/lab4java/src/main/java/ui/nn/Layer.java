package ui.nn;

public class Layer {
	private int size;
	private double[][] weights;
	private double[][] biases;
	
	public Layer(int size, double[][] weights, double[][] biases) {
		this.size = size;
		this.weights = weights;
		this.biases = biases;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double[][] getWeights() {
		return weights;
	}

	public void setWeights(double[][] weights) {
		this.weights = weights;
	}

	public double[][] getBiases() {
		return biases;
	}

	public void setBiases(double[][] biases) {
		this.biases = biases;
	}
	
}
