package hr.fer.ooup.peti.izvori;

import hr.fer.ooup.peti.promatraci.*;

public class Main {

	public static void main(String[] args) {
	
		SlijedBrojeva slijedBrojeva = new SlijedBrojeva(new TipkovnickiIzvor());
		slijedBrojeva.attach(new SumaPromatrac());
		slijedBrojeva.attach(new MedijanPromatrac());
		slijedBrojeva.attach(new ProsjekPromatrac());
		slijedBrojeva.attach(new ZapisPromatrac());
		
		
		slijedBrojeva.kreni();
		System.out.println(slijedBrojeva.kolekcija);
		
		
		
		slijedBrojeva.setIzvor(new DatotecniIzvor("./izvor.txt"));
		slijedBrojeva.kreni();
		System.out.println(slijedBrojeva.kolekcija);
		
	}

}
