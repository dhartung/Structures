package rekursiv;

import structures.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

	
	private T getItem(int index, ListItem<T> current) {
		if (current == null)
			throw new IndexOutOfBoundsException();
		
		if (index == 0)
			return current.key;
		
		return getItem(index - 1, current.next);
	}
		
	
	@Override
	public T getItem(int index) {
		try {
			return getItem(index, firstItem);			
		} catch (Exception e) {
			throw e;
		}
	}

	
	
	private int searchIndex(T needle, int index, ListItem<T> current) {
		if (current == null)
			return -1;
		
		if (current.key.equals(needle))
			return index;
		
		return searchIndex(needle, index + 1, current.next);
	}
	
	@Override
	public int searchIndex(T needle) {
		return searchIndex(needle, 0, firstItem);
	}

	
	private void add(ListItem<T> item, ListItem<T> current) {
		if (current.next == null) {
			item.back = current;
			current.next = item;
			
			return;
		}
		
		add(item, current.next);
	}
	
	@Override
	public void add(T item) {
		ListItem<T> lItem = new ListItem<>();
		lItem.key = item;
		lItem.next = null;
		
		if (firstItem == null) {
			lItem.back = null;
			firstItem = lItem;
		} else {
			/*
			 * If lastItem is used:
			 * 		lastItem.next = lItem;
			 * else 
			 */
			 
			 add(lItem, firstItem);

		}	
		
		count++;
	}

	
	// var "found" nur noetig bei replaceAll
	private boolean replaceAll(T search, T replace, boolean found, ListItem<T> currentItem) {
		if (currentItem == null)
			return found;
		
		if (currentItem.key.equals(search)) {
			currentItem.key = replace;
			found = true;
		}
		
		return replaceAll(search, replace, found, currentItem.next);
	}
	
	@Override
	public boolean replaceAll(T search, T replace) {
		return replaceAll(search, replace, false, firstItem);
	}

	
	
	private void removeAt(int index, ListItem<T> current) {
		if (current == null)
			throw new IndexOutOfBoundsException();
		
		if (index == 0) {
			current.next = current.next.next;
			current.next.back = current;
		} else {
			removeAt(index - 1, current.next);
		}		
	}
	
	@Override
	public void removeAt(int index) {
		if (index == 0) {
			firstItem = firstItem.next;
		} else {
			removeAt(index - 1, firstItem);
		}
		
		count--;
	}

	
	
	private void removeAll(T item, ListItem<T> current) {
		if (current.next == null)
			return;
		
		if (current.next.key.equals(item)) {
			current.next = current.next.next;
			
			if (current.next != null)
				current.next.back = current;
			
			count--;
			removeAll(item, current);
		} else {
			removeAll(item, current.next);
		}
	}

	@Override
	public void removeAll(T item) {
		if (firstItem == null)
			return;
		
		if (firstItem.key.equals(item)) {
			firstItem = firstItem.next;
			firstItem.back = null;
		}
		
		removeAll(item, firstItem);
	}

	
	private boolean contains(T item, ListItem<T> current) {
		if (current == null)
			return false;
		
		if (current.key.equals(item))
			return true;
		
		return contains(item, current.next);
	}

	@Override
	public boolean contains(T item) {
		return contains(item, firstItem);
	}

	
	
	private void toArray(ListItem<T> current, int index, T[] output) {
		if (current == null)
			return;
		
		output[index] = current.key;
		
		toArray(current.next, index + 1, output);
	}
	
	
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) new Object[count];
		
		toArray(firstItem, 0, arr);
		
		return arr;
	}
	
}
