package hr.fer.ooup.sesti;

public class Main {

	
	public static void main(String[] args) {
		Sheet sheet = new Sheet(5, 5);
		sheet.set("A1", "2");
		sheet.set("A2", "5");
		sheet.set("B1", "5");
		sheet.set("A3", "A1+A2");
		sheet.set("B1", "A1");
		
		System.out.println(sheet);
		
		sheet.set("A1", "4");
		sheet.set("A4","A1+A3");
		
		System.out.println(sheet);
		
		sheet.set("A1", "7");
		sheet.set("A5","A1+A4");
		
		System.out.println(sheet);
		
		
		
		try {
			//sheet.set("A1", "33");
			sheet.set("C2", "2");
			sheet.set("C3", "3");
			
			sheet.set("C1", "C2");
			sheet.set("C2", "C3");
			sheet.set("C3", "C1");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		System.out.println(sheet);
		
	}
}
