package ui.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dataset implements Iterable<Map<String, String>> {
	
	private static final Comparator<Map.Entry<String, Long>> OCCURRENCE_COMPARATOR = 
			(e1, e2) -> {
				int c = Integer.compare(e2.getValue().intValue(), e1.getValue().intValue());
				if (c != 0) return c;
				return e1.getKey().compareTo(e2.getKey());
			};
	
	private Map<String, List<String>> inputData;
	private List<String> classLabelColumn;
	private String classLabelName;

	public Dataset(Map<String, List<String>> inputData, String classLabelName) {
		this.inputData = inputData;
		this.classLabelName = classLabelName;
		this.classLabelColumn = inputData.get(classLabelName);
	}
	
	public Set<String> getHeader() {
		return inputData.keySet();
	}
	
	public List<String> getColumn(String columnName) {
		return inputData.get(columnName);
	}
	
	public String getClassLabelName() {
		return classLabelName;
	}
	
	public List<String> getClassLabelColumn() {
		return classLabelColumn;
	}
	
	public boolean isEmpty() {
		return inputData.isEmpty();
	}
	
	public int size() {
		return inputData.get(classLabelName).size();
	}
	
	public Dataset shortenDataset(String bestFeature, String featureValue) {
		Map<String, List<String>> newInputData = new HashMap<>();
		Set<String> nameSet = inputData.keySet();
		
		for (String name : nameSet) {
			newInputData.put(name, new ArrayList<>());
		}
		
		List<String> removedColumn = inputData.get(bestFeature);
		for (int i = 0; i < removedColumn.size(); i++) {
			if (removedColumn.get(i).equals(featureValue)) {
				for (String name : nameSet) {
					newInputData.get(name).add(inputData.get(name).get(i));						
				}				
			}
		}
		
		return new Dataset(newInputData, classLabelName);
	}
	
	public String getMaxOccurring() {
		Map<String, Long> count = 
			classLabelColumn.stream()
					        .collect(Collectors.groupingBy(Function.identity(),
					        		 Collectors.counting()));
		
		return count.entrySet()
					.stream()
					.sorted(OCCURRENCE_COMPARATOR)
					.map(e -> e.getKey())
					.findFirst()					
					.get();
	}

	@Override
	public int hashCode() {
		return Objects.hash(inputData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Dataset)) {
			return false;
		}
		Dataset other = (Dataset) obj;
		return Objects.equals(inputData, other.inputData);
	}

	@Override
	public Iterator<Map<String, String>> iterator() {
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<Map<String, String>> {

		private int index = 0;
		private int size = size();
		
		@Override
		public boolean hasNext() {
			return index != size;
		}

		@Override
		public Map<String, String> next() {
			Map<String, String> row = new HashMap<>();
			for (String feature : inputData.keySet()) {
				row.put(feature, inputData.get(feature).get(index));
			}
			index++;
			return row;
		}
		
	}
}
