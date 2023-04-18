package hr.fer.ooup.peti.promatraci;

import java.util.Collections;
import java.util.List;

public class MedijanPromatrac implements Promatrac{

	@Override
	public void update(List<Integer> kolekcija) {
		Collections.sort(kolekcija);
        int size = kolekcija.size();
        int medijan;
        if (size % 2 == 0) {
            int index1 = size / 2 - 1;
            int index2 = size / 2;
            medijan =  (int) ((kolekcija.get(index1) + kolekcija.get(index2)) / 2.0);
        } else {
            int index = size / 2;
            medijan =  kolekcija.get(index);
        }
		
		System.out.println("Medijan je: " + medijan);
		
		
	}
}
