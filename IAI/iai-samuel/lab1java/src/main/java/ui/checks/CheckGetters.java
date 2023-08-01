package ui.checks;

public class CheckGetters {
	public static final ICheckGetter CHECK_OPTIMISTIC = ssd -> OptimisticCheck.check(ssd);
	public static final ICheckGetter CHECK_CONSISTENT = ssd -> ConsistentCheck.check(ssd);
}
