
package assignment08;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * HashTableSet test cases.
 * 
 * @author James Gibb and Jacob Morrison
 *
 */
public class HashTableSetTest {

	@SafeVarargs
	private final <E> HashTableSet<E> uutFromArgs(E... args) {
		HashTableSet<E> uut = new HashTableSet<E>();
		for (E x : args)
			uut.add(x);
		return uut;
	}

	@SafeVarargs
	private final static <E> boolean equalsSetsIgnoreOrder(HashTableSet<E> actual, E... expected) {
		boolean result = actual.size() == expected.length;
		int i = 0;
		while (result && i < expected.length) {
			E current = expected[i++];
			result = result && actual.contains(current);
		}
		return result;
	}

	@Test
	public final void testAddEmpty() {
		HashTableSet<String> s = uutFromArgs();
		s.add("red");
//    assertEquals("{red}", s.toString());
		assertTrue(equalsSetsIgnoreOrder(s, "red"));
	}

	@Test
	public final void testAddNonEmpty() {
		HashTableSet<String> s = uutFromArgs("blue", "green");
		s.add("red");
//    String sStr = s.toString();
//    assertEquals(true,
//        sStr.contains("red") && sStr.contains("blue")
//            && sStr.contains("green")
//            && sStr.length() == "{red,green,blue}".length());
		assertTrue(equalsSetsIgnoreOrder(s, "blue", "green", "red"));
	}

	@Test
	public final void testRemoveToEmpty() {
		HashTableSet<String> s = uutFromArgs("green");
		s.remove("green");
		assertEquals("{}", s.toString());
	}

	@Test
	public final void testRemoveToNonEmpty() {
		HashTableSet<String> s = uutFromArgs("green", "blue");
		s.remove("green");
		assertEquals("{blue}", s.toString());
	}

	@Test
	public final void testContainsEmpty() {
		HashTableSet<String> s = this.uutFromArgs();
		boolean result = s.contains("one");
		assertEquals("{}", s.toString());
		assertEquals(false, result);
	}

	@Test
	public final void testContainsNonEmptyTrue() {
		HashTableSet<String> s = this.uutFromArgs("two");
		boolean result = s.contains("two");
		assertEquals("{two}", s.toString());
		assertEquals(true, result);
	}

	@Test
	public final void testContainsNonEmptyFalse() {
		HashTableSet<String> s = this.uutFromArgs("one");
		boolean result = s.contains("two");
		assertEquals("{one}", s.toString());
		assertEquals(false, result);
	}

	@Test
	public final void testSizeEmpty() {
		HashTableSet<String> s = this.uutFromArgs();
		int size = s.size();
		assertEquals("{}", s.toString());
		assertEquals(0, size);
	}

	@Test
	public final void testSizeNonEmpty() {
		HashTableSet<String> s = this.uutFromArgs("one");
		int size = s.size();
		assertEquals("{one}", s.toString());
		assertEquals(1, size);
	}

	@Test
	public final void testAddIntSet() {
		HashTableSet<Integer> s = this.uutFromArgs(1);
		s.add(2);
//    assertEquals("{1,2}", s.toString());
//    assertEquals(2, s.size());
		assertTrue(equalsSetsIgnoreOrder(s, 1, 2));
	}

	@Test
	public final void testRemoveInt() {
		HashTableSet<Integer> s = this.uutFromArgs(32, -45, 91, 0, 43);
		s.remove(91);
//    assertEquals("{0,32,43,-45}", s.toString());
//    assertEquals(4, s.size());
		assertTrue(equalsSetsIgnoreOrder(s, 0, 32, 43, -45));
	}

	@Test
	public final void testClear() {
		HashTableSet<Integer> s = this.uutFromArgs(32, -45, 91, 0, 43);
		int oldBuckets = s.numBuckets();
		s.clear();
		assertEquals("{}", s.toString());
		assertEquals(oldBuckets, s.numBuckets());
	}

	/*
	 * Mod tests
	 */
	@Test
	public void testPositiveMod1() {
		int x = HashTableSet.mod(7, 1);
		assertEquals(0, x);
	}

	@Test
	public void testNegativeMod1() {
		int x = HashTableSet.mod(-11, 1);
		assertEquals(0, x);
	}

	@Test
	public void testPositiveMod3Rem0() {
		int x = HashTableSet.mod(243, 3);
		assertEquals(0, x);
	}

	@Test
	public void testPositiveMod3Rem1() {
		int x = HashTableSet.mod(730, 3);
		assertEquals(1, x);
	}

	@Test
	public void testPositiveMod3Rem2() {
		int x = HashTableSet.mod(2189, 3);
		assertEquals(2, x);
	}

	@Test
	public void testNegativeMod7Rem0() {
		int x = HashTableSet.mod(-49, 7);
		assertEquals(0, x);
	}

	@Test
	public void testNegativeMod7Rem1() {
		int x = HashTableSet.mod(-342, 7);
		assertEquals(1, x);
	}

	@Test
	public void testNegativeMod7Rem2() {
		int x = HashTableSet.mod(-2399, 7);
		assertEquals(2, x);
	}

	@Test
	public void testNegativeMod7Rem3() {
		int x = HashTableSet.mod(-16790, 7);
		assertEquals(3, x);
	}

	@Test
	public void testNegativeMod7Rem4() {
		int x = HashTableSet.mod(-117547, 7);
		assertEquals(4, x);
	}

	@Test
	public void testNegativeMod7Rem5() {
		int x = HashTableSet.mod(-822852, 7);
		assertEquals(5, x);
	}

	@Test
	public void testNegativeMod7Rem6() {
		int x = HashTableSet.mod(-5759993, 7);
		assertEquals(6, x);
	}

	@Test
	public void testPositiveModBigger() {
		int x = HashTableSet.mod(23, 101);
		assertEquals(23, x);
	}

	@Test
	public void testNegativeModBigger() {
		int x = HashTableSet.mod(-37, 101);
		assertEquals(64, x);
	}

	@Test
	public void testPositiveModMax() {
		int x = HashTableSet.mod(2, Integer.MAX_VALUE);
		assertEquals(2, x);
	}

	@Test
	public void testMaxModMax() {
		int x = HashTableSet.mod(Integer.MAX_VALUE, Integer.MAX_VALUE);
		assertEquals(0, x);
	}

	@Test
	public void testMinModMax() {
		int x = HashTableSet.mod(Integer.MIN_VALUE, Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE - 1, x);
	}

	@Test
	public void testMinPlus1ModMax() {
		int x = HashTableSet.mod(Integer.MIN_VALUE + 1, Integer.MAX_VALUE);
		assertEquals(0, x);
	}

}
