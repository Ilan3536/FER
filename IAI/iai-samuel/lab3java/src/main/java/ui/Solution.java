package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import ui.algorithms.IAlgorithm;
import ui.algorithms.ID3;
import ui.data.Dataset;

public class Solution {

	public static void main(String ...args) {
		if (args.length < 2 || args.length > 3) {
			System.out.println("Input arguments should be path to train data and path to test data.");
			return;
		}
		
		IAlgorithm alg;
		if (args.length == 3) {
			alg = new ID3(Integer.parseInt(args[2]));
		} else {			
			alg = new ID3();
		}
		Dataset trainData = collectData(Paths.get(args[0]));
		alg.fit(trainData);
		Dataset testData = collectData(Paths.get(args[1]));
		List<String> predictions = alg.predict(testData);
		evalPredictions(predictions, testData);
	}

	private static void evalPredictions(List<String> predictions, Dataset data) {
		StringBuilder sb = new StringBuilder();		
		List<String> values = data.getColumn(data.getClassLabelName());
		int correct = 0;

		sb.append("[PREDICTIONS]:");
		for (int i = 0; i < predictions.size(); i++) {
			String prediction = predictions.get(i);
			if (prediction.equals(values.get(i))) {
				correct++;
			}
			sb.append(' ').append(prediction);
		}
		
		sb.append("\n[ACCURACY]: ").append(String.format("%.5f", (double)correct / predictions.size()));
		sb.append("\n[CONFUSION_MATRIX]:\n");
		
		List<String> classLabelValues = new ArrayList<>(new HashSet<>(data.getColumn(data.getClassLabelName())));
		Collections.sort(classLabelValues);
		int confusionMatrix[][] = new int[classLabelValues.size()][classLabelValues.size()];
		for (int i = 0; i < predictions.size(); i++) {
			int predictionIndex = classLabelValues.indexOf(data.getColumn(data.getClassLabelName()).get(i));
			int realIndex = classLabelValues.indexOf(predictions.get(i));
			confusionMatrix[predictionIndex][realIndex] += 1;
		}
		
		for (int i = 0; i < confusionMatrix.length; i++) {
			sb.append(confusionMatrix[i][0]);
			for (int j = 1; j < confusionMatrix.length; j++) {
				sb.append(' ').append(confusionMatrix[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}

	private static Dataset collectData(Path path) {
		Map<String, List<String>> inputData = new HashMap<>();
		String classLabelName = null;
		
		try (BufferedReader br = Files.newBufferedReader(path)) {
			String[] header = br.readLine().split(",");
			for (String name : header) {
				inputData.put(name, new ArrayList<>());
			}
			classLabelName = header[header.length - 1];
			
			String line;
			while ((line = br.readLine()) != null) {
				String[] input = line.split(",");
				
				for (int i = 0; i < input.length; i++) {
					inputData.get(header[i]).add(input[i]);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Dataset(inputData, classLabelName);
	}
}
