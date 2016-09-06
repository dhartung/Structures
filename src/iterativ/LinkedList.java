package iterativ;

import structures.AbstractLinkedList;

public class LinkedList<T> extends AbstractLinkedList<T> {

	@Override
	public T getItem(int index) {
		ListItem<T> current = firstItem;
		
		if (index >= count)
			throw new IndexOutOfBoundsException();
		
		for (int i = 0; i < index; i++)
			current = current.next;
		
		return current.key;
	}

	@Override
	public int searchIndex(T needle) {
		ListItem<T> current = firstItem;
		int i = 0;
		
		while (current != null) {
			if (current.key.equals(needle))
				return i;
			
			i++;
			current = current.next;
		}
		
		// Not found
		return -1;
	}

	@Override
	public void add(T item) {
		ListItem<T> lItem = new ListItem<T>();
		
		lItem.next = null;
		lItem.key = item;
		
		count++;
		
		// List is empty
		if (firstItem == null) {
			lItem.back = null;
			firstItem = lItem;
			
			return;
		}
		
		ListItem<T> current = firstItem;
		
		while (current.next != null)
			current = current.next;
		
		lItem.back = current;
		current.next = lItem;		
	}

	@Override
	public boolean replaceAll(T search, T replace) {
		ListItem<T> current = firstItem;
		boolean found = false;
		
		while (current != null) {
			if (current.key.equals(search)) {
				current.key = replace;
				found = true;
			}
				
			current = current.next;
		}
		
		return found;
	}

	@Override
	public void removeAt(int index) {
		ListItem<T> current = firstItem;
		
		if (index >= count)
			throw new IndexOutOfBoundsException();
		
		count--;
		
		if (index == 0) {
			firstItem = firstItem.next;
			return;
		}
			
		
		for (int i = 0; i < index - 1; i++)
			current = current.next;
		
		current.next = current.next.next;	
		current.next.back = current;
	}
	
	@Override
	public void removeAll(T item) {
		ListItem<T> current = firstItem;

		if (current == null)
			return;
		
		if (current.key.equals(item)) {
			firstItem = current.next;
			firstItem.back = null;
			count--;
		}
				
		while (current.next != null) {
			if (current.next.key.equals(item)) {
				current.next = current.next.next;
				
				if (current.next != null)
					current.next.back = current;
				
				count--;
			} else {
				current = current.next;
			}
		}
	}

	@Override
	public boolean contains(T item) {
		ListItem<T> current = firstItem;
		
		while (current != null)
			if (current.key.equals(item))
				return true;
			else
				current = current.next;
		
		return false;
	}
	
	
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) new Object[count];
		
		ListItem<T> current = firstItem;
		
		int index = 0;
		
		while (current != null) {
			arr[index] = current.key;
			current = current.next;
			index++;
		}

		
		return arr;
	}

}
