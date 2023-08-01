package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import ui.logic.Clause;
import ui.logic.ExpertSystem;
import ui.result.ResolutionResult;

public class Solution {
	private static final Function<String, List<String>> LOAD = path -> {
		try {
			return Files.readAllLines(Path.of(path))
					    .stream()
					    .filter(line -> !line.startsWith("#"))
					    .map(line -> line.toLowerCase())
					    .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public static void main(String ... args) {
		if (args.length > 3 && args.length < 2) {
			System.out.println("Wrong number of arguments!");
			return;
		}
		
		if (args[0].equals("resolution")) {
			List<String> resolutionLines = LOAD.apply(args[1]);
			String goal = resolutionLines.remove(resolutionLines.size() - 1);
			ExpertSystem expertSystem = createExpertSystem(resolutionLines);
			Resolution r = expertSystem.resolve(goal);
			ResolutionResult result = r.resolution();
			System.out.println(result.toString());
		} else if (args[0].equals("cooking")) {
			ExpertSystem expertSystem = createExpertSystem(LOAD.apply(args[1]));
			System.out.println(expertSystem.showSystem() + "\n");
			
			for (String line : LOAD.apply(args[2])) {
				String query = line.substring(0, line.length() - 2);
				char command = line.charAt(line.length() - 1);
				
				System.out.println("User's command: " + line);
				switch (command) {
				case '?' -> {
					Resolution r = expertSystem.resolve(query);
					ResolutionResult result = r.resolution();
					System.out.println(result.toString());
				}
				
				case '+' -> {
					expertSystem.addClause(query);
					System.out.println("Added " + query);
				}
				
				case '-' -> {
					expertSystem.removeClause(query);
					System.out.println("Removed " + query);
				}
				
				default ->
				throw new IllegalArgumentException("Unexpected value: " + command);
				}
				System.out.println();
			}
		} else {
			System.out.println("First argument can only be resolution or cooking");
		}
	}
	
	private static ExpertSystem createExpertSystem(List<String> clauseLines) {
		Set<Clause> clauses = new LinkedHashSet<>();
		
		for (String line : clauseLines) {
			Clause clause = ExpertSystem.createClause(line);
			if(clause != null) {
				clauses.add(clause);
			}
		}
		
		return new ExpertSystem(clauses);
	}
}
