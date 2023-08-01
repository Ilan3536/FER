package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import ui.algorithms.AlgorithmGetters;
import ui.algorithms.AlgorithmResult;
import ui.algorithms.ISearchAlgorithmGetter;
import ui.checks.CheckGetters;
import ui.checks.CheckResult;
import ui.checks.ICheckGetter;

public class Solution {
	private static final Function<String, List<String>> LOAD = path -> {
		try {
			return Files.readAllLines(Path.of(path))
					    .stream()
					    .filter(line -> !line.startsWith("#"))
					    .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public static void main(String ... args) {
		ISearchAlgorithmGetter algorithmGetter = null;
		ICheckGetter checkGetter = null;
		String lastArg = null;
		String algo = null;
		String stateSpacePath = null;
		String heuristicFunctionPath = null;
		boolean checkOptimistic = false;
		boolean checkConsistent = false;
		
		for(String arg : args) {
			if (lastArg != null) {
				if (lastArg.equals("--alg")) {
					switch (arg) {
					case "bfs":
						algorithmGetter = AlgorithmGetters.BFS;
						algo = "# BFS";
						break;
						
					case "ucs":
						algorithmGetter = AlgorithmGetters.UCS;
						algo = "# UCS";
						break;	
						
					case "astar":
						algorithmGetter = AlgorithmGetters.AStar;
						algo = "# A-STAR";
						break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + arg);
					}
				} else if (lastArg.equals("--ss")) {
					stateSpacePath = arg;
				} else  {
					heuristicFunctionPath = arg;
				}
				
				lastArg = null;
			} else if (arg.matches("(--alg|--ss|--h)")) {
				lastArg = arg;
			} else if (arg.contains("--check-optimistic")) {
				checkGetter = CheckGetters.CHECK_OPTIMISTIC;
				checkOptimistic = true;
				algo = "# HEURISTIC-OPTIMISTIC";
			} else if (arg.contains("--check-consistent")) {
				checkGetter = CheckGetters.CHECK_CONSISTENT;
				checkConsistent = true;
				algo = "# HEURISTIC-CONSISTENT";
			}
		}
		StringBuilder sb = new StringBuilder(algo);
		sb.append(" ");
		
		List<String> stateLines = LOAD.apply(stateSpacePath);
		StateSpaceDescriptor ssd = parseStateSpace(stateLines);
		if (heuristicFunctionPath != null) {
			List<String> heuristicLines = LOAD.apply(heuristicFunctionPath);			
			parseHeuristic(ssd, heuristicLines);
			sb.append(heuristicFunctionPath);
		}
		sb.append("\n");
		
		if (checkOptimistic || checkConsistent) {
			List<CheckResult> results = checkGetter.check(ssd);
			boolean passed = true;
			for (CheckResult result : results) {
				passed = passed && result.isCheckPassed();
				sb.append(result);
			}
			sb.append("[CONCLUSION]: Heuristic ").append(passed ? "is " : "is not ");
			if (checkGetter == CheckGetters.CHECK_CONSISTENT) {
				sb.append("consistent.");
			} else {
				sb.append("optimistic.");
			}
		} else {
			AlgorithmResult result = algorithmGetter.search(ssd);
			if (result == null) {
				sb.append("[FOUND_SOLUTION]: no\n");
			} else {
				sb.append(result);
			}
		}
		System.out.println(sb.toString());
	}

	private static StateSpaceDescriptor parseStateSpace(List<String> stateLines) {
		String start = stateLines.get(0);
		Set<String> finish = new HashSet<>(Arrays.asList(stateLines.get(1).split("\\s+")));
		Map<String, State> states = new HashMap<>();
		
		for (int i = 2; i < stateLines.size(); i++) {
			String[] stateLine = stateLines.get(i).split(":\\s*");
			String stateName = stateLine[0];
			State state = new State(stateName);
			states.put(stateName, state);
			
			if (stateLine.length < 2) {
				continue;
			}
			String[] successors = stateLine[1].split("\\s+");
			for (String successor : successors) {
				String[] successorDesc = successor.split(",");
				state.addSuccessor(new Successor(successorDesc[0], Integer.parseInt(successorDesc[1])));
			}
		}
		return new StateSpaceDescriptor(start, finish, states);
	}
	
	private static void parseHeuristic(StateSpaceDescriptor ssd, List<String> heuristicLines) {
		for (String line : heuristicLines) {
			String[] heuristicDesc = line.split(":\\s+");
			ssd.getStates().get(heuristicDesc[0]).setHeuristic(Integer.parseInt(heuristicDesc[1]));
		}
	}
}
