package ui.checks;

import java.util.List;

import ui.StateSpaceDescriptor;

public interface ICheckGetter {
	List<CheckResult> check(StateSpaceDescriptor ssd);
}
