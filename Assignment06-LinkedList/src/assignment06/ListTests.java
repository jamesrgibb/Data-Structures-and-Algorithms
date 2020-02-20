package assignment06;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.list.List;
import components.list.ListOnArrays;  
import components.util.Utilities;

public final class ListTests {

  @SafeVarargs
  private final <E> List<E> uutFromArgs(E... args) {
    List<E> list = new A6DoublyLinkedList<E>();
    for (E arg : args)
      list.add(arg);
    return list;
  }

  @SafeVarargs
  private final <E> List<E> refFromArgs(E... args) {
    List<E> ref = new ListOnArrays<E>();
    for (E arg : args)
      ref.add(arg);
    assert ref instanceof ListOnArrays<?> : "Ref not correct";
    return ref;
  }

  @Test
  public void testConstructor() {
    List<Integer> list = new ListOnArrays<>();
    assertEquals("[]", list.toString());
  }

  @Test
  public void addToEmptyUsingIndex() {
    List<Integer> list = uutFromArgs();
    list.add(0, 10);
    assertEquals(refFromArgs(10), list);
  }

  @Test
  public void addToFrontNonEmpty() {
    List<Integer> list = uutFromArgs(30, 20, 50);
    list.add(0, 100);
    assertEquals(refFromArgs(100, 30, 20, 50), list);
  }

  @Test
  public void addToEndNonEmpty() {
    List<Integer> list = uutFromArgs(30, 20, 50);
    list.add(3, 100);
    assertEquals(refFromArgs(30, 20, 50, 100), list);
  }

  @Test
  public void addMiddleNonEmpty() {
    List<Integer> list = uutFromArgs(30, 20, 50);
    list.add(1, 100);
    assertEquals(refFromArgs(30, 100, 20, 50), list);
  }

  @Test
  public void removeToEmpty() {
    List<Integer> list = uutFromArgs(42);
    int x = list.remove(0);
    assertEquals(42, x);
    assertEquals(refFromArgs(), list);
  }

  @Test
  public void removeFront() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int x = list.remove(0);
    assertEquals(42, x);
    assertEquals(refFromArgs(37, 59, 94), list);
  }

  @Test
  public void removeEnd() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int x = list.remove(list.size() - 1);
    assertEquals(94, x);
    assertEquals(refFromArgs(42, 37, 59), list);
  }

  @Test
  public void removeMiddle() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int x = list.remove(1);
    assertEquals(37, x);
    assertEquals(refFromArgs(42, 59, 94), list);
  }

  @Test
  public void indexOfFoundAtFront() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int idx = list.indexOf(42);
    assertEquals(0, idx);
    assertEquals(refFromArgs(42, 37, 59, 94), list);
  }

  @Test
  public void indexOfFoundAtEnd() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int idx = list.indexOf(94);
    assertEquals(list.size() - 1, idx);
    assertEquals(refFromArgs(42, 37, 59, 94), list);
  }

  @Test
  public void indexOfFoundAtMiddle() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int idx = list.indexOf(59);
    assertEquals(2, idx);
    assertEquals(refFromArgs(42, 37, 59, 94), list);
  }

  @Test
  public void indexOfNotFound() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int idx = list.indexOf(100);
    assertEquals(-1, idx);
    assertEquals(refFromArgs(42, 37, 59, 94), list);
  }

  @Test
  public void sizeNonEmpty() {
    List<Integer> list = uutFromArgs(42, 37, 59, 94);
    int size = list.size();
    assertEquals(4, size);
    assertEquals(refFromArgs(42, 37, 59, 94), list);
  }

  @Test
  public void sizeEmpty() {
    List<Integer> list = uutFromArgs();
    int size = list.size();
    assertEquals(0, size);
    assertEquals(refFromArgs(), list);
  }

  @Test
  public void testClear() {
    List<Integer> list = uutFromArgs(32, 54, 12, 66, -10);
    list.clear();
    assertEquals(refFromArgs(), list);
  }

  @Test
  public void getFront() {
    List<Integer> list = uutFromArgs(32, 54, 12, 66, -10);
    assertEquals(32, (int) list.get(0));
    assertEquals(refFromArgs(32, 54, 12, 66, -10), list);
  }

  @Test
  public void getEnd() {
    List<Integer> list = uutFromArgs(32, 54, 12, 66, -10);
    assertEquals(-10, (int) list.get(list.size() - 1));
    assertEquals(refFromArgs(32, 54, 12, 66, -10), list);
  }

  @Test
  public void getMiddle() {
    List<Integer> list = uutFromArgs(32, 54, 12, 66, -10);
    assertEquals(12, (int) list.get(2));
    assertEquals(refFromArgs(32, 54, 12, 66, -10), list);
  }

  @Test
  public void testAddSecondaryToEmpty() {
    List<String> list = uutFromArgs();
    list.add("red");
    assertEquals(refFromArgs("red"), list);
  }

  @Test
  public void testAddSecondaryToNonEmpty() {
    List<String> list = uutFromArgs("green", "blue");
    list.add("red");
    assertEquals(refFromArgs("green", "blue", "red"), list);
  }

  @Test
  public void setFront() {
    List<String> list = uutFromArgs("green", "blue", "orange");
    list.set(0, "red");
    assertEquals(refFromArgs("red", "blue", "orange"), list);
  }

  @Test
  public void setEnd() {
    List<String> list = uutFromArgs("green", "blue", "orange");
    list.set(2, "red");
    assertEquals(refFromArgs("green", "blue", "red"), list);
  }

  @Test
  public void setMiddle() {
    List<String> list = uutFromArgs("green", "blue", "orange");
    list.set(1, "red");
    assertEquals(refFromArgs("green", "red", "orange"), list);
  }

  @Test
  public void equalsEmpty() {
    List<Integer> list = uutFromArgs();
    assertEquals(refFromArgs(), list);
  }

  @Test
  public void equalsNonEmpty() {
    List<String> list = uutFromArgs("red", "green", "blue");
    assertEquals(refFromArgs("red", "green", "blue"), list);
  }

  @Test
  public void reverseEmpty() {
    List<String> list = uutFromArgs();
    list.reverse();
    assertEquals("[]", list.toString());
  }

  @Test
  public void reverseEvenLength() {
    List<Integer> list = uutFromArgs(1, 2, 3, 4);
    list.reverse();
    assertEquals("[4,3,2,1]", list.toString());
  }

  @Test
  public void reverseOddLength() {
    List<Integer> list = uutFromArgs(1, 2, 3, 4, 5);
    list.reverse();
    assertEquals("[5,4,3,2,1]", list.toString());
  }

  @Test
  public void regressionAdd() {
    List<Integer> list = uutFromArgs();
    List<Integer> ref = refFromArgs();

    final int n = Utilities.randomIntBetween(1000, 10000);
    for (int i = 0; i < n; i++) {
      final int x = Utilities.randomIntBetween(0, Integer.MAX_VALUE);
      list.add(x);
      ref.add(x);
    }

    assertEquals(ref, list);
  }

  @Test
  public void regressionRemove() {
    List<Integer> list = uutFromArgs();
    List<Integer> ref = refFromArgs();

    final int n = Utilities.randomIntBetween(1000, 10000);
    for (int i = 0; i < n; i++) {
      final int x = Utilities.randomIntBetween(0, Integer.MAX_VALUE);
      list.add(x);
      ref.add(x);
    }

    final int samples = 500;
    for (int i = 0; i < samples; i++) {
      final int randomIndex = Utilities.randomIntBetween(0, list.size() - 1);
      final int removedFromList = list.remove(randomIndex);
      final int removedFromRef = ref.remove(randomIndex);
      assertEquals(removedFromRef, removedFromList);
    }

    assertEquals(ref, list);
  }

  @Test
  public void regressionIndexOf() {
    List<Integer> list = uutFromArgs();
    List<Integer> ref = refFromArgs();

    final int n = Utilities.randomIntBetween(1000, 10000);
    for (int i = 0; i < n; i++) {
      final int x = Utilities.randomIntBetween(0, Integer.MAX_VALUE);
      list.add(x);
      ref.add(x);
    }

    final int samples = 500;
    for (int i = 0; i < samples; i++) {
      final int intToFind = Utilities.randomIntBetween(0, Integer.MAX_VALUE);
      final int indexInList = list.indexOf(intToFind);
      final int indexInRef = ref.indexOf(intToFind);
      assertEquals(indexInRef, indexInList);
    }

    assertEquals(ref, list);
  }

  @Test
  public void regressionGet() {
    List<Integer> list = uutFromArgs();
    List<Integer> ref = refFromArgs();

    final int n = Utilities.randomIntBetween(1000, 10000);
    for (int i = 0; i < n; i++) {
      final int x = Utilities.randomIntBetween(0, Integer.MAX_VALUE);
      list.add(x);
      ref.add(x);
    }

    final int samples = 500;
    for (int i = 0; i < samples; i++) {
      final int randomIndex = Utilities.randomIntBetween(0, list.size() - 1);
      final int numFromList = list.get(randomIndex);
      final int numFromRef = ref.get(randomIndex);
      assertEquals(numFromRef, numFromList);
    }

    assertEquals(ref, list);
  }

  @Test
  public void regressionSet() {
    List<Integer> list = uutFromArgs();
    List<Integer> ref = refFromArgs();

    final int n = Utilities.randomIntBetween(1000, 10000);
    for (int i = 0; i < n; i++) {
      final int x = Utilities.randomIntBetween(0, Integer.MAX_VALUE);
      list.add(x);
      ref.add(x);
    }

    final int samples = 500;
    for (int i = 0; i < samples; i++) {
      final int randomIndex = Utilities.randomIntBetween(0, list.size() - 1);
      list.set(randomIndex, randomIndex);
      ref.set(randomIndex, randomIndex);
    }

    assertEquals(ref, list);
  }

  @Test
  public void regressionReverse() {
    List<Integer> list = uutFromArgs();
    List<Integer> ref = refFromArgs();

    final int n = Utilities.randomIntBetween(1000, 10000);
    for (int i = 0; i < n; i++) {
      final int x = Utilities.randomIntBetween(0, Integer.MAX_VALUE);
      list.add(x);
      ref.add(x);
    }

    list.reverse();
    ref.reverse();

    assertEquals(ref, list);
  }
}
