package hr.fer.zavrsni.backend.util;

import java.time.Duration;
import java.time.LocalTime;

public class Test {
	
	static String[] worldRecords = { "empty",
			"00:00:20.910", "00:00:23.670", "00:00:46.860", "00:00:51.710", "00:01:42.000",
			"00:01:52.980", "00:03:40.070", "00:03:56.080", "00:07:32.120", "00:08:04.790",
			"00:14:31.020", "00:15:20.480", "00:00:23.710", "00:00:26.980", "00:00:51.600",
			"00:00:57.450", "00:01:51.920", "00:02:03.140", "00:00:25.950", "00:00:29.300",
			"00:00:56.880", "00:01:04.130", "00:02:05.950", "00:02:18.950", "00:00:22.270",
			"00:00:24.430", "00:00:49.450", "00:00:55.480", "00:01:50.340", "00:02:01.810",
			"00:01:54.000", "00:02:06.120", "00:04:03.840", "00:04:25.870"
	};

	public static void main(String[] args) {
		
		
		System.out.println(calculatePoints("00:00:20.910", "00:00:25.080"));
		System.out.println(calculatePoints("00:00:46.860", "00:00:52.180"));
		System.out.println(calculatePoints("00:01:42.000", "00:01:55.110"));

	}
	
	public static int calculatePoints(String time1, String time2) {
		LocalTime t1 = LocalTime.parse(time1);
        LocalTime t2 = LocalTime.parse(time2);
        
        Duration duration1 = Duration.between(LocalTime.MIN, t1);
        Duration duration2 = Duration.between(LocalTime.MIN, t2);
        
        double result = (double) duration1.toMillis() / duration2.toMillis() * 1000;
        
        return (int) result;
	}

}
