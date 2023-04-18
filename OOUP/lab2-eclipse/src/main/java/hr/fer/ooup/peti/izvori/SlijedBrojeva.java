package hr.fer.ooup.peti.izvori;

import java.util.ArrayList;
import java.util.List;

import hr.fer.ooup.peti.promatraci.*;

public class SlijedBrojeva {
	
	Izvor izvor;
	List<Integer> kolekcija;
	List<Promatrac> promatraci;

	public void setIzvor(Izvor izvor) {
		this.izvor = izvor;
	}

	public SlijedBrojeva(Izvor izvor) {
		super();
		this.izvor = izvor;
		kolekcija = new ArrayList<>();
		promatraci = new ArrayList<>();
	}
	
	public void kreni() {
		kolekcija = izvor.kreni();
		notifyPromatraci();
	}
	
	private void notifyPromatraci() {
		for (var p : promatraci) {
			p.update(kolekcija);
		}
		
	}

	public void attach(Promatrac promatrac) {
		promatraci.add(promatrac);
	}
	
	public void dettach(Promatrac promatrac) {
		promatraci.remove(promatrac);
	}
	

}
