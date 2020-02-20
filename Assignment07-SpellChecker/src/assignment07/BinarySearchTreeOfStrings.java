package assignment07;



/**
 * A binary search tree implementation from scratch.
 * 
 * @author Jacob Morrison and James Gibb
 *
 */
public class BinarySearchTreeOfStrings {
	public static class Node<String> {
		public Node() {
			data = null;
			left = null;
			right = null;

		}

		private String data;
		private Node<String> left, right;
	}

	/**
	 * Root node of the Binary Search Tree.
	 */
	private Node<String> root;

	/**
	 * Holds the size of {@code this}.
	 */
	private int size;

	/**
	 * No argument constructor.
	 */
	public BinarySearchTreeOfStrings() {
		clear();
	}

	/**
	 * Removes all the nodes in this tree.
	 * 
	 * @modifies this tree
	 */
	public void clear() {
		size = 0;
		root = null;
	}

	/**
	 * Inserts the element {@code x} at the appropriate position in this tree.
	 * 
	 * @param x element to be inserted
	 * @modifies this tree
	 */
	public void insert(String x) {
		
		// Create new node and assign data to be inserted
		Node<String> n = new Node<>();
		n.data = x;

		// Create node to use to move down the BST for comparison
		Node<String> comp = root;

		if (root == null) {
			root = n;
		} else {
			// if n is less than root and root does not have a left assign root.left as n
			if (n.data.compareTo(root.data) < 0 && root.left == null) {
				root.left = n;
				comp = root.left;
				size++;
				return;
			}
			// if n is greater than root and root does not have a right assign root.right as
			// n
			if (n.data.compareTo(root.data) > 0 && root.right == null) {
				root.right = n;
				comp = root.right;
				size++;
				return;
			}
			// iterates through BST unit it finds null value
			while (true) {
				// determines if n.data should be added on left side and adds data when it is at
				// the bottom of tree
				if (n.data.compareTo(comp.data) < 0 && comp.left == null) {
					comp.left = n;
					break;
				}
				// determines if n.data should be added on right side and adds data when it is
				// at the bottom of tree
				if (n.data.compareTo(comp.data) > 0 && comp.right == null) {
					comp.right = n;
					break;
				}
				// iterates through left side of branches
				if (n.data.compareTo(comp.data) < 0) {
					comp = comp.left;
					continue;
				}
				// iterates through right side of branches
				if (n.data.compareTo(comp.data) > 0) {
					comp = comp.right;
				}
			}
		}
		size++;
	}

	/**
	 * Removes the element {@code x} from this tree.
	 * 
	 * @param x element to be removed
	 * @modifies this tree
	 * @requires {@code x} is in {@code this}
	 */
	public void remove(String x) {
		assert contains(x) == true : "String not in BST";

		// Create new node and assign data to be inserted
		Node<String> n = new Node<>();
		n.data = x;

		// Create node to use to move down the BST for comparison
		Node<String> comp = root;

		// Create new node to assign as parent node
		Node<String> parent = new Node<>();

		// remove and reassign root if root is x
		if (n.data.compareTo(root.data) == 0) {
			if (comp.left == null && comp.right == null) {
				n = null;
				comp = n;
				size--;
				return;
			}

			comp = root.right;
			while (comp.left != null) {
				comp = comp.left;
			}
			comp.left = root.left;
			root = comp;
			size--;
			return;
		}
		while (true) {

			// compares n to comp.left and if it is the same we stop one node short of the
			// desired node to remove
			if(comp.left != null) {
			if (n.data.compareTo(comp.left.data) == 0) {
				// 0 child helper cases
				// removes desired node if the left child node has 0 children
				if (comp.left.left == null && comp.left.right == null) {
					comp.left = null;
					break;
				}
				// 1 child case (left of comp)
				// if node has one child node on the left it removes the desired node
				if (comp.left.left != null && comp.left.right == null) {
					// TODO make helper method
					comp.left = comp.left.left;
					break;
				}
				// if node has one child node on the right it removes the desired node
				if (comp.left.left == null && comp.left.right != null) {
					comp.left = comp.left.left;
					break;
				}
				// 2 child case (left of comp)
				if (comp.left.left != null && comp.left.right != null) {
					parent = comp;
					parent = parent.left.right;
					while (parent.left != null) {
						parent = parent.left;
					}
					// swaps data
					Node<String> temp = new Node<>();
					temp.data = parent.data;
					parent.data = comp.left.data;
					comp.left.data = temp.data;
					comp.left.right = null;

//					comp.left.data = parent.data;
//					parent = null;
					break;
				}
			}
			}
			// compares n to comp.right and if it is the same we stop one node short of the
			// desired node to remove
			if (n.data.compareTo(comp.right.data) == 0) {
				// 0 child helper cases
				// removes desired node if the right child node has 0 children
				if (comp.right.left == null && comp.right.right == null) {
					comp.right = null;
					break;
				}
				// 1 child case (right of comp)
				// if node has one child node on the right it removes the desired node
				if (comp.right.right != null && comp.right.left == null) {
					// TODO make helper method
					comp.right = comp.right.right;
					break;
				}
				// if node has one child node on the left it removes the desired node
				if (comp.right.right == null && comp.right.left != null) {
					comp.right = comp.right.right;
					break;
				}
				// 2 child case (right of comp)
				if (comp.right.right != null && comp.right.left != null) {
					parent = comp;
					parent = comp.right.right;
					while (parent.left != null) {
						parent = parent.left;
					}
					Node<String> temp = new Node<>();
					temp.data = parent.data;
					parent.data = comp.right.data;
					comp.right.data = temp.data;
					comp.right.right = null;

					break;
				}
			}

			// iterates through left side of branches
			if (n.data.compareTo(comp.data) < 0) {
				comp = comp.left;
				continue;
			}
			// iterates through right side of branches
			if (n.data.compareTo(comp.data) > 0) {
				comp = comp.right;
			}

		}
		size--;
	}

	/**
	 * Reports the number of nodes in this tree.
	 * 
	 * @return size of {@code this}
	 */
	public int size() {
		return size;
	}

	/**
	 * Reports whether this tree contains {@code x}.
	 * 
	 * @param x element to search
	 * @return true iff this contains x
	 */
	public boolean contains(String x) {
		// check to see if the BST has at lease one value
		if (size() > 0) {
			// return helper method starting comparisons at the root
			return containsTree(root, x);
		} else {
			return false;
		}
	}

	/**
	 * Returns the root of this tree.
	 * 
	 * @return root of this tree
	 * @requires this is not empty
	 */
	public String root() {
		return root.data;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		if (size() == 0) {
			return "[]";
		}
		// calls helper method
		buildTree(root, string);
		// removes "," off last object in list
		String s = string.substring(0, string.length() - 1);
		return "[" + s + "]";
	}

	/*
	 * Helper methods
	 */
	// helper method for toString
	private void buildTree(Node<String> node, StringBuilder string) {

		// traverses left to end of tree
		if (node.left != null) {
			buildTree(node.left, string);
		}

		// adds node to StringBuilder
		string.append(node.data + ",");

		// traverses right to end of tree
		if (node.right != null) {
			buildTree(node.right, string);
		}
	}

	// helper method for contains method
	private boolean containsTree(Node<String> node, String string) {
		// return false if the string being passed in has a null value
		if (node == null) {
			return false;
		}

		// return true if the string being passed in is equal to the first node you
		// compare to
		if (string.compareTo(node.data) == 0) {
			return true;
		}

		// recursively check all child nodes to the left
		if (string.compareTo(node.data) < 0) {
			return containsTree(node.left, string);
		}

		// recursively check all child nodes to the right
		if (string.compareTo(node.data) > 0) {
			return containsTree(node.right, string);	
		}

		return false;
	}

//	public static void main(String[] args) {
//		BinarySearchTreeOfStrings list = new BinarySearchTreeOfStrings();
//		list.insert("red");
//		list.insert("yellow");
//		list.remove("yellow");
//		System.out.println(list.toString());

//		list.insert("white");
//		list.insert("green");
//		list.insert("blue");
//		list.insert("purple");
//		list.insert("auburn");
//		list.insert("quora");
//		list.insert("indigo");
		// list.remove("red");
		// System.out.println(list.toString());
//		System.out.println(list.size);
//		System.out.println(list.contains("green"));
//		list.remove("green");
//		System.out.println(list.toString());
		// System.out.println(list.size);
//	}
}
