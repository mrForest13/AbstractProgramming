package zadaniepiate.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import zadaniepiate.SortedDeque;

public class SortedDequeTest {

	private int CeilOfDivision(int divident, int divisor) {
		return (int) Math.ceil(divident / divisor);
	}

	@Test
	public void testConstructionOfEmptyContainer() {
		SortedDeque<Integer> sortedDeque = new SortedDeque<>();
		Assert.assertTrue(sortedDeque.empty());
		Assert.assertEquals(0, sortedDeque.getSizeTotal());
		Assert.assertEquals(0, sortedDeque.getSizeUnique());
		Assert.assertEquals(0, sortedDeque.getCapacity());
	}

	@Test
	public void testReserve() {
		final int SIZE_OF_BUCKET = 3;
		SortedDeque<Integer> sortedDeque = new SortedDeque<>(SIZE_OF_BUCKET);

		final int capacityWhichIWant = SIZE_OF_BUCKET * 10;
		sortedDeque.reserve(capacityWhichIWant);

		final int expectedNumberOfBuckets = CeilOfDivision(capacityWhichIWant, SIZE_OF_BUCKET);
		Assert.assertEquals(expectedNumberOfBuckets, sortedDeque.getNumberOfBucket());

		final int expectedCapacity = expectedNumberOfBuckets * SIZE_OF_BUCKET;
		Assert.assertEquals(expectedCapacity, sortedDeque.getCapacity());

		Assert.assertTrue(sortedDeque.empty());
		Assert.assertEquals(0, sortedDeque.getSizeTotal());
		Assert.assertEquals(0, sortedDeque.getSizeUnique());
	}

	@Test
	public void testReserveWithSmallerNewCapacity() {
		final int SIZE_OF_BUCKET = 3;
		SortedDeque<Integer> sortedDeque = new SortedDeque<>(SIZE_OF_BUCKET);

		final int capacityWhichIWant = SIZE_OF_BUCKET * 10;
		sortedDeque.reserve(capacityWhichIWant);

		final int capacityWhichIWantNew = SIZE_OF_BUCKET * 5;
		sortedDeque.reserve(capacityWhichIWantNew);

		Assert.assertEquals(capacityWhichIWant / SIZE_OF_BUCKET, sortedDeque.getNumberOfBucket());
		Assert.assertEquals(capacityWhichIWant, sortedDeque.getCapacity());

		Assert.assertTrue(sortedDeque.empty());
		Assert.assertEquals(0, sortedDeque.getSizeTotal());
		Assert.assertEquals(0, sortedDeque.getSizeUnique());
	}

	@Test
	public void testSimpleAddingIntoSingleBucket() {
		int[] valuesToAdd = { 2, 0, 9, 1 };

		final int SIZE_OF_BUCKET = 10;
		SortedDeque<Integer> sortedDeque = new SortedDeque<>(SIZE_OF_BUCKET);
		for (int i = 0; i < valuesToAdd.length; i++)
			sortedDeque.insert(valuesToAdd[i]);

		Arrays.sort(valuesToAdd);

		for (int i = 0; i < valuesToAdd.length; i++)
			Assert.assertEquals(valuesToAdd[i], (int) sortedDeque.get(i));

		Assert.assertFalse(sortedDeque.empty());
		Assert.assertEquals(valuesToAdd.length, sortedDeque.getSizeTotal());
		Assert.assertEquals(valuesToAdd.length, sortedDeque.getSizeUnique());

		Assert.assertEquals(SIZE_OF_BUCKET, sortedDeque.getCapacity());
		Assert.assertEquals(1, sortedDeque.getNumberOfBucket());
	}

	@Test
	public void testAddingSmallestValueWhenFirstBucketIsFull() {
		final int SIZE_OF_BUCKET = 3;
		SortedDeque<Integer> sortedDeque = new SortedDeque<>(SIZE_OF_BUCKET);

		int[] valuesToAdd = { 2, 1, 9 };

		for (int i = 0; i < valuesToAdd.length; i++)
			sortedDeque.insert(valuesToAdd[i]);

		Arrays.sort(valuesToAdd);

		for (int i = 0; i < valuesToAdd.length; i++)
			Assert.assertEquals(valuesToAdd[i], (int) sortedDeque.get(i));

		Assert.assertFalse(sortedDeque.empty());
		Assert.assertEquals(valuesToAdd.length, sortedDeque.getSizeTotal());
		Assert.assertEquals(valuesToAdd.length, sortedDeque.getSizeUnique());

		Assert.assertEquals(SIZE_OF_BUCKET, sortedDeque.getCapacity());
		Assert.assertEquals(1, sortedDeque.getNumberOfBucket());

		//////////////////////////////////////////////////////////////////////
		final int valueSmallerThanPreviousValuesToAdd = 0;
		sortedDeque.insert(valueSmallerThanPreviousValuesToAdd);
		Assert.assertEquals(valueSmallerThanPreviousValuesToAdd, (int) sortedDeque.get(0));

		Assert.assertFalse(sortedDeque.empty());
		Assert.assertEquals(valuesToAdd.length + 1, sortedDeque.getSizeTotal());
		Assert.assertEquals(valuesToAdd.length + 1, sortedDeque.getSizeUnique());

		Assert.assertEquals(2 * SIZE_OF_BUCKET, sortedDeque.getCapacity());
		Assert.assertEquals(2, sortedDeque.getNumberOfBucket());
	}

	@Test
	public void testAddingGreaterValueWhenFirstBucketIsFull() {
		final int SIZE_OF_BUCKET = 3;
		SortedDeque<Integer> sortedDeque = new SortedDeque<>(SIZE_OF_BUCKET);

		int[] valuesToAdd = { 2, 1, 9 };

		for (int i = 0; i < valuesToAdd.length; i++)
			sortedDeque.insert(valuesToAdd[i]);

		Arrays.sort(valuesToAdd);

		for (int i = 0; i < valuesToAdd.length; i++)
			Assert.assertEquals(valuesToAdd[i], (int) sortedDeque.get(i));

		Assert.assertFalse(sortedDeque.empty());
		Assert.assertEquals(valuesToAdd.length, sortedDeque.getSizeTotal());
		Assert.assertEquals(valuesToAdd.length, sortedDeque.getSizeUnique());

		Assert.assertEquals(SIZE_OF_BUCKET, sortedDeque.getCapacity());
		Assert.assertEquals(1, sortedDeque.getNumberOfBucket());

		//////////////////////////////////////////////////////////////////////
		final int valueSmallerThanPreviousValuesToAdd = 100;
		sortedDeque.insert(valueSmallerThanPreviousValuesToAdd);
		Assert.assertTrue(sortedDeque.back() == valueSmallerThanPreviousValuesToAdd);

		Assert.assertFalse(sortedDeque.empty());
		Assert.assertEquals(valuesToAdd.length + 1, sortedDeque.getSizeTotal());
		Assert.assertEquals(valuesToAdd.length + 1, sortedDeque.getSizeUnique());

		Assert.assertEquals(2 * SIZE_OF_BUCKET, sortedDeque.getCapacity());
		Assert.assertEquals(2, sortedDeque.getNumberOfBucket());
	}

	@Test
	public void testAddingValueWhichShouldBeAddedBetweenTwoFullBuckets() {
		final int SIZE_OF_BUCKET = 3;
		SortedDeque<Integer> sortedDeque = new SortedDeque<>(SIZE_OF_BUCKET);

		int[] valuesToAddToFirstBucket = { 1, 2, 3 };

		for (int i = 0; i < valuesToAddToFirstBucket.length; i++)
			sortedDeque.insert(valuesToAddToFirstBucket[i]);

		int[] valuesToAddToSecondBucket = { 5, 6, 7 };

		for (int i = 0; i < valuesToAddToSecondBucket.length; i++)
			sortedDeque.insert(valuesToAddToSecondBucket[i]);

		int valueWhichShouldBeAddedBetweenTwoFullBuckets = 4;
		sortedDeque.insert(valueWhichShouldBeAddedBetweenTwoFullBuckets);

		final int expectedTotalNumberOfElements = valuesToAddToFirstBucket.length + 1
				+ valuesToAddToSecondBucket.length;
		Assert.assertEquals(expectedTotalNumberOfElements, sortedDeque.getSizeTotal());
		Assert.assertEquals(expectedTotalNumberOfElements, sortedDeque.getSizeUnique());

		Assert.assertEquals(3 * SIZE_OF_BUCKET, sortedDeque.getCapacity());
		Assert.assertEquals(3, sortedDeque.getNumberOfBucket());
	}

	@Test
	public void testAddingValueWhichShouldBeAddedBetweenTwoValuesInFullBucketAfterWhichIsAnotherFullBucket() {
		final int SIZE_OF_BUCKET = 3;
		SortedDeque<Double> sortedDeque = new SortedDeque<>(SIZE_OF_BUCKET);

		List<Double> valuesToAddToFirstBucket = new ArrayList<>(Arrays.asList(1., 2., 3.));

		for (int i = 0; i < valuesToAddToFirstBucket.size(); i++)
			sortedDeque.insert(valuesToAddToFirstBucket.get(i));

		List<Double> valuesToAddToSecondBucket = new ArrayList<>(Arrays.asList(5., 6., 7.));

		for (int i = 0; i < valuesToAddToSecondBucket.size(); i++)
			sortedDeque.insert(valuesToAddToSecondBucket.get(i));

		double valueWhichShouldBeAddedBetweenTwoFullBuckets = 2.78;
		sortedDeque.insert(valueWhichShouldBeAddedBetweenTwoFullBuckets);

		/////////////////////////////////////////////////////////////////////

		List<Double> allValue = new ArrayList<>();
		allValue.addAll(valuesToAddToFirstBucket);
		allValue.addAll(valuesToAddToSecondBucket);
		allValue.add(valueWhichShouldBeAddedBetweenTwoFullBuckets);

		Collections.sort(allValue);

		for (int i = 0; i < allValue.size(); i++)
			Assert.assertEquals(allValue.get(i), sortedDeque.get(i));

		/////////////////////////////////////////////////////////////////////

		final int expectedTotalNumberOfElements = valuesToAddToFirstBucket.size() + 1
				+ valuesToAddToSecondBucket.size();
		Assert.assertEquals(expectedTotalNumberOfElements, sortedDeque.getSizeTotal());
		Assert.assertEquals(expectedTotalNumberOfElements, sortedDeque.getSizeUnique());

		Assert.assertEquals(3 * SIZE_OF_BUCKET, sortedDeque.getCapacity());
		Assert.assertEquals(3, sortedDeque.getNumberOfBucket());
	}

	@Test
	public void testAddingDuplicatedElements() {
		SortedDeque<Integer> sortedDeque = new SortedDeque<>();

		List<Integer> valuesToAdd = new ArrayList<>(Arrays.asList(2, 1, 9, 1, 5, 2, 9));

		for (int i = 0; i < valuesToAdd.size(); i++)
			sortedDeque.insert(valuesToAdd.get(i));

		Collections.sort(valuesToAdd);

		Set<Integer> uniqueValues = new HashSet<Integer>(valuesToAdd);

		int i = 0;
		for (Integer e : uniqueValues)
			Assert.assertEquals(e, sortedDeque.get(i++));

		Assert.assertFalse(sortedDeque.empty());
		Assert.assertEquals(valuesToAdd.size(), sortedDeque.getSizeTotal());
		Assert.assertEquals(uniqueValues.size(), sortedDeque.getSizeUnique());
	}

	@Test
	public void testIterator() {
		SortedDeque<Float> sortedDeque = new SortedDeque<>();

		List<Float> valuesToAdd = new ArrayList<Float>(Arrays.asList(1.11f, 2.22f, 3.33f, 4.44f, 5.55f));

		for (int i = 0; i < valuesToAdd.size(); i++)
			sortedDeque.insert(valuesToAdd.get(i));
		
		Collections.sort(valuesToAdd);
		
		int i = 0;
		for(Float f : sortedDeque) {
			Assert.assertEquals(f, valuesToAdd.get(i++));
		}
	}
}
