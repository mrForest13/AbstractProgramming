package zadanieczwarte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Composition<T> implements Executeable<T>, Iterable<Executeable<T>> {

	private List<Executeable<T>> executeableList;

	public Composition() {
		this.executeableList = new ArrayList<>();
	}

	public Composition(Executeable<T> arg) {
		this.executeableList = new ArrayList<>(Arrays.asList(arg));
	}

	public Composition(List<Executeable<T>> list) {
		this.executeableList = list;
	}
	
	public Composition(Executeable<T> arg, Executeable<T> arg1) {
		this.executeableList = new ArrayList<>(Arrays.asList(arg, arg1));
	}

	public void add(Executeable<T> arg) {
		executeableList.add(arg);
	}

	@Override
	public Iterator<Executeable<T>> iterator() {
		return executeableList.iterator();
	}

	@Override
	public Integer execute(Integer x) {

		Integer wynik = x.intValue();

		for (Executeable<T> e : executeableList)
			wynik = e.execute(wynik);

		return wynik;
	}

}
