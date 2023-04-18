package hr.fer.ooup.peti.promatraci;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ZapisPromatrac implements Promatrac{

	@Override
	public void update(List<Integer> kolekcija) {
		
		try {
			LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
			String filename = "Zapis_" + now.format(formatter2) + ".txt";
			
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Integer i : kolekcija) {
                writer.write(i.toString());
                writer.write(" ");
            }
            
            writer.write("\n" + now.format(formatter1));
            writer.close();
            
            System.out.println("Kolekcija zapisana u " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
