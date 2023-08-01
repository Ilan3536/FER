package ui;

import java.util.Map;
import java.util.Set;

public class StateSpaceDescriptor {
	private String start;
	private Set<String> finish;
	private Map<String, State> states;
	
	public StateSpaceDescriptor(String start, Set<String> finish, Map<String, State> states) {
		this.start = start;
		this.finish = finish;
		this.states = states;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public String getStart() {
		return start;
	}
	
	public Set<String> getFinish() {
		return finish;
	}
	
	public Map<String, State> getStates() {
		return states;
	}
}
