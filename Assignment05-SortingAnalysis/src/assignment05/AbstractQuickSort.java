package assignment05;

import java.util.List;

/**
 * Implementation of common methods for all quick-sort variants.
 * 
 * @author  James Gibb & John Gibb
 *
 * @param <T> type of elements of the collection to be sorted
 */
public abstract class AbstractQuickSort<T extends Comparable<? super T>> extends AbstractSorter<T> {
	/**
	 * Returns the pivot around which to quick-sort the list. This method may modify
	 * the {@code list}, e.g., median of three will move smallest value to front of
	 * the list.
	 * 
	 * @param list  the list to find the pivot in
	 * @param start start-index of the sublist to consider
	 * @param end   end-index of the sublist to consider
	 * @return pivot for quick-sort
	 * 
	 * @requires start <= end
	 * 
	 * @modifies {@code list}
	 */
	protected abstract T pivot(List<T> list, int start, int end);

	protected int pivotIndex(List<T> list, int start, int end, T pivot) {
		for (int count = start; count < end; count++) {
			if (list.get(count).compareTo(pivot) == 0) {
				return count;
			}
		}
		return 0;
	}

	protected void change(List<T> list, int x, int y) {
		T temp = list.get(x);
		list.set(x, list.get(y));
		list.set(y, temp);
	}

	protected int changeLeft(List<T> list, int left, int right, T pivot, int index) {
		for (int i = left; i < index; i++) {
			if (pivot.compareTo(list.get(i)) <= 0) {
				index--;
				change(list, index, i);
			}
		}
		return index;
	}

	protected int changeRight(List<T> list, int left, int right, T pivot, int index) {
		for (int i = index; i < right; i++) {
			if (pivot.compareTo(list.get(i)) > 0)
				index++;
			change(list, index, i);
		}
		return index;
	}

	/**
	 * Determines a pivot and partitions the {@code list} between {@code left} and
	 * {@code right} such that all elements less than pivot are on its left and all
	 * elements greater than pivot are to its right.
	 * 
	 * @param list  the list to be partitioned
	 * @param left  start-index of the sublist to partition
	 * @param right end-index of the sublist to partition
	 * @return the location of the pivot in the resulting list
	 * 
	 * @modifies {@code list}
	 */
	protected int partition(List<T> list, int left, int right) {
		assert list != null : "Violation of: list is not null";
		T pivot = pivot(list, left, right);
		int index = pivotIndex(list, left, right, pivot);
		index = changeLeft(list, left, right, pivot, index);
		index = changeRight(list, left, right, pivot, index);
		return index;
	}

	/**
	 * Sorts the virtual {@code list} between indexes {@code left} and {@code right}
	 * using the Quick-Sort algorithm.
	 * 
	 * @param list  the list to be sorted
	 * @param left  start-index of the sublist to be sorted
	 * @param right end-index of the sublist to be sorted
	 * 
	 * @modifies {@code list}
	 */
	protected void quickSort(List<T> list, int left, int right) {
		assert list != null : "Violation of: list is not null";
		if (right-left > threshold() && SortUtils.isSorted(list)) {
			int pivot = partition(list, left, right);
			quickSort(list, left, pivot - 1);
			quickSort(list, pivot + 1, right);
		} else {
			insertionQuick(list, left, right);
			
		}
	}

	@Override
	public void sort(List<T> list) {
		assert list != null : "Violation of: list is not null";
		setThreshold(6);
		quickSort(list, 0, list.size());
	}
	  public void insertionQuick(List<T> list, int start, int end) {
	    assert list != null : "Violation of: list is not null";
	    List<T> li =  list;
		  for(int count = start; count<end;count++) {
		   int c2 = count+1; 
		   while(c2<end) { // insertion sort loop 
		    if(li.get(count).compareTo(li.get(c2))>0){
		     T num = li.get(c2); //get the index for new value
		     for(int i = c2-1; i >= count; i--) { 
		      li.set(i+1, li.get(i)); // set the index for the new value
		     }
		    li.set(count, num); // insert into the new value
		    }
		    c2++;
		  }
		  }
		  list = li;
	  }
}
