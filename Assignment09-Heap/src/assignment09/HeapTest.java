package assignment09;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class HeapTest {
  @SafeVarargs
  private final <E> Heap<E> uutFromArgs(E... args) {
    Heap<E> h = new Heap<>();
    for (E e : args)
      h.add(e);
    return h;
  }

  /**
   * Reports whether the given heap is a min-heap of integers.
   * 
   * @param h heap to be tested
   * @return true iff h is a min-heap of integers
   */
  private static boolean isMinHeapInt(Heap<Integer> h) {
    if (h.size() == 0)
      return true;
    String result = h.toString();
    String[] nums = result.substring(1, result.length() - 1).split(","); // Substring removes brackets, split gets numbers
    Integer[] h1 = new Integer[nums.length];
    for (int i = 0; i < nums.length; i++) {
      h1[i] = Integer.parseInt(nums[i]);
    }
    return isHeap(h1, 0, (o1, o2) -> Integer.compare(o1, o2));
  }

  /**
   * Reports whether the given heap is a max-heap of integers.
   * 
   * @param h heap to be tested
   * @return true iff h is a max-heap of integers
   */
  private static boolean isMaxHeapInt(Heap<Integer> h) {
    if (h.size() == 0)
      return true;
    String result = h.toString();
    String[] nums = result.substring(1, result.length() - 1).split(","); // Substring removes brackets, split gets numbers
    Integer[] h1 = new Integer[nums.length];
    for (int i = 0; i < nums.length; i++) {
      h1[i] = Integer.parseInt(nums[i]);
    }
    return isHeap(h1, 0, (o1, o2) -> -Integer.compare(o1, o2));
  }

  /**
   * Reports whether the given heap is a min-heap of Strings.
   * 
   * @param h heap to be tested
   * @return true iff h is a min-heap of Strings
   */
  private static boolean isMinHeapString(Heap<String> h) {
    if (h.size() == 0)
      return true;
    String result = h.toString();
    String[] h1 = result.substring(1, result.length() - 1).split(","); // Substring removes brackets, split gets numbers

    return isHeap(h1, 0, (s1, s2) -> s1.compareTo(s2));
  }

  /**
   * Reports whether the given array containing a complete binary tree with root
   * at the index {@code start} is a heap under the given {@code order}.
   * 
   * @param <E>       type of the elements of the array
   * @param heapArray array as a heap to be tested
   * @param start     index of the root
   * @param order     comparator to check the heap property
   * @return true iff the array is a heap
   */
  private static <E> boolean isHeap(E[] heapArray, int start,
      Comparator<E> order) {
    boolean answer = true;
    int leftIdx = 2 * start + 1;
    if (leftIdx < heapArray.length) {
      answer = order.compare(heapArray[start], heapArray[leftIdx]) <= 0
          && isHeap(heapArray, leftIdx, order);
      int rightIdx = leftIdx + 1;
      if (rightIdx < heapArray.length) {
        answer = answer
            && order.compare(heapArray[start], heapArray[rightIdx]) <= 0
            && isHeap(heapArray, rightIdx, order);
      }
    }
    return answer;
  }

  @Test
  public void heapConstructorNoSiftDown() {
    Integer[] nums = { 10, 30, 20, 50, 40 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[10,30,20,50,40]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapConstructorSiftDownRight() {
    Integer[] nums = { 60, 30, 20, 50, 40 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[20,30,60,50,40]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapConstructorSiftDownLeft() {
    Integer[] nums = { 60, 20, 30, 50, 10 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[10,20,30,50,60]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapAddMany1() {
    Heap<Integer> h = uutFromArgs(10, 30, 20, 50, 40);
    assertTrue("[10,30,20,50,40]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapAddMany2() {
    Heap<Integer> h = uutFromArgs(60, 30, 20, 50, 40);
    assertTrue("[20,40,30,60,50]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapAddMany3() {
    Heap<Integer> h = uutFromArgs(60, 20, 30, 50, 10);
    assertTrue("[10,20,30,60,50]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapifyOneElt() {
    Integer[] nums = { 10 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[10]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(1, h.size());
  }

  @Test
  public void heapifyTwoElts1() {
    Integer[] nums = { 10, 20 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[10,20]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(2, h.size());
  }

  @Test
  public void heapifyTwoElts2() {
    Integer[] nums = { 20, 10 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[10,20]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(2, h.size());
  }

  @Test
  public void heapifyThreeElts1() {
    Integer[] nums = { 10, 20, 30 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[10,20,30]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(3, h.size());
  }

  @Test
  public void heapifyThreeElts2() {
    Integer[] nums = { 10, 30, 20 };
    Heap<Integer> h = new Heap<>(nums);
    assertTrue("[10,30,20]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(3, h.size());
  }

  @Test
  public void h30add20() {
    Heap<Integer> h = new Heap<>();
    h.add(30);
    h.add(20);

    assertTrue("[20,30]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(2, h.size());
  }

  @Test
  public void removeFirstSingle() {
    Heap<Integer> h = new Heap<>();
    h.add(30);
    int removed = h.removeFirst();
    assertTrue("[]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(0, h.size());
    assertEquals(30, removed);
  }

  @Test
  public void removeFirst1() {
    Heap<Integer> h = uutFromArgs(10, 30, 20, 50, 40);
    assertEquals(10, (int) h.removeFirst());
    assertTrue("[20,30,40,50]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(4, h.size());
  }

  @Test
  public void removeFirst2() {
    Heap<Integer> h = uutFromArgs(10, 30, 20, 50, 40);
    h.removeFirst();
    assertEquals(20, (int) h.removeFirst());
    assertTrue("[30,50,40]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(3, h.size());
  }

  @Test
  public void removeFirstAll() {
    Heap<Integer> h = uutFromArgs(10, 30, 20, 50, 40);
    assertEquals(10, (int) h.removeFirst());
    assertEquals(20, (int) h.removeFirst());
    assertEquals(30, (int) h.removeFirst());
    assertEquals(40, (int) h.removeFirst());
    assertEquals(50, (int) h.removeFirst());
    assertTrue("[]".equals(h.toString()) || isMinHeapInt(h));
    assertEquals(0, h.size());
  }

  @Test
  public void changeOrder() {
    Integer[] nums = { 60, 30, 20, 50, 40 };
    Heap<Integer> h = new Heap<>(nums);
    h.changeOrder((o1, o2) -> -Integer.compare(o1, o2));
    assertTrue("[60,50,20,30,40]".equals(h.toString()) || isMaxHeapInt(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapStrings1() {
    Heap<String> h = uutFromArgs("red");
    assertTrue("[red]".equals(h.toString()) || isMinHeapString(h));
    assertEquals(1, h.size());
  }

  @Test
  public void heapStrings2() {
    Heap<String> h = uutFromArgs("red", "blue", "black", "yellow", "green");
    assertTrue("[black,green,blue,yellow,red]".equals(h.toString())
        || isMinHeapString(h));
    assertEquals(5, h.size());
  }

  @Test
  public void heapStringsRemoveFirst() {
    Heap<String> h = uutFromArgs("red");
    assertEquals("red", h.removeFirst());
    assertTrue("[]".equals(h.toString()) || isMinHeapString(h));
    assertEquals(0, h.size());
  }

}
