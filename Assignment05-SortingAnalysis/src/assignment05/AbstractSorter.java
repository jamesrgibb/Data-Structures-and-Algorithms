package assignment05;

/**
 * Implementations of the common methods of the {@code Sorter} component.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of elements of the collection to be sorted
 */
public abstract class AbstractSorter<T extends Comparable<? super T>>
    implements Sorter<T> {

  /**
   * Name of the sorter, e.g. "Insertion Sort".
   */
  protected String name;
  /**
   * Complexity class, e.g., O(n^2), using the enum {@link ComplexityClass}.
   */
  protected ComplexityClass complexity;

  @Override
  public String name() {
    return name;
  }

  @Override
  public final ComplexityClass getExpectedComplexityClass() {
    return this.complexity;
  }

  @Override
  public void setThreshold(int threshold)
      throws UnsupportedOperationException {
    assert threshold >= 0 : "Violation of: threshold non-negative";

    throw new UnsupportedOperationException(
        "This operation is not supported by " + this.name());
  }

  @Override
  public int threshold() throws UnsupportedOperationException {

    throw new UnsupportedOperationException(
        "This operation is not supported by " + this.name());
  }

  // TODO Override other methods if required
  // TODO Add private helper methods as needed

}
