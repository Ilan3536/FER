package ui.tree;

public class Subtree {
	private String featureValue;
	private ITreeElement elem;
	
	public Subtree(String featureValue, ITreeElement elem) {
		this.featureValue = featureValue;
		this.elem = elem;
	}

	public String getFeatureValue() {
		return featureValue;
	}

	public ITreeElement getElem() {
		return elem;
	}
	
}
