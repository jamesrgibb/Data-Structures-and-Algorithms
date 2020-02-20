package assignment09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import components.list.List;
import components.list.ListOnJavaArrayList;

// TODO make a siftUp for add method

/**
 * Implementation of a Binary Heap on array.
 * 
 * @author Jacob Morrison and James Gibb
 *
 * @param <E> type of the element of the heap
 */
public class Heap<E> {
	private static final int INITIAL_CAPACITY = 10;

	private E[] rep;
	private Comparator<E> order;
	private int end;

	/**
	 * No argument constructor, builds an empty heap.
	 * 
	 * @ensures this is a heap
	 */
	public Heap() {
		// initialize rep as a new object with INITIAL_CAPACITY
		rep = (E[]) new Object[INITIAL_CAPACITY];
		// call inferOrder to determine the order for the type of object we are using.
		inferOrder();
		end = 0;
	}

	/**
	 * Constructor from a comparator, builds an empty heap with the given ordering.
	 * 
	 * @param cmp comparator for the new heap
	 * @ensures this is a heap
	 */
	public Heap(Comparator<E> cmp) {
		// set cmp equal to order which is the comparator for the whole class
		order = cmp;
		// initialize rep as a new object with INITIAL_CAPACITY
		rep = (E[]) new Object[INITIAL_CAPACITY];
		end = 0;
	}

	/**
	 * Constructor from an array, builds a heap from the given array elements.
	 * 
	 * @param args elements to put in this heap
	 * @ensures this is a heap
	 */
	public Heap(E[] args) {
		end = 0;
		rep = (E[]) new Object[INITIAL_CAPACITY];
		for (int i = 0; i < args.length - 1; i++) {
			rep[i] = args[i];
			end++;
		}
		// call inferOrder to determine the order for the type of object we are using.
		inferOrder();
		// call heapify to set it up as a heap.
		heapify(0);

	}

	/**
	 * Infers the order based on the type of the elements, {@code E}. If
	 * {@code E extends Comparable}, it gets the order from that relation,
	 * otherwise, it tries to infer it by comparing the hashcodes of the two
	 * elements.
	 */
	private void inferOrder() {
		order = new Comparator<E>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(E x, E y) {
				try {
					return ((Comparable<E>) x).compareTo(y);
				} catch (ClassCastException e) {
					return Integer.compare(x.hashCode(), y.hashCode());
				}
			}
		};
	}

	/**
	 * Converts the given complete binary tree into a heap.
	 * 
	 * @param start index of the root in the underlying array representation
	 * @ensures the rep is a heap
	 * @requires the rep is a complete binary tree
	 * @modifies this
	 */
	private void heapify(int start) {
		int leftChildIndex;
		int rightChildIndex;
		if (2 * start + 1 < end) {
			leftChildIndex = (2 * start) + 1;
			rightChildIndex = (2 * start) + 2;
		} else {
			return;
		}

		heapify(leftChildIndex);
		heapify(rightChildIndex);
		siftDown(start);
	}

	/**
	 * Sifts the root of the tree down appropriately so that the resulting tree is a
	 * heap.
	 * 
	 * @param start index of the root in the underlying array representation
	 * @requires both left and right subtrees are heaps
	 * @ensures the rep is a heap
	 * @modifies this
	 */
	private void siftDown(int start) {

		E leftChild;
		E rightChild;
		E parent;
		E smallerChild;
		int smChildIdx = 0;

		if (2 * start + 1 < end) {
			leftChild = rep[2 * start + 1];
			rightChild = rep[2 * start + 2];
			parent = rep[start];
			smallerChild = rep[start];

		} else {
			return;
		}
		if (2 * start + 2 < end) {
			if (order.compare(leftChild, rightChild) < 0) {
				smallerChild = leftChild;
				smChildIdx = (2 * start) + 1;
			} else {
				smallerChild = rightChild;
				smChildIdx = (2 * start) + 2;
			}
		}

		if (size() < 3) {
			if (order.compare(rep[0], rep[1]) > 0) {
				swap(0, 1);
			}
		}

		if (order.compare(smallerChild, parent) < 0) {
			swap(start, smChildIdx);
//			if (smChildIdx + 1 < size() - 1) {
			siftDown(smChildIdx);
		}
		// }
	}

	/**
	 * Sifts the child of the tree up (towards the root) appropriately so that the
	 * resulting tree is a heap.
	 * 
	 * @param start index of the root in the underlying array representation
	 * @requires both left and right subtrees are heaps
	 * @ensures the rep is a heap
	 * @modifies this
	 */
	private void siftUp(int start) {
		E parent = rep[start];
		int parentIndex = 0;

		if ((start - 1) / 2 < end) {
			parent = rep[(start - 1) / 2];
			parentIndex = (start - 1) / 2;
		} else {
			return;
		}

		if (order.compare(parent, rep[start]) > 0) {
			swap(parentIndex, start);
			siftUp(parentIndex);
		}
	}

	/**
	 * Swaps two values
	 * 
	 * @param parent
	 * @param child
	 */
	private void swap(int x, int y) {
		E temp = rep[x];
		rep[x] = rep[y];
		rep[y] = temp;
	}

	/**
	 * Adds {@code x} to this maintaining the heap property.
	 * 
	 * @param x element to be added
	 * @modifies this
	 */
	public void add(E x) {
		if (size() == rep.length) {
			rep = resize(rep);
		}
		// add the new object at the end of the heap
		rep[end] = x;
		// siftUp the object to the correct location
		siftUp(end);
		end++;
	}

	private E[] resize(E[] rep) {
		int length = rep.length;
		E[] resizedRep = (E[]) new Object[length * 2];
		for (int i = 0; i < length; i++) {
			resizedRep[i] = rep[i];
		}
		return resizedRep;
	}

	/**
	 * Removes and returns the first element from the heap.
	 * 
	 * @return the first element of this
	 * @requires this is not empty
	 * @modifies this
	 */
	public E removeFirst() {
		// create object to be removed
		E removedObject = rep[0];
		if (rep.length <= 1) {
			clear();
		}
		// swap root with end element
		swap(0, size() - 1);
		end--;
		// sift the new root down to the correct spot
		siftDown(0);

		return removedObject;
	}

	/**
	 * Reports the number of elements in this heap.
	 * 
	 * @return number of elements in this
	 */
	public int size() {
		return end;
	}

	/**
	 * Change the order of this heap to the given one.
	 * 
	 * @param cmp new ordering relation
	 * @modifies this
	 */
	public void changeOrder(Comparator<E> cmp) {
		order = cmp;
		heapify(0);
	}

	public void clear() {
		rep = (E[]) new Object[INITIAL_CAPACITY];
	}

	/**
	 * Puts the contents of this heap in a list in a sorted order and returns it,
	 * emptying the heap in the process.
	 * 
	 * @return contents of the heap in a sorted order
	 * @modifies this
	 */
	public List<E> sort() {
		List<E> sortedHeapList = new ListOnJavaArrayList<E>();
		while (end > 0) {
			sortedHeapList.add(removeFirst());
		}

		return sortedHeapList; // TODO implement this method
	}

	/*
	 * Methods from Object
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for (int i = 0; i < end; i++) {
			s.append(rep[i] + ",");
		}
		if (s.length() > 1) {
			s.deleteCharAt(s.length() - 1);
		}
		s.append("]");
		String ret = s.toString();
		return ret;
	}

}
