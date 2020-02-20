package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * @author James Gibb & John Gibb
 * 
 */

public abstract class SorterTest {

  /**
   * Returns the sorter under test (InsertionSort, MergeSort, QuickSortPivotM3,
   * etc.) for Integers.
   * 
   * Implement this by extending this abstract class.
   * 
   * @return sorter under test
   */
  protected abstract Sorter<Integer> uutInt();

  /**
   * Returns the sorter under test (InsertionSort, MergeSort, QuickSortPivotM3,
   * etc.) for Strings.
   * 
   * Implement this by extending this abstract class.
   * 
   * @return sorter under test
   */
  protected abstract Sorter<String> uutString();

  /**
   * Helper method for creating lists from args quickly.
   * 
   * @param <T>  type of arguments
   * @param args variable arguments to be put in the list
   * @return a list containing {@code args} in that order
   */
  @SafeVarargs
  final private <T> List<T> listFromArgs(T... args) {
    List<T> list = new ArrayList<T>();
    for (T arg : args) {
      list.add(arg);
    }
    return list;
  }

  @Test
  public void sortEmpty() {
    Sorter<Integer> sTest = uutInt();
    List<Integer> listTest = listFromArgs();
    List<Integer> listRef = listFromArgs();
    sTest.sort(listTest);
    assertEquals(listRef, listTest);
  }

  @Test
  public void sortDuplicates() {
    Sorter<Integer> sTest = uutInt();
    List<Integer> listTest = listFromArgs(2, 2, 2, 2);
    List<Integer> listRef = listFromArgs(2, 2, 2, 2);
    sTest.sort(listTest);
    assertEquals(listRef, listTest);
  }

  @Test
  public void sortSorted() {
    Sorter<Integer> sTest = uutInt();
    List<Integer> listTest = listFromArgs(1, 2, 3, 4, 5);
    List<Integer> listRef = listFromArgs(1, 2, 3, 4, 5);
    sTest.sort(listTest);
    assertEquals(listRef, listTest);
  }

  @Test
  public void sortRevSortedStrings() {
    Sorter<String> sTest = uutString();
    List<String> listTest = listFromArgs("Zebra", "Yak", "Wolf");
    List<String> listRef = listFromArgs("Wolf", "Yak", "Zebra");
    sTest.sort(listTest);
    assertEquals(listRef, listTest);
  }

}

