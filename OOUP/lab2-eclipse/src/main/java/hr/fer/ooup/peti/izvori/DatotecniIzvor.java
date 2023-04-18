package hr.fer.ooup.peti.izvori;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatotecniIzvor implements Izvor {
	
	String filename;
	
	public DatotecniIzvor(String filename) {
		super();
		this.filename = filename;
	}

	@Override
	public List<Integer> kreni() {
		List<Integer> list = new ArrayList<>();
	    try {
	        FileInputStream fis = new FileInputStream(filename);
	        Scanner scanner = new Scanner(fis);
	        int number;

	        do {
	            number = scanner.nextInt();

	            if (number == -1) break;

	            list.add(number);
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	        } while (number != -1);

	        scanner.close();
	        fis.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

}
