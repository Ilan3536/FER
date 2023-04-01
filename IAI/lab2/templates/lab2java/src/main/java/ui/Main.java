package ui;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<>();
		set.add("prvi");
		set.add("drugi");
		set.add("treci");
		
		for( var s : set) {
			if (s.equals("treci")) {
				set.add("cetvrti");
			}
		}
		System.out.println(set);
	}

	private static void remove(Set<String> set) {
		set.remove("treci");
		
	}

}
