package structures;

public interface IExtendedLinearOperations<T> extends IExtendedOperations<T> {

	public boolean isAscending(java.util.Comparator<T> cmp);
	
	public void exchangePair();
	
	public void rotateAll();
}
