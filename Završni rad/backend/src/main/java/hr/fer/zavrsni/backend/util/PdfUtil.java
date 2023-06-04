package hr.fer.zavrsni.backend.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PdfUtil {
	
	public static void main(String[] args) throws IOException {
        String inputfilePath = "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\data.txt";
        String ouputfilePath = "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\data_limiti.txt";
        
        
        try ( FileReader fileReader = new FileReader(inputfilePath);
              BufferedReader bufferedReader = new BufferedReader(fileReader);
        	  FileWriter writer = new FileWriter(ouputfilePath)) {
        	
	        	String line;
	            while ((line = bufferedReader.readLine()) != null) {

	            	String result="";
	            	String[] results = line.split(" ");
	            	
	            	if (results[0].startsWith("50") || results[0].startsWith("1500")) {
	            		result = results[0] + " " + results[1] + " "+ results[2] + " " +  results[8];
	            	} else {
	            		result = results[0] + " " + results[1] +  " " +results[2] + " " + results[10];
	            	}
	            	
	            	writer.write(result + "\n");
	            	System.out.println(line);
	            }
        	
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
	}

}
