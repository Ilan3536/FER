package ui.data;

import java.util.Iterator;
import java.util.List;

public class Dataset implements Iterable<ui.data.Dataset.DataInstance> {
	
	private List<String> header;
	private List<List<Double>> data;
	private String classLabelName;

	public Dataset(List<String> header, List<List<Double>> data, String classLabelName) {
		this.header = header;
		this.data = data;
		this.classLabelName = classLabelName;
	}
	
	@Override
	public Iterator<DataInstance> iterator() {
		return new IteratorImpl();
	}
	
	public int size() {
		return data.size();
	}
	
	public int inputSize() {
		return header.size() - 1;
	}
	
	private class IteratorImpl implements Iterator<DataInstance> {

		private int index = 0;
		private int size = data.size();
		
		@Override
		public boolean hasNext() {
			return index != size;
		}

		@Override
		public DataInstance next() {
			int rowSize = header.size() - 1;
			double[][] inputs = new double[rowSize][1];
			List<Double> row = data.get(index++);
			
			for (int i = 0; i < rowSize; i++) {
				inputs[i][0] = row.get(i);
			}
			
			return new DataInstance(inputs, row.get(rowSize));
		}
		
	}
	
	public static class DataInstance {
		private double[][] inputs;
		private double output;
		
		public DataInstance(double[][] inputs, double output) {
			this.inputs = inputs;
			this.output = output;
		}
		
		public double[][] getInputs() {
			return inputs;
		}
		public double getOutput() {
			return output;
		}
		
	}
}