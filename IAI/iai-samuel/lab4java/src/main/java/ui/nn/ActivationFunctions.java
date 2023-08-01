package ui.nn;

import java.util.function.Function;

public class ActivationFunctions {

	public static final Function<double[][], double[][]> APPLY_LINEAR = matrix -> matrix;
	
	public static final Function<double[][], double[][]> APPLY_SIGMOID = matrix ->  {
		double[][] newMatrix = new double[matrix.length][1];
		
		for (int i = 0; i < matrix.length; i++) {
			newMatrix[i][0] = 1.0 / (1.0 + Math.pow(Math.E, -matrix[i][0]));
		}
		
		return newMatrix;
	};
}
