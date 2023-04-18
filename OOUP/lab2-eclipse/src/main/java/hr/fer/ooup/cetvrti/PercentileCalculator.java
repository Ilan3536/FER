package hr.fer.ooup.cetvrti;

import java.util.List;

public interface PercentileCalculator {
	
	double calculate(List<Integer> list, double percentile);

}
