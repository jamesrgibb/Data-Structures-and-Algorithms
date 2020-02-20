package assignment08;

import java.util.ArrayList;

import components.list.List;
import components.list.SinglyLinkedList;
import components.set.Set;
import components.set.SetOnArray;
import components.set.SetOnHashTable;

/**
 * Hash Table based implementation of a Set.
 * 
 * @author Jacob Morrison and James Gibb
 *
 * @param <E> type of the elements of this set
 */
public class HashTableSet<E> {

	/*
	 * Private members -----
	 */
	private static final int DEFAULT_NUM_BUCKETS = 101;
	private Set<E>[] hashTable;
	private int size;

	/*
	 * Helper methods
	 */
	/**
	 * Computes {@code a} mod {@code b} as % should have been defined to work.
	 *
	 * @param a the number being reduced
	 * @param b the modulus
	 * @return the result of a mod b, which satisfies {@code 0 <=  mod < b}
	 * @requires b > 0
	 */
	public static int mod(int a, int b) {
		assert b > 0 : "Violation of: b > 0";
		// subtract the modulus from the num being reduced
		// and multiply it by the multiple of a divided by b.
		Integer modulus = (((a % b) + b) % b);
		if (a >= 0 && b >= 0 && a + b < 0) {
			return a % b;
		}
		return modulus;
	}

	/*
	 * Constructors -----
	 */
	/**
	 * No-argument constructor, resulting in a hash table of default size.
	 */
	public HashTableSet() {
		this.hashTable = new Set[DEFAULT_NUM_BUCKETS];
		size = 0;
		for (int i = 0; i < DEFAULT_NUM_BUCKETS; i++) {
			Set<E> set = new SetOnArray<E>();
			hashTable[i] = set;
		}

	}

	/**
	 * Constructor resulting in a hash table of size {@code hashTableSize}.
	 *
	 * @param hashTableSize size of hash table
	 */
	public HashTableSet(int hashTableSize) {
		this.hashTable = new Set[hashTableSize];
		size = 0;
		for (int i = 0; i < hashTableSize; i++) {
			Set<E> set = new SetOnArray<E>();
			hashTable[i] = set;
		}
	}

	/*
	 * Set methods -----
	 */
	/**
	 * Adds {@code x} to {@code this}.
	 *
	 * @param x the element to be added
	 * 
	 * @modifies {@code this}
	 * 
	 * @requires {@code x} is not in {@code this}
	 * 
	 */
	public void add(E x) {
		assert x != null : "Violation of: x is not null";
		assert !this.contains(x) : "Violation of: x is not in this";
		int index = mod(x.hashCode(), numBuckets());
		hashTable[index].add(x);
		size++;
	}

	/**
	 * Removes {@code x} from this.
	 *
	 * @param x the element to be removed
	 *
	 * @modifies {@code this}
	 * 
	 * @requires {@code x} is in {@code this}
	 * 
	 */
	public void remove(E x) {
		assert x != null : "Violation of: x is not null";
		assert this.contains(x) : "Violation of: x is in this";
		int index = mod(x.hashCode(), numBuckets());
		hashTable[index].remove(x);
		size--;
	}

	/**
	 * Reports whether {@code x} is in {@code this}.
	 *
	 * @param x the element to be checked
	 * @return true iff element is in {@code this}
	 * 
	 */
	public boolean contains(E x) {
		assert x != null : "Violation of: x is not null";
		// int index = mod(x.hashCode(), numBuckets());
		for (int index = 0; index < hashTable.length; index++) {
			if (hashTable[index].contains(x)) {
				return true;
			}
		}
		return false; // TODO implement this method
	}

	/**
	 * Reports the number of elements in {@code this}.
	 * 
	 * @return size of this set
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all the elements in {@code this}.
	 */
	public void clear() {
		this.hashTable = new Set[DEFAULT_NUM_BUCKETS];
		for (int i = 0; i < DEFAULT_NUM_BUCKETS; i++) {
			Set<E> set = new SetOnArray<E>();
			hashTable[i] = set;
		}
		// TODO check to see if this needs to be changed depending on the constructor
		// called
	}

	/*
	 * Methods inherited from Object
	 */
	/**
	 * String representation of the hash table. Elements in bucket 0, followed by
	 * those in bucket 1, and so on, separated by commas and enclosed in {..}.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < this.hashTable.length; i++) {
			if (this.hashTable[i].size() != 0) {
				String index = this.hashTable[i].toString();
				index = index.substring(1, index.length() - 1);
				s.append(index + ",");
			}
		}
		if (s.length() != 0) {
			s.deleteCharAt(s.length() - 1);
		}
		String result = s.toString();
		result = "{" + result + "}";
		return result; // TODO implement this method
	}

	/*
	 * Other methods specific to hash tables; for testing/performance purposes only
	 */
	/**
	 * Returns the number of elements in the specified bucket.
	 * 
	 * @param bucketIndex index of the bucket requested
	 * @requires 0 <= bucketIndex < numBuckets()
	 */
	public int bucketSize(int bucketIndex) {
		return this.hashTable[bucketIndex].size(); // TODO implement this method
	}

	/**
	 * Reports the number of buckets in this hashtable.
	 * 
	 * @return number of buckets
	 */
	public int numBuckets() {
		return hashTable.length; // TODO implement this method
	}

}
