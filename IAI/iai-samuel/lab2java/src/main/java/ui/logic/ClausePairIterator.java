package ui.logic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class ClausePairIterator implements Iterator<ClausePair> {
	private Clause[] clauseList;
	private Clause[] sos;
	
	private int first = 0;
	private int second = 0;

	public static Iterable<ClausePair> getIterator(Set<Clause> clausesSet, Set<Clause> sosSet) {
		Clause[] clauses = clausesSet.toArray(Clause[]::new);
		Clause[] sos = sosSet.toArray(Clause[]::new);
		return () -> new ClausePairIterator(clauses, sos);
	}
	
	private ClausePairIterator(Clause[] clauseList, Clause[] sos) {
		this.clauseList = clauseList;
		this.sos = sos;
	}

	@Override
	public boolean hasNext() {
		return first != clauseList.length;
	}

	@Override
	public ClausePair next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		ClausePair pair = new ClausePair(clauseList[first], sos[second]);
		
		if (++second == sos.length) {
			first++;
			second = 0;
		}
		
		return pair;
	}
	
}