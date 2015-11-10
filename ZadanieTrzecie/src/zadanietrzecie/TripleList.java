package zadanietrzecie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TripleList<T> implements Iterable<T> {

	private int count = 0;
	//Lista na potrzeby interface Iterable. Znacznie ułatwi zadanie
	List<T> list = new ArrayList<T>();
	
	private T value;
	private TripleList<T> nextElement;
	private TripleList<T> previousElement;
	private TripleList<T> middleElement;
	
	public TripleList() {
		value = null;
		previousElement = null;
		middleElement = null;
		nextElement = null;
	}

	public TripleList(T value, TripleList<T> previousElement, TripleList<T> middleElement,
			TripleList<T> nextElement) {
		
		this.value = value;
		this.previousElement = previousElement;
		this.middleElement = middleElement;
		this.nextElement = nextElement;
	}

	public int count() {
		return count;
	}

	public void add(T value) {

		if (count == 0) {
			
			this.value = value;
			count++;
			list.add(value);
			return;
		}
		
		TripleList<T> last = this;
		
		while(last.getNextElement() != null) 
			last = last.getNextElement();
		
	
		if ((count % 2) == 1) {
			
			 last.middleElement = new TripleList<T>(value,null,last,null);
			 list.add(value);
		
		} else {
			
			last.nextElement = new TripleList<T>(value,last,null,null);
			list.add(value);
		}

		count++;
	}
	
	public void addAll(TripleList<T> list) {
		
		Iterator<T> l = list.iterator();
		
		while(l.hasNext()){
			add(l.next());
		}
	}

	public TripleList<T> getNextElement() {
		return nextElement;
	}

	public TripleList<T> getPreviousElement() {
		return previousElement;
	}

	public T getValue() {
		return value;
	}

	public TripleList<T> getMiddleElement() {
		return middleElement;
	}

	@Override
	public Iterator<T> iterator() {	
		
		return list.iterator();
		
	}

	public List<T> toList() {
		return list;
	}
}
