package hr.fer.ooup.cetvrti;



public class DistributionTester {
	
	NumberGenerator generator;
	PercentileCalculator calculator;
	
	public void generateSequence(NumberGenerator generator) {
		this.generator = generator;
		generator.generate();
	}

	public void calculatePercentile(PercentileCalculator calculator, double percentile) {
		this.calculator = calculator;
		double res = Double.parseDouble(String.format("%.1f",calculator.calculate(generator.getSequence(), percentile)).replace(',', '.'));
		System.out.println(res);
		
	}
	
	

}
