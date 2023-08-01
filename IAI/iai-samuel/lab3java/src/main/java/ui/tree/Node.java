package ui.tree;

import java.util.List;
import java.util.Map;

public class Node implements ITreeElement {

	private String feature;
	private List<Subtree> subtrees;
	
	public Node(String feature, List<Subtree> subtrees) {
		this.feature = feature;
		this.subtrees = subtrees;
	}

	public String getFeature() {
		return feature;
	}

	public List<Subtree> getSubtrees() {
		return subtrees;
	}

	@Override
	public void printBranch(String branchStr, int depth) {
		StringBuilder sb; 
		for (Subtree subtree : subtrees) {
			sb = new StringBuilder();
			sb.append(branchStr)
			  .append(depth)
			  .append(':')
			  .append(feature)
			  .append('=')
			  .append(subtree.getFeatureValue())
			  .append(' ');
			subtree.getElem().printBranch(sb.toString(), depth + 1);
		}
	}

	@Override
	public String traverse(Map<String, String> row) {
		for (Subtree subtree : subtrees) {
			if (row.get(feature).equals(subtree.getFeatureValue())) {
				return subtree.getElem().traverse(row);
			}
		}
		return null;
	}

}
