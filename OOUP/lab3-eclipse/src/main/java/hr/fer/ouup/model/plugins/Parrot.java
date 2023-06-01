package hr.fer.ouup.model.plugins;

import hr.fer.ouup.model.Animal;

public class Parrot extends Animal {

	private String animalName;

	public Parrot(String name) {
		this.animalName = name;
	}

	@Override
	public String name() {
		return animalName;
	}

	@Override
	public String greet() {
		return "Dobar dan dragi korisniče!";
	}

	@Override
	public String menu() {
		return "sjemenke";
	}

}
