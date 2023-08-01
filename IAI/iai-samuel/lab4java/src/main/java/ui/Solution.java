package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import ui.data.Dataset;
import ui.ga.GeneticAlgorithm;
import ui.nn.NeuralNetwork;

public class Solution {

	public static void main(String ... args) {
		if (args.length != 16) {
			System.out.println("Wrong arg num!");
			return;
		}
		Path train = null;
		Path test = null;
		int[] layerSizes = null;
		int popSize = 0;
		int elitism = 0;
		double p = 0.0;
		double k = 0.0;
		int iter = 0;

		String last = null;
		for (int i = 0; i < args.length; i++) {
			if (i % 2 == 0) {
				last = args[i];
			} else {
				switch (last) {
				case "--train" -> train = Paths.get(args[i]);
				case "--test" -> test = Paths.get(args[i]);
				case "--nn" -> {
					switch (args[i]) {
					case "5s" -> layerSizes = new int[] {5, 1};
					case "20s" -> layerSizes = new int[] {20, 1};
					case "5s5s" -> layerSizes = new int[] {5, 5, 1};
					default ->
						throw new IllegalArgumentException("Unexpected value: " + args[i]);
					}
				}
				case "--popsize" -> popSize = Integer.parseInt(args[i]);
				case "--elitism" -> elitism = Integer.parseInt(args[i]);
				case "--p" -> p = Double.parseDouble(args[i]);
				case "--K" -> k = Double.parseDouble(args[i]);
				case "--iter" -> iter = Integer.parseInt(args[i]);
				default ->
					throw new IllegalArgumentException("Unexpected value: " + last);
				}
			}
		}
		
		Dataset trainData = collectData(train);
		Dataset testData = collectData(test);

		GeneticAlgorithm ga = new GeneticAlgorithm(layerSizes, iter, popSize, elitism, p, k);
		NeuralNetwork best = ga.train(trainData).getNn();
		double err = best.computeData(testData);

		System.out.println(String.format("[Test error]: %.6f", err));
	}
	
	private static Dataset collectData(Path path) {
		List<String> header = new ArrayList<>();
		List<List<Double>> data = new ArrayList<>();
		String classLabelName = null;
		
		try (BufferedReader br = Files.newBufferedReader(path)) {
			String[] headerNames = br.readLine().split(",");
			for (String name : headerNames) {
				header.add(name);
			}
			classLabelName = headerNames[headerNames.length - 1];
			
			String line;
			while ((line = br.readLine()) != null) {
				String[] input = line.split(",");
				
				List<Double> row = new ArrayList<>();
				for (int i = 0; i < input.length; i++) {
					row.add(Double.parseDouble(input[i]));
				}
				data.add(row);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Dataset(header, data, classLabelName);
	}
}
