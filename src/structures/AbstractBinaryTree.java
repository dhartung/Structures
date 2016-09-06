package structures;

public abstract class AbstractBinaryTree<t>  implements IOperations<t> {

	protected TreeNode<t> firstNode = null;
	protected java.util.Comparator<t> comperator;
	protected int count; // optional
	
	
	public AbstractBinaryTree(java.util.Comparator<t> cmp) {
		comperator = cmp;
	}
	
	public TreeNode<t> getRoot() {
		return firstNode;
	}
	
	/**
	 * Copied from work sheet 
	 */
	public class TreeNode <T> {
		public T  key;
		public TreeNode<T> left;
		public TreeNode<T> right;
	}
	
	public enum Position {
		LEFT, RIGHT
	}
	
	
	/**
	 * Debug-Stuff
	 */
    private void printTree(String indent, TreeNode<t> treeItem, boolean last)
    {
        
        String data = indent + (last ? "\\- " : "|- ");        		
        		
        data += treeItem == null ? '#' : treeItem.key.toString();
        indent += " ";

        System.out.println(data);
        
        if (treeItem != null && (treeItem.left != null || treeItem.right != null)){
        	printTree(indent, treeItem.left, false);
        	printTree(indent, treeItem.right, true);
        }
    }
    
    @Deprecated
    public final void printDebugTree() {
    	printTree("", firstNode, true);
    }
}
