package assignment03;

/**
 * Provides constants and public methods for a Sudoku solver.
 * 
 * Any class implementing this interface must provide:
 * 
 * (1) a constructor that satisfies the following contract:
 * 
 * Description: Creates a new puzzle by reading a file.
 *
 * Parameters: String filename Relative path of the file containing the puzzle
 * in the given format
 * 
 * Requires: The file must be 9 rows of 9 numbers separated by whitespace the
 * numbers should be 1-9 or 0 representing an empty square
 * 
 * Example contents:
 * 
 * <pre>
003020600
900305001
001806400
008102900
700000008
006708200
002609500
800203009
005010300
 * </pre>
 *
 * (2) toString() method that returns a string representation of the puzzle such
 * that a non-fixed element is represented by a space, a fixed element by the
 * digit itself, each row on a line by itself, and each column separated by a
 * pipe ({@code |}). Moreover, each "box" is represented by dashes and pluses as
 * shown below.
 * 
 * The string representation for the example above is:
 * 
 * <pre>
 | |3| |2| |6| | |
9| | |3| |5| | |1|
 | |1|8| |6|4| | |
-----+-----+-----+
 | |8|1| |2|9| | |
7| | | | | | | |8|
 | |6|7| |8|2| | |
-----+-----+-----+
 | |2|6| |9|5| | |
8| | |2| |3| | |9|
 | |5| |1| |3| | |
-----+-----+-----+
 * </pre>
 * 
 * @author James Gibb & John Gibb
 *
 */
public interface Sudoku {
	/*
	 * Constants
	 */
	/**
	 * The integer value 0.
	 */
	static final int ZERO = 0;
	/**
	 * Height and width of a standard sudoku puzzle, 9
	 */
	static final int PUZZLE_HEIGHT_WIDTH = 9;
	/**
	 * Height and width of a "box" in a standard sudoku puzzle, 3
	 */
	static final int BOX_HEIGHT_WIDTH = 3;

	/**
	 * Returns the element at the given position in the puzzle. If the element is
	 * not fixed yet, returns 0.
	 * 
	 * @param row row index of the element
	 * @param col column index of the element
	 * @return (row,col)-th element if fixed, 0 otherwise
	 * 
	 * @requires [{@code row} and {@code col} are valid indices]
	 */
	public int element(int row, int col);

	/**
	 * Sets the element at the given position to {@code number}.
	 * 
	 * @param row    row index of the element
	 * @param col    column index of the element
	 * @param number value to be set at the given position
	 * 
	 * @modifies {@code this}
	 * 
	 * @requires [{@code row} and {@code col} are valid indices] and [{@code number}
	 *           is a valid digit 0-9]
	 */
	public void setElement(int row, int col, int number);

	/**
	 * Tries to solve {@code this} puzzle, returns {@code true} if it is solved,
	 * {@code false} otherwise.
	 * 
	 * @return true iff this puzzle is solved
	 * 
	 * @modifies {@code this}
	 */
	public boolean solve();

	/**
	 * Verifies whether {@code this} puzzle is solved.
	 * 
	 * @return true iff this puzzle is solved
	 */
	public boolean verify();

}
