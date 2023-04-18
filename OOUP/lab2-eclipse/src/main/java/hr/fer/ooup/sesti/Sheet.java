package hr.fer.ooup.sesti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Sheet {
	
	Cell[][] array;
	
	
	public Sheet(int rows, int columns) {
		array = new Cell[rows][columns];
	}
	
	public Cell cell(String ref) {
		int row = Integer.parseInt(ref.replaceAll("\\D+","")) - 1; 
		int col = ref.toUpperCase().charAt(0) - 'A'; 
		return array[row][col];
	}
	
	public void set(String ref, String content) {
		int row = Integer.parseInt(ref.replaceAll("\\D+","")) - 1; 
		int col = ref.toUpperCase().charAt(0) - 'A'; 
		
		if (array[row][col] == null) {
			array[row][col] =  new Cell(content, this);
		} else {
			for (var referencedCell : this.getRefs(array[row][col])) {
				referencedCell.dettach(array[row][col]);
			}
			
			array[row][col].changeCell(content, this);
		}
		
	}
	
	public List<Cell> getRefs(Cell cell){
		String[] referencedCells = cell.exp.split("\\+");
		List<Cell> listCells = new ArrayList<>();
		
		if (referencedCells.length>1) {
			listCells.add(cell(referencedCells[0]));
			listCells.add(cell(referencedCells[1]));
			return listCells;
		}
		if (!StringUtils.isNumeric(referencedCells[0])) {
			listCells.add(cell(referencedCells[0]));			
		}
		return listCells;
		
	}
	
	public double evaluate(Cell cell, List<Cell> visited) {
		if (cell==null) {
			return 0;
		}
		if (StringUtils.isNumeric(cell.exp)) {
			return Double.parseDouble(cell.exp);
		}
		if (visited.contains(cell)) throw new StackOverflowError("Loop found. Throw exception so program doesn't crash.");
		
		visited.add(cell);
		
		List<Cell> refCells = getRefs(cell);

		if (refCells.size()>1) {			
			return evaluate(refCells.get(0), visited) + evaluate(refCells.get(1), visited);
		}
		
		return evaluate(refCells.get(0), visited);
				
	}


	@Override
	public String toString() {
		String str="";
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				str+=array[i][j] + " ";
			}
			str+="\n";
		}
		return str;
	}
	
	
	

}
