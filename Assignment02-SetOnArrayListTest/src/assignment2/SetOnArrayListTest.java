package assignment2;

import static org.junit.Assert.*;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class SetOnArrayListTest {
	/**
	 * Creates and returns a Set under test, i.e. a set with dynamic type
	 * SetOnArrayList, populated with the given words.
	 * 
	 * @param words Strings to populate the set with
	 * @return generated set with the proper dynamic type
	 */
	private Set<String> createFromArgsUUT(String... words) {
		Set<String> s = new SetOnArrayList<>();
		for (String w : words) {
			if (!s.contains(w)) {
				s.add(w);
			}
		}
		return s;
	}

	/**
	 * Creates and returns a Set for reference, i.e. a set with dynamic type Set1L,
	 * populated with the given words.
	 * 
	 * @param words Strings to populate the set with
	 * @return generated set with the proper dynamic type
	 */
	private Set<String> createFromArgsRef(String... words) {
		Set<String> s = new Set1L<>();
		for (String w : words) {
			if (!s.contains(w)) {
				s.add(w);
			}
		}
		return s;
	}

	@Test
	public final void testSize() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT();
		// Adds the string "red" to the empty set s
		s.add("red");
		// Creates a Set1L with a string "red" in it
		Set<String> sExpected = createFromArgsRef("red");
		// Checks if the two sets are equal
		assertEquals(s.size(), sExpected.size());
	}
	public final void testSizeMultiple() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT();
		// Adds the string "red" to the empty set s
		s.add("red");
		s.add("black");
		s.add("blue");
		s.add("white");
		//add values to sExpected
		Set<String> sExpected = createFromArgsRef("red","black","blue","white");
		// Checks if the two sets are equal
		assertEquals(s.size(), sExpected.size());
	}
	public final void testSizeEmpty() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT();
		Set<String> sExpected = createFromArgsRef();
		// Checks if the two sets are equal
		assertEquals(s.size(), sExpected.size());
	}
	public final void testAddMore() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT("red");
		s.add("black");
		s.add("blue");
		s.add("white");
		// Creates a Set1L with a string "red" in it
		Set<String> sExpected = createFromArgsRef("red","black","blue","white");
		// Checks if the two sets are equal
		assertEquals(s, sExpected);
	}
	public final void testAddItemThatAlreadyExists() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT("blue");
		// Adds the strings to the empty set s and duplicate as well
		s.add("red");
		s.add("black");
		s.add("blue");
		s.add("white");
		// Creates a Set1L with a string "red" in it
		Set<String> sExpected = createFromArgsRef("red","black","blue","white");
		// Checks if the two sets are equal
		assertEquals(s, sExpected);
	}
	@Test
	public final void testAddToEmpty() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT();
		// Adds the string "red" to the empty set s
		s.add("red");
		// Creates a Set1L with a string "red" in it
		Set<String> sExpected = createFromArgsRef("red");
		// Checks if the two sets are equal
		assertEquals(s, sExpected);
	}
	@Test
		// Creates and empty set s (SetOnArrayList)
	public final void testRemove() {
		Set<String> s = createFromArgsUUT("blue");
		s.add("red");
		// Removes the string "red" to the empty set s
		s.remove("red");
		// Creates a Set1L with a string "red" in it
		Set<String> sExpected = createFromArgsRef("blue");
		// Checks if the two sets are equal
		assertEquals(s, sExpected);
	}

	@Test
	public final void testRemoveWithMultipleValues() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT("black");
		s.add("red");
		s.add("blue");
		s.remove("red");
		s.remove("blue");
		// Removes the string "red" to the empty set s		
		// Creates a Set1L with a string "red" in it
		Set<String> sExpected = createFromArgsRef("black");
		// Checks if the two sets are equal
		assertEquals(s, sExpected);
	}
	@Test
	public final void testContains() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> sExpected = createFromArgsRef("red");
		// tests contains
		assertTrue(sExpected.contains("red"));
	}
	@Test
	public final void testContainsForTwoValues() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> sExpected = createFromArgsRef("red","blue");
		// tests contains
		assertTrue(sExpected.contains("red") && sExpected.contains("blue"));
	}
	@Test
	public final void testContainsWhenEmpty() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> sExpected = createFromArgsRef("");
		// tests contains
		assertTrue(sExpected.contains(""));
	}
	@Test
	public final void testContainswithMultipleItems() {
		Set<String> sExpected = createFromArgsRef("red", "black", "blue", "white");
		// Checks if the two sets are equal
		assertTrue(sExpected.contains("blue") && sExpected.contains("blue")
				&& sExpected.contains("black") && sExpected.contains("white"));
	}
	@Test
	public final void testContainswithFalse() {
		
		Set<String> sExpected = createFromArgsRef("red", "black", "blue", "white");
		// Checks if the two sets are equal
		assertFalse(sExpected.contains("purple"));
	}
	@Test
	public final void testMultipleFalseValuesWithContains() {
		
		Set<String> sExpected = createFromArgsRef("red", "black", "blue", "white");
		// Checks if the two sets are equal
		assertFalse(sExpected.contains("purple") && sExpected.contains("green") && sExpected.contains("pink"));
	}
	@Test
	public final void testContainswithFalseAndEmpty() {
		Set<String> sExpected = createFromArgsRef("");
		// Checks if the two sets are equal
		assertFalse(sExpected.contains("black"));
	}	
}
