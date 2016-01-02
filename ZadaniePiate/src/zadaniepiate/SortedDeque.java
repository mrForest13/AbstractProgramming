package zadaniepiate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SortedDeque<T extends Comparable<T>> implements Iterable<T> {

	private int sizeOfBucket;
	private int numberOfBucket;
	private int sizeUnique;
	private int sizeTotal;
	private int capacity;

	List<Element<T>[]> elementList = new ArrayList<Element<T>[]>();

	public int getNumberOfBucket() {
		return numberOfBucket;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getSizeTotal() {
		return sizeTotal;
	}

	public int getSizeUnique() {
		return sizeUnique;
	}

	public SortedDeque() {
		this.sizeOfBucket = 10;
	}

	public SortedDeque(int sizeOfBucket) {
		this.sizeOfBucket = sizeOfBucket;
	}

	public boolean empty() {
		return sizeUnique != 0 ? false : true;
	}

	public boolean reserve(int capacityWhichIWant) {
		if (capacityWhichIWant > capacity) {
			capacity = capacityWhichIWant - (capacityWhichIWant % sizeOfBucket);
			numberOfBucket = (int) Math.ceil(capacity / sizeOfBucket);
			return true;
		} else
			return false;
	}

	public void checkFreeBucket() {
		if (numberOfBucket == 0 || sizeUnique % (numberOfBucket * sizeOfBucket) == 0) {
			elementList.add(getArray(Element.class, sizeOfBucket));
			numberOfBucket++;
			capacity += sizeOfBucket;
		}
	}

	public <E> Element<T>[] getArray(Class<E> clazz, int size) {
		@SuppressWarnings("unchecked")
		Element<T>[] arr = (Element<T>[]) Array.newInstance(clazz, size);

		return arr;
	}

	public void insert(T i) {
		checkFreeBucket();
		Element<T> element = new Element<T>(i);
		int index[] = getIndex(element);
		if (index[0] == -1)
			return;

		moveAndAdd(element, index[0], index[1]);
	}

	public void moveAndAdd(Element<T> e, int index, int bucketNumber) {

		int actualIndex = (sizeUnique - 1) % sizeOfBucket;
		int actualBucket = (int) Math.ceil((sizeUnique - 1) / sizeOfBucket);

		for (int i = sizeUnique - 1; i >= index; i--) {

			Element<T> temp = elementList.get(actualBucket)[actualIndex];

			if (actualIndex + 1 == sizeOfBucket) {
				elementList.get(actualBucket + 1)[0] = temp;
			} else {
				elementList.get(actualBucket)[actualIndex + 1] = temp;
			}

			if (actualIndex == 0) {
				actualIndex = sizeOfBucket - 1;
				actualBucket--;
			} else
				actualIndex--;

		}

		elementList.get(bucketNumber)[index] = e;
		sizeUnique++;
		sizeTotal++;

	}

	public int[] getIndex(Element<T> e) {

		int numberOfBucket = 0;
		int index = 0;

		while (true) {

			if (sizeUnique == 0 || elementList.get(numberOfBucket)[index] == null) {
				elementList.get(numberOfBucket)[index] = e;
				index = -1;
				numberOfBucket = -1;
				sizeUnique++;
				sizeTotal++;
				break;
			}

			if (elementList.get(numberOfBucket)[index].compareTo(e) == 1)
				break;

			if (elementList.get(numberOfBucket)[index].compareTo(e) == 0) {
				elementList.get(numberOfBucket)[index].countAdd();
				index = -1;
				numberOfBucket = -1;
				sizeTotal++;
				break;
			}

			if (index == sizeOfBucket - 1) {
				index = 0;
				numberOfBucket++;
			} else
				index++;
		}

		return new int[] { index, numberOfBucket };

	}

	public T get(int i) {
		return (elementList.get((int) Math.ceil(i / sizeOfBucket))[i % sizeOfBucket]).getValue();
	}

	public T back() {
		return get(sizeUnique - 1);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			int index = 0;

			@Override
			public boolean hasNext() {
				return index < sizeUnique ? true : false;
			}

			@Override
			public T next() {
				return get(index++);
			}
		};
	}
}
