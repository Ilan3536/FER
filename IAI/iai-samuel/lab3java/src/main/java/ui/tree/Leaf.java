package ui.tree;

import java.util.Map;

public class Leaf implements ITreeElement {
	private String classLabelValue;

	public Leaf(String classLabelValue) {
		this.classLabelValue = classLabelValue;
	}

	public String getClassLabelValue() {
		return classLabelValue;
	}

	@Override
	public void printBranch(String branchStr, int depth) {
		System.out.println(branchStr + classLabelValue);
	}

	@Override
	public String traverse(Map<String, String> row) {
		return classLabelValue;
	}
	
}
