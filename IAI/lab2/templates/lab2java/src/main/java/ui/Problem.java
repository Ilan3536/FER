package ui;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Problem {

	public static String GOAL_CLAUSE;
	public static Set<Clause> parseClauses(String clausesPath) {
		List<String> allLines;
		
		try {

			//allLines = Files.readAllLines(Paths.get(clausesPath).toAbsolutePath());
			allLines = Files.readAllLines(Paths.get("C:\\FER\\6. semestar\\IAI\\autograder\\data\\lab2\\files\\" + clausesPath + ".txt"));
			
			Set<Clause> clauses = new HashSet<>();
			
			int i = 0;
			int clausesCount=1;
			while ( i < allLines.size()) {
				if (allLines.get(i).startsWith("#")) {
					i++;
					continue;
				}
				
//				if (i == allLines.size() - 1) {
//					String literal = allLines.get(i++);
//					if (literal.startsWith("~")) {
//						literal = literal.substring(1);
//					} else {
//						literal = "~" + literal;
//					}
//					Set<String> clauseLiterals = new HashSet<>();
//					clauseLiterals.add(literal.toLowerCase());
//					Clause clause = new Clause(clauseLiterals, i);
//					clauses.add(clause);
//					break;
//				}
				
				if (i == allLines.size()-1 ) {
					
					GOAL_CLAUSE = allLines.get(i).toLowerCase();
				}
				String[] literals = allLines.get(i++).toLowerCase().split(" v ");
				Set<String> clauseLiterals = new HashSet<>();
				
				clauseLiterals = Arrays.stream(literals).collect(Collectors.toSet());
				
				Clause clause = new Clause(clauseLiterals, clausesCount++);
				
				
				
				clauses.add(clause);
				
			}
			return clauses;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return null;
	}

	

}
