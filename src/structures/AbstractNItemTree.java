package structures;

public abstract class AbstractNItemTree<t>  implements IOperations<t> {

	/**
	 * Copied from work sheet 
	 */
	public class TreeNode <T> {
		public T[]  theKeys;
		public TreeNode<T>[]  theSuccessors;
		public int numberOfKeys;
	}
}
