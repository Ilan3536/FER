package hr.fer.ooup.cetvrti;

import java.util.List;

public class NumberGeneratorBaseDec implements NumberGenerator{

	NumberGenerator wrapee;
	
	public NumberGeneratorBaseDec(NumberGenerator generator) {
		wrapee = generator;
	}
	@Override
	public void generate() {
		wrapee.generate();
	}

	@Override
	public List<Integer> getSequence() {
		return wrapee.getSequence();
	}
	
	

}
