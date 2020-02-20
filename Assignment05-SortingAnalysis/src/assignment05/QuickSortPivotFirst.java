package assignment05;

import java.util.List;

/**
 * Implements in-place QuickSort using the {@code Sorter} interface by selecting
 * the first element in the list as the pivot.
 * 
 * This class must implement the {@code setThreshold, threshold} methods.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class QuickSortPivotFirst<T extends Comparable<? super T>>
    extends AbstractQuickSort<T> {	
	private int threshold;
	public QuickSortPivotFirst() {
	this.name = "QuickSortPivotFirst";
	this.complexity = ComplexityClass.NSQUARED;
	threshold = 6;
	}
  @Override
  protected T pivot(List<T> list, int start, int end) {
    assert list != null : "Violation of: list is not null";
    return list.get(start);
  }
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
