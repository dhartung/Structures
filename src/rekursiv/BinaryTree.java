package rekursiv;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import structures.AbstractBinaryTree;

public class BinaryTree<T> extends AbstractBinaryTree<T> {

	public BinaryTree(Comparator<T> cmp) {
		super(cmp);
	}

	
	private void add(TreeNode<T> item, TreeNode<T> current) {
		int cmpResult = comperator.compare(item.key, current.key);
		
		if (cmpResult <= 0) {
			if (current.left == null)
				current.left = item;				
			else
				add(item, current.left);
			
		} else {
			if (current.right == null)
				current.right = item;
			else
				add(item, current.right);
		}
	}
	
	@Override
	public void add(T item) {
		TreeNode<T> tItem = new TreeNode<>();
		
		tItem.key = item;
		
		if (firstNode == null)
			firstNode = tItem;
		else
			add(tItem, firstNode);
	}

	
	
	private void removeAll(T item, TreeNode<T> current) {	
		if (current.left != null) {
						
			if (current.left.key.equals(item)) {
				remove(current, Position.LEFT);
				
				removeAll(item, current);
			} else {
				removeAll(item, current.left);
			}		
		}
			
		if (current.right != null) {
			
			if (current.right.key.equals(item)) {
				remove(current, Position.RIGHT);
				
				removeAll(item, current);
			} else {
				removeAll(item, current.right);
			}			
		}		
	}
	
	
	private TreeNode<T> get2ndPrevElement(TreeNode<T> start) {
		if (start.right.right == null)
			return start;
		
		return get2ndPrevElement(start.right);
	}
	
	private void remove(TreeNode<T> parent, Position side) {
		TreeNode<T> current;
		
		if (parent == null)
			current = firstNode;
		else if (side == Position.LEFT)
			current = parent.left;
		else
			current = parent.right;
		
		// Node has a right and a left sub-tree
		if (current.right != null && current.left != null) {
			
			
			if (current.left.right != null) {
				TreeNode<T> min = get2ndPrevElement(current.left);  
				
				// min.left = smallest element, thats bigger than the selected one
				
				current.key = min.right.key;			
				remove(min, Position.RIGHT);
			} else {
				current.key = current.left.key;
				current.left = current.left.left;
			}
						
			return;
		}
		
		if (current.left != null) {
			if (parent == null)
				firstNode = current;
			else if (side == Position.LEFT)
				parent.left = current.left;
			else
				parent.right = current.left;
			
			return;
		}
			
		// current.right != null or both are null
		
		if (parent == null)
			firstNode = current;
		else if (side == Position.LEFT)
			parent.left = current.right;
		else
			parent.right = current.right;
	}
	
	
	@Override
	public void removeAll(T item) {
		if (firstNode.key.equals(item)) {
			remove(null, null);
			
			removeAll(item);
		}			
		
		removeAll(item, firstNode);
	}

	
	private boolean contains(T item, TreeNode<T> current) {
		if (current == null)
			return false;
		
		if (current.key.equals(item))
			return true;
		
		if (comperator.compare(item, current.key) <= 0)
			return contains(item, current.left);
		else
			return contains(item, current.right);
	}
	
	@Override
	public boolean contains(T item) {
		return contains(item, firstNode);
	}

	
	private void toList(TreeNode<T> current, List<T> output) {
		if (current == null)
			return;
		
		toList(current.left, output);
		
		output.add(current.key);
		
		toList(current.right, output);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		LinkedList<T> output = new LinkedList<>();
		
		toList(firstNode, output);
		
		return (T[]) output.toArray();
	}

}
