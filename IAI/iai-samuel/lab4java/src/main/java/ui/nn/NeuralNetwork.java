package ui.nn;

import ui.data.Dataset;
import ui.data.Dataset.DataInstance;
import ui.utils.MatrixUtils;

public class NeuralNetwork {

	private Layer[] layers;
	
	public NeuralNetwork(Layer[] layers) {
		this.layers = layers;
	}
	
	public Layer[] getLayers() {
		return layers;
	}
	
	public void setWeights(double[][] weights, int index) {
		layers[index].setWeights(weights);
	}
	
	public double[][] getWeights(int index) {
		return layers[index].getWeights();
	}
	
	public void setBiases(double[][] biases, int index) {
		layers[index].setBiases(biases);
	}
	
	public double[][] getBiases(int index) {
		return layers[index].getBiases();
	}
	
	private double computeForwardProp(double[][] input) {
		double[][] matrix = input;
		
		for (int i = 0; i < layers.length; i++) {
			matrix = MatrixUtils.multiply(layers[i].getWeights(), matrix);
			matrix = MatrixUtils.add(matrix, layers[i].getBiases());
			matrix = i < layers.length - 1 
						? ActivationFunctions.APPLY_SIGMOID.apply(matrix)
						: ActivationFunctions.APPLY_LINEAR.apply(matrix);
		}
		
		return matrix[0][0];
	}
	
	public double computeData(Dataset data) {
		double err = 0;
		
		for (DataInstance di : data) {
			err += Math.pow(di.getOutput() - computeForwardProp(di.getInputs()), 2);
		}
		
		return err / data.size();
	}
}
