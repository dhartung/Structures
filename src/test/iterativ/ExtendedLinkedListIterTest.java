package test.iterativ;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import iterativ.ExtendedLinkedList;

public class ExtendedLinkedListIterTest {
	ExtendedLinkedList<Integer> list;
	java.util.Comparator<Integer> comparator = Comparator.naturalOrder();

	@Before
	public void init() {
		list = new ExtendedLinkedList<>();
		
		list.add(0);
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(5);
		
		
	}
	

	@Test
	public void testRemoveDuplicates() {
		list.removeDuplicates();
		
		assertEquals(1, (int)list.getItem(1));
		assertEquals(5, (int)list.getItem(4));
	}

	@Test
	public void testGet2ndMax() {
		assertEquals(3, (int)list.get2ndMax(comparator));
	}

	@Test
	public void testIsAscending() {
		assertTrue(list.isAscending(comparator));
		list.add(2);
		assertFalse(list.isAscending(comparator));
	}

	@Test
	public void testExchangePair() {
		list.exchangePair();
		
		assertEquals(0, (int)list.getItem(0));
		assertEquals(0, (int)list.getItem(1));
		assertEquals(2, (int)list.getItem(2));
		assertEquals(1, (int)list.getItem(3));
		assertEquals(3, (int)list.getItem(4));
		assertEquals(3, (int)list.getItem(5));
		assertEquals(5, (int)list.getItem(6));
	}

	@Test
	public void testRotateAll() {
		list.rotateAll();
		
		assertEquals(5, (int)list.getItem(0));
		assertEquals(3, (int)list.getItem(1));
		assertEquals(3, (int)list.getItem(2));
		assertEquals(2, (int)list.getItem(3));
		assertEquals(1, (int)list.getItem(4));
		assertEquals(0, (int)list.getItem(5));
		assertEquals(0, (int)list.getItem(6));
	}

	@Test
	public void testRotateAllwithBack() {
		list.rotateAllwithBack();
		
		assertEquals(5, (int)list.getItem(0));
		assertEquals(3, (int)list.getItem(1));
		assertEquals(3, (int)list.getItem(2));
		assertEquals(2, (int)list.getItem(3));
		assertEquals(1, (int)list.getItem(4));
		assertEquals(0, (int)list.getItem(5));
		assertEquals(0, (int)list.getItem(6));
	}

}
