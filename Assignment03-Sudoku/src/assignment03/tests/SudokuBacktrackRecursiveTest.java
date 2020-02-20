package assignment03.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import assignment03.SudokuBacktrackRecursive;

/**
 * JUnit test cases for public methods of the {@code SudokuBacktrackRecursive}
 * class.
 * 
 * @author James Gibb & John Gibb
 *
 */
public class SudokuBacktrackRecursiveTest {
	private SudokuBacktrackRecursive s;

	@Before // executed before every test case
	public void setUp() {
		s = new SudokuBacktrackRecursive("data/sudoku1.txt");
	}

	@Test
	public void number4IsValidAtRow0Col0() {
		final int number = 4;
		assertEquals(true, s.isValidForRow(0, number));
		assertEquals(true, s.isValidForColumn(0, number));
		assertEquals(true, s.isValidForBox(0, 0, number));
	}
	@Test
	public void isValidForPosition() {
		final int num = 2;
		assertEquals(false,s.isValidForPosition(0, 0, num));
		assertEquals(false,s.isValidForPosition(2, 0,4));
		assertEquals(true, s.isValidForPosition(5,0,4));
	}
	@Test
	public void elementCheckSpace() {
		assertEquals(3,s.element(0,2));
		assertEquals(0,s.element(8,8));
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
}
