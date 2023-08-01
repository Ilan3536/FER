package ui.algorithms;

import java.util.List;

import ui.data.Dataset;

public interface IAlgorithm {

	void fit(Dataset data);

	List<String> predict(Dataset data);

}