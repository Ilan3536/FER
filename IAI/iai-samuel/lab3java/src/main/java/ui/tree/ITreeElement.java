package ui.tree;

import java.util.Map;

public interface ITreeElement {
	
	void printBranch(String branchStr, int depth);
	
	String traverse(Map<String, String> row);
}
