package assignment05;

import java.util.List;

/**
 * Implements in-place QuickSort using the {@code Sorter} interface by selecting
 * the "median of three" as the pivot.
 * 
 * This class must implement the {@code setThreshold, threshold} methods.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class QuickSortPivotM3<T extends Comparable<? super T>>
    extends AbstractQuickSort<T> {
	private int threshold;
	public QuickSortPivotM3() {
	threshold = 6;
	this.name = "QuickSortPivotM3";
	this.complexity = ComplexityClass.NLOGN;
  // TODO add constructor
}
  @Override
  protected T pivot(List<T> list, int start, int end) {
    assert list != null : "Violation of: list is not null";
  T x = list.get(start);
  T y = list.get(end-1);
  T z = list.get((end-start)/2);
  if((x.compareTo(y)>0 && x.compareTo(z)<0)||(x.compareTo(y)<0 && x.compareTo(z)>0)||x.compareTo(z)==0) {
	  return x;
  }
  if((y.compareTo(x)>0 && y.compareTo(z)<0)||(y.compareTo(x)<0 && y.compareTo(z)>0)||y.compareTo(x)==0||y.compareTo(z)==0) {
	  return y;
  }
  if((z.compareTo(y)>0 && z.compareTo(x)<0)||(z.compareTo(y)<0 && z.compareTo(x)>0)) {
	  return z;
  }
    return null;
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
