package zadaniepiate;

public class Element<T extends Comparable<T>> implements Comparable<Element<T>> {
	
	private T value;
	private int count;
		
	public T getValue() {
		return value;
	}

	public Element() {
		this.value = null;
		this.count = 0;
	}
	
	public Element(T value) {
		this.value = value;
		this.count = 1;
	}

	@Override
	public String toString() {
		return "Element [value=" + value + "]";
	}

	@Override
	public int compareTo(Element<T> o) {
		return value.compareTo(o.getValue());
	}

	public int getCount() {
		return count;
	}

	public void countAdd() {
		this.count++;
	}
	
}
