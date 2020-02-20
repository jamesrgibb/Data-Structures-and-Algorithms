package assignment05;
/**
 * @author James Gibb & John Gibb
 * 
 */
public enum ComplexityClass {
  LOGN("O(log n)"), N("O(n)"), NLOGN("O(n log n)"), NSQUARED("O(n^2)");
  private String value;

  private ComplexityClass(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
