package test.iterativ;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import iterativ.LinkedList;

public class LinkedListIterTest {

	public LinkedList<String> list;
	
	@Before
	public void init() {
		list = new LinkedList<>();
		
		list.add("Lorem");	// 0
		list.add("Ipsum");	// 1
		list.add("Dolor");	// 2
		list.add("Sit");	// 3
		list.add("Amet");	// 4
		list.add("Ipsum");	// 5
	}
	
	@Test
	public void testGetCount() {
		assertEquals(6, list.getCount());
	}
	
	@Test
	public void testGetItem() {
		assertEquals("Ipsum", list.getItem(1));
		assertEquals("Sit", list.getItem(3));
	}

	@Test
	public void testSearchIndex() {
		assertEquals(2, list.searchIndex("Dolor"));
	}

	@Test
	public void testReplaceAll() {
		list.replaceAll("Ipsum", "42");
		
		assertEquals("42", list.getItem(1));
		assertEquals("42", list.getItem(5));
	}

	@Test
	public void testRemoveAt() {
		list.removeAt(4);
		assertEquals("Ipsum", list.getItem(4));
	}
	
	@Test
	public void testRemoveAll() {
		list.removeAll("Ipsum");
		assertEquals("Dolor", list.getItem(1));
		assertEquals(4, list.getCount());
	}
	
	@Test
	public void testContains() {
		assertTrue(list.contains("Sit"));
		assertFalse(list.contains("Hallo"));
	}
	
	@Test
	public void testToArray() {
		assertArrayEquals(new String[] {"Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Ipsum"}, list.toArray());
	}

}
