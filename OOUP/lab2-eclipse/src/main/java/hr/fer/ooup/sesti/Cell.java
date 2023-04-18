package hr.fer.ooup.sesti;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Observer{
	
	public String exp;
	public Sheet sheet;
	private double value;
	public List<Observer> observers;
	
	public Cell(String exp, Sheet sheet) {
		super();
		this.exp = exp;
		this.sheet = sheet;
		this.value = sheet.evaluate(this, new ArrayList<Cell>());
		observers = new ArrayList<>();
		
		for (var referencedCell : sheet.getRefs(this)) {
			referencedCell.attach(this);
		}
		
		
	}
	
	private void notifyObservers() {
		for (var observer : observers) {
			observer.update();
		}
	}

	public void changeCell(String exp, Sheet sheet) {
		this.exp = exp;
		this.value = sheet.evaluate(this, new ArrayList<Cell>());
		
		for (var referencedCell : sheet.getRefs(this)) {
			referencedCell.attach(this);
		}
		notifyObservers();
	}

	@Override
	public void update() {
		value = sheet.evaluate(this, new ArrayList<Cell>());
	}

	public double getValue() {
		return value;
	}
	
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	public void dettach(Observer observer) {
		observers.remove(observer);
	}
	
	@Override
	public String toString() {
		return ""+value;
	}
	
	
	
	

}
