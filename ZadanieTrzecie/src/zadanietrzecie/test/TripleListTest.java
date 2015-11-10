package zadanietrzecie.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import zadanietrzecie.TripleList;

public class TripleListTest {

	@Test
	public void TestEmptyListCreation() {

		TripleList<Integer> tripleList = new TripleList<Integer>();
		assertEquals(0, tripleList.count());

		assertNull(tripleList.getPreviousElement());
		assertNull(tripleList.getMiddleElement());
		assertNull(tripleList.getValue());
	}

	@Test
	public void TestAddingSingleElement() {

		TripleList<Integer> tripleList = new TripleList<Integer>();
		final Integer value = 4;
		tripleList.add(value);
		assertEquals(1, tripleList.count());
		assertEquals(value, tripleList.getValue());

		assertNull(tripleList.getPreviousElement());
		assertNull(tripleList.getMiddleElement());
		assertNull(tripleList.getNextElement());
	}

	@Test
	public void TestAddingTwoElements() {

		TripleList<Integer> tripleList = new TripleList<Integer>();
		Integer value1 = 4;
		Integer value2 = -9;
		tripleList.add(value1);
		tripleList.add(value2);
		assertEquals(2, tripleList.count());
		// checking values
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.getMiddleElement().getValue());
		assertEquals(tripleList.getValue(), tripleList.getMiddleElement()
				.getMiddleElement().getValue());
		// checking neighbour Nodes of first element
		assertNull(tripleList.getPreviousElement());
		assertNotNull(tripleList.getMiddleElement());
		assertNull(tripleList.getNextElement());
		// checking neighbour Nodes of second element
		assertNull(tripleList.getMiddleElement().getPreviousElement());
		assertNull(tripleList.getMiddleElement().getNextElement());
	}

	@Test
	public void TestAddingTreeElements() {

		TripleList<Integer> tripleList = new TripleList<Integer>();
		Integer value1 = 4;
		Integer value2 = -9;
		Integer value3 = 47;
		tripleList.add(value1);
		tripleList.add(value2);
		tripleList.add(value3);
		assertEquals(3, tripleList.count());
		// checking values
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.getMiddleElement().getValue());
		assertEquals(value3, tripleList.getNextElement().getValue());
		// checking neighbour Nodes of first element
		assertNull(tripleList.getPreviousElement());
		assertNotNull(tripleList.getMiddleElement());
		assertNotNull(tripleList.getNextElement());
		// checking neighbour Nodes of second element
		assertNull(tripleList.getMiddleElement().getPreviousElement());
		assertNotNull(tripleList.getMiddleElement().getMiddleElement());
		assertNull(tripleList.getMiddleElement().getNextElement());
		// checking neighbour Nodes of third/last element
		assertNotNull(tripleList.getNextElement().getPreviousElement());
		assertNull(tripleList.getNextElement().getMiddleElement());
		assertNull(tripleList.getNextElement().getNextElement());
		// checking values
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.getMiddleElement().getValue());
		assertEquals(value3, tripleList.getNextElement().getValue());

	}

	@Test
	public void TestAddingFiveElements() {

		TripleList<Integer> tripleList = new TripleList<Integer>();
		Integer value1 = 1;
		Integer value2 = 2;
		Integer value3 = 3;
		Integer value4 = 4;
		Integer value5 = 5;
		tripleList.add(value1);
		tripleList.add(value2);
		tripleList.add(value3);
		tripleList.add(value4);
		tripleList.add(value5);
		assertEquals(5, tripleList.count());
		// checking values
		assertEquals(value1, tripleList.getValue());
		assertEquals(value2, tripleList.getMiddleElement().getValue());
		assertEquals(value3, tripleList.getNextElement().getValue());
		assertEquals(value4, tripleList.getNextElement().getMiddleElement()
				.getValue());
		assertEquals(value5, tripleList.getNextElement().getNextElement()
				.getValue());
	}

	@Test
	public void TestListsEnumerator() // Bezsensowny test.
	{
		Double[] values = { 1.1, 3.14, 6.13, 9.99999, 99.001 };
		TripleList<Double> tripleList = new TripleList<Double>();
		int i;
		for (i = 0; i < values.length; ++i) {
			tripleList.add(values[i]);
		}
		i = 0;
		for (Double d : values) {
			assertEquals(values[i++], d);
		}
	}

	@Test
	public void TestListsEnumerator2() // W javie iterface Iterable
	{
		Double[] values = { 1.1, 3.14, 6.13, 9.99999, 99.001 };
		TripleList<Double> tripleList = new TripleList<Double>();
		int i;
		for (i = 0; i < values.length; ++i) {
			tripleList.add(values[i]);
		}
		i = 0;
		Iterator<Double> it = tripleList.iterator();
		while (it.hasNext()) {
			assertEquals(values[i++], it.next());
		}
	}

	@Test
	public void TestIfNoCycle() {
		/** Initialization of the TripleList **/
		final int NUMBER_OF_ELEMENTS = 100;
		TripleList<Integer> tripleList = new TripleList<Integer>();
		for (int i = 0; i < NUMBER_OF_ELEMENTS; ++i) {
			tripleList.add(i);
		}
		/**
		 * Created 2 TripleLists, first jumps every single element, another
		 * every two elements, in out case every two elements means every
		 * NextElement
		 **/
		TripleList<Integer> tripleListEverySingleNode = tripleList;
		TripleList<Integer> tripleListEveryTwoNodes = tripleList
				.getNextElement();
		for (int i = 0; i < NUMBER_OF_ELEMENTS * NUMBER_OF_ELEMENTS; ++i) {
			assertNotSame(tripleListEverySingleNode, tripleListEveryTwoNodes);
			JumpToNextElement(tripleListEverySingleNode);
			if (null == tripleListEveryTwoNodes.getNextElement()) {
				// if list has end means there are no cycles
				break;
			} else {
				tripleListEveryTwoNodes = tripleListEveryTwoNodes
						.getNextElement();
			}
		}
	}
	
	
	@Test
    public void ArrayInitializers()
    {
        final TripleList<Integer> tl1 = new TripleList<Integer>() {{add(5);add(10);add(15);}};
        assertEquals(3, tl1.count());
        TripleList<Integer> tl2 = new TripleList<Integer>() {{add(0);addAll(tl1);add(20);}};
        assertEquals(3, tl1.count());
        assertEquals(5, tl2.count());
        assertEquals(tl1.getValue(), tl2.getMiddleElement().getValue());
        List<Integer> tl1Sorted = tl1.toList();
        Collections.sort(tl1Sorted);
        List<Integer> tl2Sorted = tl2.toList();
        Collections.sort(tl2Sorted);

    }
	

	private void JumpToNextElement(TripleList<Integer> element) {
		if (IsNotLastElement(element)) {
			if (IsMiddleElement(element)) {
				if (null != element.getMiddleElement().getNextElement()) {
					element = element.getMiddleElement().getNextElement();
				}
			} else {
				if (null != element.getNextElement()) {
					element = element.getNextElement();
				}
			}
		}
	}

	private boolean IsNotLastElement(TripleList<Integer> element) {
		return null != element.getMiddleElement();
	}

	private boolean IsMiddleElement(TripleList<Integer> element) {
		return null == element.getNextElement()
				&& null == element.getPreviousElement()
				&& null != element.getMiddleElement();
	}
}
