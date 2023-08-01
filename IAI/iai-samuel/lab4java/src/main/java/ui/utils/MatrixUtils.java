package ui.utils;

import java.util.Random;

public class MatrixUtils {

	public static double[][] multiply(double[][] matrix1, double[][] matrix2) {
		double[][] newMatrix = new double[matrix1.length][matrix2[0].length];
		
		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix2[0].length; j++) {
				double value = 0;
				for (int k = 0; k < matrix1[0].length; k++) {
					value += matrix1[i][k] * matrix2[k][j];
				}
				newMatrix[i][j] = value;
			}
		}
		
		return newMatrix;
	}
	
	public static double[][] add(double[][] matrix, double[][] vector) {
		double[][] newMatrix = new double[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				newMatrix[i][j] = matrix[i][j] + vector[i][j];				
			}
		}
		
		return newMatrix;
	}
	 
	public static double[][] initMatrix(Random rand, int rows, int cols) {
		double[][] matrix = new double[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = rand.nextGaussian() * 0.01;
			}
		}
		
		return matrix;
	}
}
