package assignment05;

import java.util.List;
import java.util.Random;

/**
 * Implements in-place QuickSort using the {@code Sorter} interface by selecting
 * a random element in the list as the pivot.
 * 
 * This class must implement the {@code setThreshold, threshold} methods.
 * 
 * @author  James Gibb & John Gibb
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class QuickSortPivotRandom<T extends Comparable<? super T>>
    extends AbstractQuickSort<T> {
	private int threshold;
	public QuickSortPivotRandom() {
	threshold = 6;
	this.name = "QuickSortPivotRandom";
	this.complexity = ComplexityClass.NLOGN;
	}
  @Override
  protected T pivot(List<T> list, int start, int end) {
    assert list != null : "Violation of: list is not null";
    Random r = new Random(end + 1);
    return list.get(r.nextInt(end-start)+start);  }
  @Override
  public String name() {
	  return name;
  }
	public void solve(List<T> list) {
	  sort(list);
  }
	  @Override
	  public int threshold() {
		  return threshold;
	  }
	  @Override
	  public void setThreshold(int threshold1) {
		  threshold = threshold1; 
	  }
}