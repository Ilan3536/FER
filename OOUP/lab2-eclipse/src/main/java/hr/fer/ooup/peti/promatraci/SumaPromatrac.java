package hr.fer.ooup.peti.promatraci;

import java.util.List;

public class SumaPromatrac implements Promatrac{

	@Override
	public void update(List<Integer> kolekcija) {
		int sum=0;
		for (var el: kolekcija) {
			sum+= el;
		}
		
		System.out.println("Suma je: " + sum);
		
	}

	
}
