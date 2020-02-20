package assignment03.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import assignment03.SudokuByElimination;

/**
 * JUnit test cases for public methods of the {@code SudokuByElimination} class.
 * 
 * @author Swaroop Joshi
 *
 */
public class SudokuByEliminationTest {
	private SudokuByElimination s;

	@Before // executed before every test case
	public void setUp() {
		s = new SudokuByElimination("data/sudoku1.txt");
	}
	/*
	 * checks to see if the element is being referenced correctly
	 * 
	 */
	@Test
	public void testElement() {
		assertEquals(2,s.element(0,4));
		assertEquals(0,s.element(8,8));
		assertEquals(7,s.element(4,0));
		assertEquals(0,s.element(0, 0));
		
	}
	/*
	 * tests the validity of the position of the element
	 * 
	 */
	@Test
	public void testIsValid() {
		assertEquals(false,s.isValid(0, 0, 9));
		assertEquals(true,s.isValid(0, 0, 4));
		assertEquals(false,s.isValid(8, 8, 9));
		assertEquals(true,s.isValid(0, 0, 5));
	}
	@Test
	public void testToString() {
		assertEquals(
				" | |3| |2| |6| | |\n" + //
				"9| | |3| |5| | |1|\n" + //
				" | |1|8| |6|4| | |\n" + //
				"-----+-----+-----+\n" + //
				" | |8|1| |2|9| | |\n" + //
				"7| | | | | | | |8|\n" + //
				" | |6|7| |8|2| | |\n" + //
				"-----+-----+-----+\n" + //
				" | |2|6| |9|5| | |\n" + //
				"8| | |2| |3| | |9|\n" + //
				" | |5| |1| |3| | |\n" + //
				"-----+-----+-----+\n", s.toString());
	}
	
	/*
	 * Checks to see if the verify method is working properly
	 */
	@Test
	public void testVerify() {
		assertEquals(false,s.verify());
	}
	
	@Test
	public void testSolve() {
		s.solve();
		assertEquals(
				"4|8|3|9|2|1|6|5|7|\n" + 
				"9|6|7|3|4|5|8|2|1|\n" + 
				"2|5|1|8|7|6|4|9|3|\n" + 
				"-----+-----+-----+\n" + 
				"5|4|8|1|3|2|9|7|6|\n" + 
				"7|2|9|5|6|4|1|3|8|\n" + 
				"1|3|6|7|9|8|2|4|5|\n" + 
				"-----+-----+-----+\n" + 
				"3|7|2|6|8|9|5|1|4|\n" + 
				"8|1|4|2|5|3|7|6|9|\n" + 
				"6|9|5|4|1|7|3|8|2|\n" + 
				"-----+-----+-----+\n", s.toString());
	}
}
