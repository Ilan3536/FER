package hr.fer.ooup.peti.promatraci;

import java.util.List;

public class ProsjekPromatrac implements Promatrac{

	@Override
	public void update(List<Integer> kolekcija) {
		int sum=0;
		for (var el: kolekcija) {
			sum+= el;
		}
		
		double prosjek = 1.0 * sum / kolekcija.size();
		
		System.out.println("Prosjek je: " + prosjek);
		
		
	}

}
