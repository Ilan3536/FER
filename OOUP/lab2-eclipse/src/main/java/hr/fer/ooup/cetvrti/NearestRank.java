package hr.fer.ooup.cetvrti;

import java.util.List;

public class NearestRank implements PercentileCalculator {

	@Override
	public double calculate(List<Integer> list, double percentile) {
		
		int n = (int) Math.ceil(1.0 * percentile / 100 * list.size());
		
		return 1.0 *  list.get(n-1);
	}

}
