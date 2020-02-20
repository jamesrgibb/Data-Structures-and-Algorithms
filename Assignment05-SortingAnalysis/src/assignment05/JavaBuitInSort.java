package assignment05;

import java.util.Collections;
import java.util.List;



/**
 * Wraps Java's built-in sort in the {@code Sorter} interface.
 * 
 * This class does not implement the {@code setThreshold} method.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class JavaBuitInSort<T extends Comparable<? super T>>
    extends AbstractSorter<T> {

  /**
   * Sole constructor
   */
  public JavaBuitInSort() {
    this.name = "Java Sort";
    this.complexity = ComplexityClass.NLOGN;
  }

  @Override
  public final void sort(List<T> list) {
    Collections.sort(list);
  }

}
