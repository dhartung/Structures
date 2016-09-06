package structures;

public interface ILinearOperations<T> extends IOperations<T> {
	
	public T getItem(int index);
	
	public int searchIndex(T needle);
	
	public void removeAt(int index);
	
	public boolean replaceAll(T search, T replace);
}
