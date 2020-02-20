package assignment06;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import components.list.DoublyLinkedList;

/**
 * 
 * @author James Gibb and Jacob Morrison
 *
 * @param <E>
 */
public class A6DoublyLinkedList<E> extends DoublyLinkedList<E> {
	/*
	 * Private members
	 */
	private static final int NOT_FOUND = -1;

	private static class Node<E> {
		public Node() {
			next = null;
			previous = null;
			data = null;
		}

		private E data;
		private Node<E> next, previous;
	}

	/**
	 * Head & tail node of the doubly-linked list.
	 */
	private Node<E> head;
	private Node<E> tail;
	/**
	 * Holds the size of {@code this}.
	 */
	private int size;
	/**
	 * Hold the number of modifications made to the list so far, useful for
	 * iterator.
	 */
	private int modCount = 0;

	/**
	 * Constructor for 
	 */
	public A6DoublyLinkedList() {
		clear();
	}

	/**
	 * Clears Doubly Linked List, resets head & tail, and updates size and modCount.
	 */
	@Override
	public void clear() {
		head = new Node<>();
		tail = new Node<>();
		head.next = tail;
		tail.previous = head;
		head.previous = null;
		tail.next = null;
		size = 0;
		modCount++;
	}

	/**
	 * Adds object to double linked list at specific index location
	 */
	@Override
	public void add(int index, E x) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index <= this.size() : "Violation of: index < this.size()";
		assert x != null : "Violation of: x is not null";

		// node created and is assigned to the object that is being added
		Node<E> n = new Node<>();
		n.data = x;

		// iterates through to the correct index location
		Node<E> p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}

		// assigns next and previous pointers to added object
		n.next = p.next;
		p.next.previous = n;
		p.next = n;
		n.previous = p;

		size++;
		modCount++;
	}

	/**
	 * Removes and returns object from specified location by reassigning next and previous pointers to overlook
	 * object to be removed
	 */
	@Override
	public E remove(int index) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index < this.size() : "Violation of: index < this.size()";

		// node created to return removed object
		Node<E> p = head;
		// iterates to the object you want to remove
		for (int i = 0; i < index; i++) {
			p = p.next;
		}

		// reassigns next and previous pointers to bypass object and given index
		Node<E> objectToRemove = p.next;
		p.next = objectToRemove.next;
		p.next.previous = p;

		size--;
		modCount++;
		
		// returns object you removed
		return objectToRemove.data;
	}

	/**
	 * Returns index of given object
	 */
	@Override
	public int indexOf(E x) {
		assert x != null : "Violation of: x is not null";

		// node created to return index
		Node<E> p = head.next;
		int result = NOT_FOUND;
		int i = 0;
		
		// iterates through list until given object is found
		while (p != null) {
			if (p.data == x) {
				result = i;
				break;
			}
			i++;
			p = p.next;
		}
		// returns given object or "NOT_FOUND"
		return result;
	}

	/**
	 * Returns size of doubly linked list
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * Secondary methods
	 */
	
	/**
	 * Returns object from doubly linked list from given index
	 */
	@Override
	public E get(int index) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index < this.size() : "Violation of: index < this.size()";

		// node created to return object
		Node<E> p = head.next;
		
		// iterates through list to given index
		for (int i = 0; i < index; i++)
			p = p.next;

		return p.data;
	}

	/*
	 * Iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new A6DoublyLinkedListIterator();
	}

	/**
	 * Adds object at end of the doubly linked list
	 */
	@Override
	public void add(E x) {
		this.add(size, x);
	}

	/**
	 * Prints doubly linked list using iterator and StringBuilder
	 * Prints in format "[X,X,X,X]"
	 */
	@Override
	public String toString() {
		// toString with iterator
		StringBuilder string = new StringBuilder();
		Iterator<E> it = iterator();
		string.append("[");
		// checks if "it" has a next value
		while (it.hasNext()) {
			// increments "it"
			E val = it.next();
			// adds val and "," to string
			if (it.hasNext()) {
				string.append(val + ",");
			} else {
				string.append(val);
			}
		}
		string.append("]");
		return string.toString();
	}

	/**
	 * Reverses the order of doubly linked list and correctly reassigns next and previous pointers
	 */
	@Override
	public void reverse() {
	// node created to assign a new head for the reversed list
	Node<E> newHead = new Node<>();
	// node created to go through doubly linked list, starting from the last object in list
	Node<E> current = tail.previous;
	// node created to iterate through doubly linked list
	Node<E> itNode = newHead;
	while (current != null) {
	// node created for new doubly linked list to be assigned correct data from old list 
	Node<E> newNode = new Node<>();
	// next and previous indicators are assigned to newNode
	itNode.next = newNode;
	newNode.previous = itNode;
	// data from old list is assigned to newNode in reversed doubly linked list
	newNode.data = current.data;
	itNode = newNode;
	current = current.previous;
	}

	// head and tail are correctly reassigned
	head = newHead;
	tail = itNode;
	modCount++;
	}

	private class A6DoublyLinkedListIterator implements Iterator<E> {
		/*** Current node. */
		private Node<E> current;
		/*** Expected count of modifications , set to modCount in the constructor. */
		private int expectedModCount;

		A6DoublyLinkedListIterator() {
			current = head.next;
			expectedModCount = modCount;
		}

		// Return true if the iteration has more elements
		@Override
		public boolean hasNext() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException();
			return current.next != null;
		}

		// return the value from the node it is currently pointing to, and then advance
		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E hold = current.data;
			current = current.next;
			return hold;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


}
