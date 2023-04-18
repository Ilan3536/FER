package hr.fer.ooup.cetvrti;
import java.util.List;

public class Main {
	
	static DistributionTester tester;
	
	public static void main(String[] args) {
		tester = new DistributionTester();
		int N = 80;
		
		
		tester.generateSequence(new Fibonacci(10));
				
		tester.calculatePercentile(new NearestRank(), N);
		tester.calculatePercentile(new Interpolation(), N);
		System.out.println();

		tester.generateSequence(new Limiter(new Fibonacci(10), 9));
		System.out.println(tester.generator.getSequence());
		
		tester.generateSequence(new Modifier(new Fibonacci(10), new Add5Modification()));
		System.out.println(tester.generator.getSequence());

		tester.calculatePercentile(new NearestRank(), N);
		tester.calculatePercentile(new Interpolation(), N);
		System.out.println();
		
		
		
		tester.generateSequence(new NormalDistribution(51, 2, 21));

		tester.calculatePercentile(new NearestRank(), N);
		tester.calculatePercentile(new Interpolation(), N);
		System.out.println();
				
		tester.generateSequence(new Consecutive(1, 99, 2));

		tester.calculatePercentile(new NearestRank(), N);
		tester.calculatePercentile(new Interpolation(), N);
		
	}

}
