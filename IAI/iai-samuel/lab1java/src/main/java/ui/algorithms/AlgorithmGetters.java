package ui.algorithms;

public class AlgorithmGetters {
	public static final ISearchAlgorithmGetter BFS = ssd -> ui.algorithms.BFS.search(ssd);
	public static final ISearchAlgorithmGetter UCS = ssd -> ui.algorithms.UCS.search(ssd);
	public static final ISearchAlgorithmGetter AStar = ssd -> ui.algorithms.AStar.search(ssd);
}
