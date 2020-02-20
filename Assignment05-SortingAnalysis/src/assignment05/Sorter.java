package assignment05;

import java.util.List;

/**
 * The base interface for implementing various sorting algorithms.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of elements of the collection to be sorted
 */
public interface Sorter<T extends Comparable<? super T>> {
  /**
   * Sorts the given list in the "increasing" order as defined by the
   * {@code Comparable} extended by {@code T}.
   * 
   * @param list the list of {@code T}s to be sorted
   * 
   * @ensures {@code list} is sorted
   * 
   * @modifies {@code list}
   */
  public void sort(List<T> list);

  /**
   * Returns the name of the sorting algorithm (must be set in the constructor).
   * 
   * @return name of the sorting algorithm
   */
  public String name();

  /**
   * Sets the threshold for the size of input. If the input is smaller than that
   * size, insertion sort should be called. (Optional method.)
   * 
   * @param threshold the threshold to be set
   * 
   * @requires threshold is nonnegative
   * 
   * @throws UnsupportedOperationException if the method is not implemented
   */
  public void setThreshold(int threshold)
      throws UnsupportedOperationException;

  /**
   * Gets the threshold for the size of input (Optional method) below which an
   * insertion sort is better than merge or quick sorts.
   * 
   * @returns the threshold
   * 
   * @throws UnsupportedOperationException if the method is not implemented
   */
  public int threshold() throws UnsupportedOperationException;

  /**
   * Return the expected Complexity Class (O(n), O(n^2), etc.) of this sorting
   * algorithm.
   * 
   * @return expected complexity class
   */
  public ComplexityClass getExpectedComplexityClass();


}
