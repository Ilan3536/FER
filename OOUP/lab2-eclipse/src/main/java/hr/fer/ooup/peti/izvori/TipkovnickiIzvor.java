package hr.fer.ooup.peti.izvori;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.Scanner;

public class TipkovnickiIzvor implements Izvor {
	
	public TipkovnickiIzvor() {
	}

	@Override
	public List<Integer> kreni() {
		
		Scanner scanner = new Scanner(System.in);
		int number;
		List<Integer> list = new ArrayList<>();
		

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
		return list;
	}

}
