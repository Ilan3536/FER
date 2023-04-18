package hr.fer.ooup.cetvrti;

import java.util.ArrayList;
import java.util.List;

public class Modifier extends NumberGeneratorBaseDec {

	
	Modification modification;
	
	public Modifier(NumberGenerator generator, Modification modification) {
		super(generator);
		this.modification = modification;
		
	}
	
	@Override
	public List<Integer> getSequence() {
		List<Integer> sequence = wrapee.getSequence();
		List<Integer> modifiedSequence = new ArrayList<>();
		
		for (var el : sequence) {
			modifiedSequence.add(modification.modifiy(el));
		}
		
		return modifiedSequence;
	}

	
}
