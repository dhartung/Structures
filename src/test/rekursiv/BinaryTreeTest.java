package test.rekursiv;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import rekursiv.BinaryTree;

public class BinaryTreeTest {

	protected BinaryTree<Integer> intTree;
	protected java.util.Comparator<Integer> intComparator = Comparator.naturalOrder();
	
	protected Integer[] entrys;
	
	@Before
	public void init() {
		intTree = new BinaryTree<>(intComparator);
		
		entrys = new Integer[] {10,5,8,7,54,7,87,54,8,2,154,8,54,45,21,849,4566,45,6,56,8,456,21,846,45,6};
		
		for (int i = 0; i < entrys.length; i++)
			intTree.add(entrys[i]);
		
		
	}
	
	
	@Test(timeout=500)
	public void testAdd() {
	
		intTree.add(425487);
		
		assertTrue(intTree.contains(425487));		
	}

	@Test(timeout=500)
	public void testRemoveAll() {
		
		intTree.removeAll(54);
		
		
		
		assertFalse(intTree.contains(54));
		
		assertTrue(intTree.contains(56));
		assertTrue(intTree.contains(21));
	}
	
	@Test(timeout=500)
	public void testRemoveAllComplex() {
		
		intTree.removeAll(10);		
		intTree.removeAll(846);
		intTree.removeAll(45);		
		intTree.removeAll(54);
		intTree.removeAll(21);
		
		assertArrayEquals(new Integer[] {2, 5, 6, 6, 7, 7, 8, 8, 8, 8, 56, 87, 154, 456, 849, 4566},
						  intTree.toArray());
	}

	@Test(timeout=500)
	public void testContains() {
		assertTrue(intTree.contains(4566));
		assertTrue(intTree.contains(10));
		assertTrue(intTree.contains(45));
		
		assertFalse(intTree.contains(92));
	}

	
	@Test(timeout=500)
	public void testToArray() {
		Arrays.sort(entrys);
		
		assertArrayEquals(entrys, intTree.toArray());
	}

}
