package ui;

public class Solution {

	public static void main(String ... args) {
		
		String clausesPath="";
		String userCommandsPath="";
		
		if (args[0].equals("resolution")) {
			clausesPath = args[1];
			Algorithms.RefutationResolution(clausesPath);
			
		} else if (args[0].equals("cooking")) {
			clausesPath = args[1];
			userCommandsPath = args[2];
		}
		
		
	}

}
