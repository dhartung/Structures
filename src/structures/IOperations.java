package structures;

public interface IOperations<T> {
	
	public void add(T item);
	
	public void removeAll(T item);
	
	public boolean contains(T item);
	
	public T[] toArray();
	
}
