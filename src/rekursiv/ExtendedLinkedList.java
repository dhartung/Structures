package rekursiv;

import java.util.Comparator;

import iterativ.LinkedList;
import structures.IExtendedLinearOperations;

public class ExtendedLinkedList<T> extends LinkedList<T> implements IExtendedLinearOperations<T> {

	
	private void removeDuplicates(ListItem<T> current) {
		if (current == null)
			return;
		
		// Remove all Items containing the current key
		findAndRemoveNext(current.key, current);
		
		removeDuplicates(current.next);		
	}
	
	private void findAndRemoveNext(T item, ListItem<T> current) {
		if (current.next == null)
			return;
		
		if (current.next.key.equals(item)) {
			current.next = current.next.next;
			
			if (current.next != null)
				current.next.back = current;
			
			count--;
			
			findAndRemoveNext(item, current);
		} else {
			findAndRemoveNext(item, current.next);
		}		
	}
	
	@Override
	public void removeDuplicates() {
		if (firstItem == null)
			return;
		
		removeDuplicates(firstItem);
	}

	
	private T get2ndMax(Comparator<T> cmp, T max1, T max2, ListItem<T> current) {
		if (current == null)
			return max2;
	
		
		if (cmp.compare(current.key, max2) > 0) {
			max2 = current.key;
			
			if (cmp.compare(max2, max1) > 0) {
				T tmp = max1;
				max1 = max2;
				max2 = tmp;
			}
		}
		
		return get2ndMax(cmp, max1, max2, current.next);
	}
	
	@Override
	public T get2ndMax(Comparator<T> cmp) {
		if (firstItem == null)
			return null;
		
		return get2ndMax(cmp, firstItem.key, firstItem.key, firstItem);
	}

	
	private boolean isAscending(Comparator<T> cmp, ListItem<T> current) {
		if (current.next == null)
			return true;
		
		// Next element < current element
		if (cmp.compare(current.next.key, current.key) < 0)
			return false;
		
		return isAscending(cmp, current.next);
	}
	
	@Override
	public boolean isAscending(Comparator<T> cmp) {
		if (firstItem == null)
			return true;
		
		return isAscending(cmp, firstItem);		
	}

	
	private void exchangePair(ListItem<T> current) {
		if (current.next == null)
			return;
		
		T tmp = current.key;
		current.key = current.next.key;
		current.next.key = tmp;
		
		exchangePair(current.next.next);
	}
	
	
	@Override
	public void exchangePair() {
		if (firstItem == null)
			return;
		
		exchangePair(firstItem);		
	}

	
	private void rotateAll(int index, int max, ListItem<T> frontLine, ListItem<T> backLine) {
		if (index >= max)
			return;
		
		T tmp = frontLine.key;
		frontLine.key = backLine.key;
		backLine.key = tmp;
		
		rotateAll(index + 1, max, frontLine.next, backLine.back);
	}
	
	private ListItem<T> getBackLine(ListItem<T> current) {
		if (current.next == null)
			return current;
		
		return getBackLine(current.next);
	}
	
	
	@Override
	public void rotateAll() {
		if (firstItem == null)
			return;
		
		rotateAll(0, count/2, firstItem, getBackLine(firstItem));
	}

}
