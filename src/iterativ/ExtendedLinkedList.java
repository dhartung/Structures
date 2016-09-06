package iterativ;

import java.util.Comparator;

import structures.IExtendedLinearOperations;

public class ExtendedLinkedList<T> extends LinkedList<T> implements IExtendedLinearOperations<T>{

	private void removeAfter(T key, ListItem<T> lItem) {
		ListItem<T> lastlItem = lItem;
		lItem = lItem.next;
		
		while (lItem != null) {
			if (lItem.key.equals(key)) {
				lastlItem.next = lItem.next;
				count--;
			} else
				lastlItem = lItem;
					
			lItem = lItem.next;
		}
		
	}
	
	@Override
	public void removeDuplicates() {
		ListItem<T> lItem = firstItem;
		
		while (lItem != null) {
			removeAfter(lItem.key, lItem);
			
			lItem = lItem.next;
		}	
	}

	@Override
	public T get2ndMax(Comparator<T> cmp) {
		if (firstItem == null)
			return null;
		
		T max1 = firstItem.key;
		T max2 = firstItem.key;
		
		
		ListItem<T> lItem = firstItem;
		
		while (lItem != null) {
			
			if (cmp.compare(lItem.key, max2) > 0) {
				if (cmp.compare(lItem.key, max1) > 0) {
					max2 = max1;
					max1 = lItem.key;
				} else {
					max2 = lItem.key;
				}					
			}

			lItem = lItem.next;
		}	
		
		return max2;
	}

	@Override
	public boolean isAscending(Comparator<T> cmp) {
		ListItem<T> lItem = firstItem;
		
		if (lItem == null)
			return true;
		
		while (lItem.next != null) {
			if (cmp.compare(lItem.next.key, lItem.key) < 0) {
				return false;	
			}

			lItem = lItem.next;
		}	
		
		return true;
	}

	@Override
	public void exchangePair() {
		ListItem<T> lItem = firstItem;
		
		while (lItem.next != null && lItem != null) {
			T tmp = lItem.key;
			lItem.key = lItem.next.key;
			lItem.next.key = tmp;			

			lItem = lItem.next.next;
		}			
	}

	@SuppressWarnings("unchecked")
	@Override
	public void rotateAll() {
		Object[] elems = new Object[count];
		int index = 0;
		
		ListItem<T> lItem = firstItem;

		
		while (lItem != null) {
			elems[index] = lItem;	
			index++;
			lItem = lItem.next;
		}	
		
		for (int i = 0; i < elems.length / 2; i++) {
			ListItem<T> elem1 = (ListItem<T>) elems[i];
			ListItem<T> elem2 = (ListItem<T>) elems[count - i - 1];

			
			T tmp = elem1.key;
			elem1.key = elem2.key;
			elem2.key = tmp;
		}
	}

	
	public void rotateAllwithBack() {
		ListItem<T> lastItem = firstItem;
		ListItem<T> firstLItem = firstItem;
		
		if (lastItem == null)
			return;
		
		// Get lastItem
		while (lastItem.next != null)
			lastItem = lastItem.next;
		
		for (int i = 0; i < count / 2; i++) {
			T tmp = firstLItem.key;
			firstLItem.key = lastItem.key;
			lastItem.key = tmp;
			
			firstLItem = firstLItem.next;
			lastItem = lastItem.back;
		}
	}
}
