package hr.fer.ouup.model;

public class Main {

	public static void main(String[] args) {

		Animal parrot = AnimalFactory.newInstance("Parrot", "Slobodanka");
		parrot.animalPrintGreeting();
		parrot.animalPrintMenu();
	}

}
