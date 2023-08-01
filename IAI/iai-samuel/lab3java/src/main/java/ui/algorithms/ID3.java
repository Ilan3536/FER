package ui.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ui.data.Dataset;
import ui.tree.ITreeElement;
import ui.tree.Leaf;
import ui.tree.Node;
import ui.tree.Subtree;

public class ID3 implements IAlgorithm {
	
	private Dataset trainData;
	private ITreeElement root;
	private int maxDept;
	
	public ID3(int maxDepth) {
		this.maxDept = maxDepth;
	}
	
	public ID3() {
		this(Integer.MAX_VALUE);
	}
	
	@Override
	public void fit(Dataset data) {
		trainData = data;

		root = id3(data, data, data.getHeader(), data.getClassLabelName(), 0);
		System.out.println("[BRANCHES]:");
		root.printBranch("" , 1);
	}

	@Override
	public List<String> predict(Dataset data) {
		List<String> predictions = new ArrayList<>();
		for (Map<String, String> row : data) {
			String prediction = root.traverse(row);
			if (prediction == null) {
				prediction = trainData.getMaxOccurring();
			}
			predictions.add(prediction);
		}
		return predictions;
	}

	private ITreeElement id3(Dataset data,
					 Dataset parentData,
					 Set<String> header,
					 String classLabel,
					 int depth) {
		String classLabelValue;
		if (data.isEmpty()) {
			classLabelValue = parentData.getMaxOccurring();
			return new Leaf(classLabelValue);
		}
		classLabelValue = data.getMaxOccurring();
		if (header.isEmpty() || depth == maxDept || data.equals(data.shortenDataset(classLabel, classLabelValue))) {
			return new Leaf(classLabelValue);
		}
		
		StringBuilder sb = new StringBuilder();
		String bestFeature = null; 
		double maxIG = -1;
		for (String feature : header) {
			if (feature.equals(classLabel)) continue;
			double ig = calcIG(data, feature);
			if (maxIG < ig) {
				maxIG = ig;
				bestFeature = feature;
			} else if (maxIG == ig) {
				bestFeature = bestFeature.compareTo(feature) < 0 
								? bestFeature 
								: feature;
			}
			sb.append("IG(").append(feature).append(")=").append(String.format("%.4f", ig)).append(" ");
		}
		System.out.println(sb.toString());
		
		List<Subtree> subtrees = new ArrayList<>();
		Set<String> newHeader = new HashSet<>(header);
		newHeader.remove(bestFeature);
		
		for (String featureValue : new HashSet<>(data.getColumn(bestFeature))) {
			Dataset newData = data.shortenDataset(bestFeature, featureValue);	
			ITreeElement t = id3(newData, data, newHeader, classLabel, depth + 1);
			subtrees.add(new Subtree(featureValue, t));
		}
		
		return new Node(bestFeature, subtrees);
	}
	
	private double calcIG(Dataset data, String feature) {
		List<String> classLabelColumn = data.getClassLabelColumn();
				
		Map<String, List<String>> featureClassLabel = new HashMap<>();
		List<String> featureValues = data.getColumn(feature);
		for (int i = 0; i < featureValues.size(); i++) {
			List<String> featureCounts = featureClassLabel.get(featureValues.get(i));
			if (featureCounts == null) {
				featureCounts = new ArrayList<>();
				featureClassLabel.put(featureValues.get(i), featureCounts);
			}
			featureCounts.add(classLabelColumn.get(i));
		}

		double ig = calcEntropy(classLabelColumn);
		for (String featureValue : new HashSet<>(featureValues)) {
			double freq = (double)Collections.frequency(featureValues, featureValue);
			ig -= freq / featureValues.size() * calcEntropy(featureClassLabel.get(featureValue));
		}

		return ig;
	}
	
	private double calcEntropy(List<String> dataColumn) {
		double dataEntropy = 0;
		for (String classLabel : new HashSet<>(dataColumn)) {
			double ratio = (double)Collections.frequency(dataColumn, classLabel) / dataColumn.size();
			dataEntropy += -(ratio * Math.log(ratio) / Math.log(2));
		}
		return dataEntropy;
	}
}
