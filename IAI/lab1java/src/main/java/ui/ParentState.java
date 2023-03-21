package ui;

public class ParentState {
	
	private String parent;
	private String child;
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public ParentState(String parent, String child) {
		super();
		this.parent = parent;
		this.child = child;
	}
	
	

}
