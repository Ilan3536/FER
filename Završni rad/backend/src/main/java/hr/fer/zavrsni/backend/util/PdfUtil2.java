package hr.fer.zavrsni.backend.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PdfUtil2 {
	
	public static void main(String[] args) throws IOException {
        String inputfilePath = "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\data-50s-m.txt";
        String ouputfilePathOsoba = "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\50slobodno-osoba-M.txt";
        String ouputfilePathRez = "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\50slobodno-rez-M.txt";
        
        
        try ( FileReader fileReader = new FileReader(inputfilePath);
              BufferedReader bufferedReader = new BufferedReader(fileReader);
        	  FileWriter writerOsoba = new FileWriter(ouputfilePathOsoba); 
              FileWriter writerRez = new FileWriter(ouputfilePathRez)) {
        		int idosoba=106;
        		int idrezultat=35;
	        	String line;
	        	Boolean podatciKrenuli = false;
	        	Boolean women=false;
	            while ((line = bufferedReader.readLine()) != null) {
	            	
	            	String osoba="";
	            	String rezultat="";
	            	if (line.contains("MLAĐI SENIORI")) {
	            		podatciKrenuli=true;
	            		continue;
	            	}
	            	if (line.contains("MLAĐe SENIORKE")) {
	            		podatciKrenuli=true;
	            		women=true;
	            		continue;
	            	}
	            	if (!podatciKrenuli) {
	            		continue;
	            	}
	            	
	            	String[] arr = line.split(" ");
	            	
	            	if (isNumeric(arr[0])) {
	            		osoba += "("+ idosoba + ", 8, " + arr[1] + ", " + arr[2] + (women ? ", F, " : ", M, ")  + 
	            				arr[3] + "-01-01, " + generateRandomNumber(1, 10) + "),";
	            		
	            		rezultat += "(" + 
	            		
	            		idosoba++;
	            	}
	            	
	            	
	            	
	            	writerOsoba.write(osoba + "\n");
	            	System.out.println(line);
	            }
        	
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
	}
	
	
	public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '.' && c != '-') {
                return false;
            }
        }
        
        return true;
    }
	
	public static int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Invalid range");
        }
        
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
