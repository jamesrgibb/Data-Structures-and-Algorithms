package assignment05;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements QuickSort using the {@code Sorter} interface by selecting the
 * first element in the list as the pivot and partitions the list by creating
 * copies instead of doing it in-place.
 * 
 * This class does not implement the {@code setThreshold, threshold} methods.
 * 
 * This class does not extend {@code AbstractQuickSort}, but it extends
 * {@code AbstractSorter} and thus implements the {@code Sorter}.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class QuickSortNaive<T extends Comparable<? super T>>
    extends AbstractSorter<T> {

  /**
   * Sole constructor.
   */
  public QuickSortNaive() {
    this.name = "QuickSortNaive";
    this.complexity = ComplexityClass.NLOGN;
  }

  @Override
  public final void sort(List<T> list) {
    if (list.size() > 1) {
      T pivot = list.remove(0);

      List<T> front = new ArrayList<T>();
      List<T> back = new ArrayList<T>();

      naivePartition(list, pivot, front, back);
      sort(front);
      sort(back);

      list.addAll(front);
      list.add(pivot);
      list.addAll(back);
    }
  }

  /**
   * Partitions {@code list} into two parts: entries no larger than {@code pivot}
   * are put in {@code front}, and the rest are put in {@code back}. {@code list}
   * is empty at the end of this method.
   *
   * @param <T>   type of {@code List} entries
   * @param list  the {@code List} to be partitioned
   * @param pivot the partitioning value
   * @param front upon return, the entries no larger than {@code pivot}
   * @param back  upon return, the entries larger than {@code pivot}
   * @param order ordering by which to separate entries
   * @modifies list, front, back
   */
  private void naivePartition(List<T> list, T pivot, List<T> front,
      List<T> back) {
    assert list != null : "Violation of: list is not null";
    assert front != null : "Violation of: front is not null";
    assert back != null : "Violation of: back is not null";
    assert pivot != null : "Violation of: pivot is not null";

    while (list.size() > 0) {
      T x = list.remove(0);
      if (x.compareTo(pivot) < 0) {
        front.add(x);
      } else {
        back.add(x);
      }
    }
  }

}
