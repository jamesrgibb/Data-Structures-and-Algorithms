/**
 * 
 */
package assignment06;

import components.list.DoublyLinkedList;
import components.list.List;
import components.list.ListOnArrays;
import components.stopwatch.Stopwatch1;
import components.util.Utilities;

/**
 * @author Jacob Morrison and James Gibb
 * @param <E>
 *
 */
public class Timing<E> {

	/**
	 * @param <E>
	 * @param args
	 * @return
	 * @return
	 */

	private static Stopwatch1 timer;
	
	// length of list, we changed this for every list size and ran each one manually
	private static int n = 500000;

	public static long addAtFront(A6DoublyLinkedList<Integer> list) {
		
		// creating a new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// makes random element between 0-9 and inserts at font of list
		final int x = Utilities.randomIntBetween(0, 9);
		list.add(0, x);
		
		// ends timer
		timer.stop();
		
		return timer.elapsed();
	}
	
	public static long addAtFrontListOnArrays(ListOnArrays<Integer> list) {
		// creating a new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// makes random element between 0-9 and inserts at font of list
		final int x = Utilities.randomIntBetween(0, 9);
		list.add(0, x);
		
		// ends timer
		timer.stop();
		
		return timer.elapsed();
	}
	
	public static long addAtEnd(A6DoublyLinkedList<Integer> list) {
		
		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// creates random element between 0-9 and adds at the end of the list
		int x = Utilities.randomIntBetween(0, 9);
		list.add(x);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}
	
	public static long addAtEndListOnArrays(ListOnArrays<Integer> list) {
		
		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// creates random element between 0-9 and adds at the end of the list
		int x = Utilities.randomIntBetween(0, 9);
		list.add(x);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}
	
	public static long addRandomlyInMiddle(A6DoublyLinkedList<Integer> list) {

		//randomly in middle
		int rand = Utilities.randomIntBetween(0, n - 1);

		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();
		
		// makes random element between 0-9 and inserts into at rand
		int x = Utilities.randomIntBetween(0, 9);
		list.add(rand, x);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}
	
	public static long addRandomlyInMiddleListOnArrays(ListOnArrays<Integer> list) {

		//randomly in middle
		int rand = Utilities.randomIntBetween(0, n - 1);

		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();
		
		// makes random element between 0-9 and inserts into at rand
		int x = Utilities.randomIntBetween(0, 9);
		list.add(rand, x);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}

	public static long removeRandomlyInMiddle(A6DoublyLinkedList<Integer> list) {

		//randomly in middle
		int rand = Utilities.randomIntBetween(0, n - 1);

		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();
		
		
		
		list.remove(rand);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}
	public static long removeRandomlyInMiddleListOnArrays(ListOnArrays<Integer> list) {

		//randomly in middle
		int rand = Utilities.randomIntBetween(0, n - 1);

		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();
		

		list.remove(rand);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}
	
public static long removeAtEnd(A6DoublyLinkedList<Integer> list) {
		
		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// creates random element between 0-9 and adds at the end of the list
		int x = Utilities.randomIntBetween(0, 9);
		list.remove(x);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}
	
	public static long removeAtEndListOnArrays(ListOnArrays<Integer> list) {
		
		// creates new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// creates random element between 0-9 and adds at the end of the list
		int x = Utilities.randomIntBetween(0, 9);
		list.remove(x);
		
		// stops timer
		timer.stop();
		
		return timer.elapsed();
	}
	
public static long removeAtFront(A6DoublyLinkedList<Integer> list) {
		
		// creating a new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// makes random element between 0-9 and inserts at font of list
		final int x = Utilities.randomIntBetween(0, 9);
		list.remove(x);
		
		// ends timer
		timer.stop();
		
		return timer.elapsed();
	}
	
	public static long removeAtFrontListOnArrays(ListOnArrays<Integer> list) {
		// creating a new timer
		timer = new Stopwatch1();
		timer.reset();
		// starts timer
		timer.start();

		// makes random element between 0-9 and inserts at font of list
		final int x = Utilities.randomIntBetween(0, 9);
		list.remove(x);
		
		// ends timer
		timer.stop();
		
		return timer.elapsed();
	}
	public static void main(String[] args) {
		A6DoublyLinkedList<Integer> list1 = new A6DoublyLinkedList<Integer>();
		// adds "n" number of elements to list1
		for(int i = 0; i < n; i++) {
			int x = Utilities.randomIntBetween(0, 9);
			list1.add(x);
		}
		ListOnArrays<Integer> list2 = new ListOnArrays<Integer>();
		// adds "n" number of elements to list2
		for(int i = 0; i < n; i++) {
			int x = Utilities.randomIntBetween(0, 9);
			list2.add(x);
		}
		// get timing for add using ListOnArrays
//		System.out.println(n);
//		System.out.println("addAtFrontListOnArrays, " + addAtFrontListOnArrays(list2));
//		System.out.println("addAtEndListOnArrays, " + addAtEndListOnArrays(list2));
//		System.out.println("addRandomlyInMiddleListOnArrays, " + addRandomlyInMiddleListOnArrays(list2));
		
		// get timing for add using A6DoublyLinkedLists
//		System.out.println(n);
//		System.out.println("addAtFront, " + addAtFront(list1));
//		System.out.println("addAtEnd, " + addAtEnd(list1));
//		System.out.println("addRandomlyInMiddle, " + addRandomlyInMiddle(list1));
		
		// get timing for remove using ListOnArrays
		System.out.println("removeAtFrontListOnArrays, " + removeAtFrontListOnArrays(list2));
		System.out.println("removeRandomlyInMiddleListOnArrays, " + removeRandomlyInMiddleListOnArrays(list2));
		System.out.println("removeAtEndListOnArrays, " + removeAtEndListOnArrays(list2));
		System.out.println();
		System.out.println();
		// get timing for remove using A6DoublyLinkedLists
		System.out.println("removeAtFront, " + removeAtFront(list1));
		System.out.println("removeAtEnd, " + removeAtEnd(list1));
		System.out.println("remocveRandomlyInMiddle, " + removeRandomlyInMiddle(list1));
		

	}
}
