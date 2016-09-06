package structures;

public abstract class AbstractLinkedList<t> implements ILinearOperations<t> {
	
	
	protected ListItem<t> firstItem = null;
	protected ListItem<t> lastItem = null;
	protected int count = 0;
	
	/**
	 * Copied from work sheet 
	 */
	public class ListItem <T> {
		public T  key;
		public ListItem<T> next;
		public ListItem<T> back;
	}
	
	public int getCount() {
		return count;
	}
}
