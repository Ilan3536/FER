package hr.fer.ouup.model;

public abstract class Animal {
	
	public abstract String name();
	public abstract String greet();
	public abstract String menu();
	
	public void animalPrintGreeting() {
		System.out.println(this.name() + " pozdravlja: " + this.greet());
	}
	
	public void animalPrintMenu() {
		System.out.println(this.name() + " voli: " + this.menu());

	}

}
