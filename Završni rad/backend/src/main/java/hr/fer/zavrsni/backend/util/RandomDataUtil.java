package hr.fer.zavrsni.backend.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class RandomDataUtil {

	static String[] worldRecords = { "empty",
			"00:00:20.910", "00:00:23.670", "00:00:46.860", "00:00:51.710", "00:01:42.000",
			"00:01:52.980", "00:03:40.070", "00:03:56.080", "00:07:32.120", "00:08:04.790",
			"00:14:31.020", "00:15:20.480", "00:00:23.710", "00:00:26.980", "00:00:51.600",
			"00:00:57.450", "00:01:51.920", "00:02:03.140", "00:00:25.950", "00:00:29.300",
			"00:00:56.880", "00:01:04.130", "00:02:05.950", "00:02:18.950", "00:00:22.270",
			"00:00:24.430", "00:00:49.450", "00:00:55.480", "00:01:50.340", "00:02:01.810",
			"00:01:54.000", "00:02:06.120", "00:04:03.840", "00:04:25.870"
	};
	static String[] lowerBounds = populateBound(5);
	static String[] upperBounds = populateBound(15);
	static int RESULTS = 20;
	public static void main(String[] args) {

		int iddisciplina = 3;
		int indexrez = 142;
		int indexoso = 150;
		for (; iddisciplina < 35; iddisciplina++) {
			generateRezultati(iddisciplina, indexrez, worldRecords[iddisciplina]); //starting index for rezultat
			generateOsobe( iddisciplina % 2 == 0 ? "F" : "M" , indexoso); //starting index for osoba
			indexrez+=RESULTS;
			indexoso+=RESULTS;
		}
		
	}
	
	
	public static void generateRezultati(int iddisciplina, int index, String rekord) {
		String disciplina = ""+iddisciplina;
		 String ouputfilePathRez = 
				 "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\randomRezultati.txt";
	        int i = index; //starting index for rezultat
	        String timeRange1 = lowerBounds[iddisciplina];
	        String timeRange2 = upperBounds[iddisciplina];
	        try ( FileWriter writerRez = new FileWriter(ouputfilePathRez, true)) {
	        	
	        	//writerRez.write("INSERT INTO rezultat VALUES\n");
	        	String rezultat = "";
	        	for ( ; i < index+RESULTS; i++) {
	        		System.out.println("time1: " + timeRange1 + " time2: " + timeRange2);
	        		String time = generateRandomTime(timeRange1, timeRange2).toString();
	        		rezultat = "(" + i + ", " + iddisciplina + ", 12, \'1970-01-01 " + time + 
	        				"\', " + calculatePoints(rekord, time) + ", " +
	        				"\'2022-02-24\'" + ", " + (i+8) + "),\n";
	        		
//	        		if (i == index + RESULTS-1) {
//	        			writerRez.write(rezultat.substring(0,rezultat.length()-2) + ";\n");
//	        			continue;
//	        		}
	        		writerRez.write(rezultat);
	        	}
	        	
	        	
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	

	public static void generateOsobe(String spol, int index) {
		String indexSpol = index + spol;
		 String ouputfilePathRez = 
				 "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\randomOsobe.txt";
	        int i = index; //starting index for
	        try ( FileWriter writerRez = new FileWriter(ouputfilePathRez, true)) {
	        	
//	        	writerRez.write("INSERT INTO osoba (idosoba, iddrzava, imeosoba, prezimeosoba, spol, datumrodjenja, visina, tezina, idklub) "
//	        			+ "VALUES\n");
	        	String rezultat = "";
	        	for ( ; i < index+RESULTS; i++) {
	        		
	        		rezultat = "(" + i + ", 8, "   + "\'" + generateRandomName(spol) + "\'" + ",  " + "\'" + generateRandomSurname() + "\'" + ", " +
	        					"\'" + spol + "\', " + "\'" + generateRandomDate(LocalDate.of(1990, 1, 1),LocalDate.of(2008, 1, 1)) + "\'" + ", " + 
	        					generateRandomNumber(160, 210) + ", " + generateRandomNumber(70, 100) + ", " + generateRandomNumber(1, 10) +  "),\n";
	        		
//	        		if (i == index + RESULTS-1) {
//	        			writerRez.write(rezultat.substring(0,rezultat.length()-2) + ";\n");
//	        			continue;
//	        		}
	        		writerRez.write(rezultat);
	        	}
	        	
	        	
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static String[] populateBound(int bound) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

		String[] arr = new String[worldRecords.length];
		arr[0] = "empty";
		for (int i = 1; i < worldRecords.length; i++) {
		  LocalTime time = LocalTime.parse(worldRecords[i], formatter);
		  int minutes = time.getMinute();
		  int seconds = time.getSecond();
		  String milliseconds = worldRecords[i].substring(9, 12);

		  seconds += (minutes + 1) * bound;
		  minutes += seconds / 60;
		  seconds %= 60;
		  LocalTime newTime = LocalTime.of(0, minutes, seconds, Integer.parseInt(milliseconds) * 1000000);
		  arr[i] = newTime.format(formatter);
		}
		
		return arr;
	}
	
	public static LocalTime generateRandomTime(String startTime, String endTime) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        long startMillis = start.toNanoOfDay() / 1_000_000; // Convert to milliseconds
        long endMillis = end.toNanoOfDay() / 1_000_000; // Convert to milliseconds
        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis + 1);
        return LocalTime.ofNanoOfDay(randomMillis * 1_000_000); // Convert back to nanoseconds
    }
	
	public static int generateRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Invalid range");
        }
        
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
	
	public static int calculatePoints(String time1, String time2) {
		LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);
        
        Duration duration1 = Duration.between(LocalTime.MIN, t1);
        Duration duration2 = Duration.between(LocalTime.MIN, t2);
        
        double result = (double) duration1.toMillis() / duration2.toMillis() * 1000;
        
        return (int) result;
	}
	public static LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        
        Random random = new Random();
        long randomEpochDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay));
        
        return LocalDate.ofEpochDay(randomEpochDay);
    }
	
	public static String generateRandomName(String gender) {
		String[] namesF = {
	            "Ana", "Mia", "Ema", "Lana", "Lara", "Nina", "Elena", "Petra", "Iva", "Lucija",
	            "Lorena", "Martina", "Tara", "Lea", "Lena", "Klara", "Sara", "Nika", "Magdalena", "Ela",
	            "Marta", "Karla", "Dora", "Ivana", "Mihaela", "Daria", "Tea", "Hana", "Eva", "Laura",
	            "Nora", "Vita", "Luka", "Maja", "Dunja", "Marina", "Viktoria", "Barbara", "Anja", "Monika"
	        };
		String[] namesM = {
	            "Ivan", "Marko", "Matija", "Luka", "Filip", "David", "Antonio", "Petar", "Ivano", "Dino",
	            "Leo", "Josip", "Gabriel", "Nikola", "Roko", "Lovro", "Stjepan", "Leon", "Borna", "Dominik",
	            "Toni", "Lovro", "Kristijan", "Filip", "Fran", "Ilija", "Stipe", "Renato", "Emanuel", "Bruno",
	            "Davor", "Dario", "Matej", "Marin", "Ljubomir", "Dinko", "Mario", "Nino", "Dorian", "Karlo"
	        };
		return gender == "M" ? namesM[generateRandomNumber(0, 39)] : namesF[generateRandomNumber(0, 39)];
	}
	public static String generateRandomSurname() {

		String[] croatianSurnames = {
            "Kovačić", "Horvat", "Novak", "Kovač", "Babić",
            "Jurić", "Petrović", "Matić", "Tomić", "Pavić",
            "Marković", "Đurđević", "Knežević", "Kovačević", "Vuković",
            "Radić", "Božić", "Kovaček", "Kralj", "Šarić",
            "Marinović", "Blažević", "Mišić", "Kušec", "Lovrić",
            "Knežić", "Dujmović", "Grgić", "Perić", "Luketić",
            "Ivančić", "Mikulić", "Jakovljević", "Kos", "Nikolić",
            "Bogdanović", "Mlinarić", "Barać", "Radoš", "Sudar",
            "Blažić"
        };
		
		return croatianSurnames[generateRandomNumber(0,39)];
	}
}
