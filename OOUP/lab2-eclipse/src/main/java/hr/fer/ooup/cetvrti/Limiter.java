package hr.fer.ooup.cetvrti;

import java.util.List;

public class Limiter extends NumberGeneratorBaseDec {
	
	private int limit;
	
	public Limiter(NumberGenerator generator, int limit) {
		super(generator);
		this.limit = limit;
	} 
	
	@Override
	public List<Integer> getSequence() {
		return wrapee.getSequence().subList(0, limit);
	}
	
	
}
