package iterativ;

import java.util.Comparator;
import java.util.LinkedList;

import structures.AbstractBinaryTree;

public class BinaryTree<T> extends AbstractBinaryTree<T> {

	public BinaryTree(Comparator<T> cmp) {
		super(cmp);
	}

	@Override
	public void add(T item) {
		TreeNode<T> node = new TreeNode<>();		
		
		node.key = item;
		node.left = null;
		node.right = null;
		
		if (firstNode == null) {
			firstNode = node;
			return;
		}
			
		TreeNode<T> current = firstNode;
		
		count++;
		
		while (true) {
			int cmpResult = comperator.compare(item, current.key);
			
			if (cmpResult <= 0)
				if (current.left == null) {
					current.left = node;
					return;
				} else {
					current = current.left;
				}
			
			if (cmpResult > 0)
				if (current.right == null) {
					current.right = node;
					return;
				} else {
					current = current.right;
				}
		}	

	}

	
	
	
	private void removeKey(TreeNode<T> parent, Position pos) {
		TreeNode<T> current;
	
		if (parent == null) {
			current = firstNode;
		} else if(pos == Position.LEFT) {
			current = parent.left;			
		} else {
			current = parent.right;
		}
		
		
		// There is a left and a right sub-tree
		if (current.left != null && current.right != null) {
			TreeNode<T> min = current.left;
			
			// Find fitting Element and change
			
			if (min.right == null) {
				current.key = min.key;
				current.left = min.left;
				
				return;
			}
			
			while (min.right.right != null)
				min = min.right;
			
			current.key = min.right.key;
			min.right = min.right.left;
			
			count--;
			
			return;
		}
		
		// There is just a left sub-tree
		if (current.left != null) {
			if (parent == null)
				parent = current.left;
			else if (pos == Position.LEFT)
				parent.left = current.left;
			else
				parent.right = current.left;
			
			return;
		}
		
		
		// .right != null or .right == null
		
		// Node is leaf or has only one sub-tree
		if (parent == null)
			parent = current.right;
		else if (pos == Position.LEFT)
			parent.left = current.right;
		else
			parent.right = current.right;
		
		return;

	}
	
	
	@Override
	public void removeAll(T item) {
		TreeNode<T> current = firstNode;
		TreeNode<T> parent = null;
		Position direction = null;
		
		while (current != null) {	
			
			if (current.key.equals(item)) {
				removeKey(parent, direction);
				
				if (parent != null)
					current = parent; // May the entry was replaced by the same key -> test this entry again
			}
			
			
			
			int cmpResult = comperator.compare(item, current.key);
			
			parent = current;
			
			// (C.cmp(a, b) == 0) does NOT equal (a.equals(b) == true)
			
			if (cmpResult <= 0) {
				direction = Position.LEFT;
				current = current.left;
			} else {
				direction = Position.RIGHT;
				current = current.right;
			}				
		}	
	}

	@Override
	public boolean contains(T item) {
		TreeNode<T> current = firstNode;
		
		while (current != null) {
			int cmpResult = comperator.compare(item, current.key);
			
			if (current.key.equals(item))
				return true;
			

			// (C.cmp(a, b) == 0) does NOT equal (a.equals(b) == true)
			if (cmpResult <= 0)
				current = current.left;
			
			if (cmpResult > 0)
				current = current.right;
		}	
		
		return false;
	}

	
	
	
	protected class StackElement {
		public TreeNode<T> Node;
		public int Status = 0;
		
		public StackElement(TreeNode<T> node) {
			Node = node;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		LinkedList<StackElement> list = new LinkedList<>();
		LinkedList<T> output = new LinkedList<>();
		
		list.add(new StackElement(firstNode));
		

		while (list.size() > 0) {
			StackElement current = list.get(list.size() - 1);
			
			if (current.Status == 0) {
				if (current.Node.left == null) {
					current.Status = 1;					
				} else {
					list.add(new StackElement(current.Node.left));
				}
			}

			if (current.Status == 1) {
				output.add(current.Node.key);
				
				if (current.Node.right == null) {
					current.Status = 2;
				} else {
					list.add(new StackElement(current.Node.right));
				}
			}	

			if (current.Status == 2) {
				list.remove(list.size() - 1);
				
				if (list.size() > 0)
					list.get(list.size() - 1).Status++;
			}
			
		}

		return (T[]) output.toArray();
	}

}
