package hr.fer.ooup.cetvrti;

import java.util.List;

public class Interpolation implements PercentileCalculator{

	@Override
	public double calculate(List<Integer> list, double percentile) {
		int N = list.size();
		for (int i=0; i < list.size(); i++) {
			
			double elementPercentile = 100 * (i+1 - 0.5) / N;

			if (percentile < elementPercentile) {
				if (i==0) return elementPercentile;
				
				int v1 = list.get(i-1);
				int v2 = list.get(i);
				
				double p1 = 100 * (i - 0.5) / N;
				
				return v1 + N * (percentile - p1) * (v2 - v1) / 100;
				
				
			}
		}
		
		return list.get(N-1);
	}

}
