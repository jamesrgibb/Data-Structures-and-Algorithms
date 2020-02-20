package assignment03;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;
/**
 * Brute force, recursive implementation of the sudoku solver.
 *
 * @author James Gibb / John Gibb
 *
 */
public class SudokuBacktrackRecursive implements Sudoku {
 /*
  * Private fields and helper methods if required---
  */
 // TODO: How to represent the puzzle? 1-d or 2-d array?
 /**
  * Creates a new puzzle by reading a file.
  *
  * @param filename Relative path of the file containing the puzzle in the given
  *                 format
  * @requires The file must be 9 rows of 9 numbers separated by whitespace the
  *           numbers should be 1-9 or 0 representing an empty square
  */
	private static int guesses;
 private static Stopwatch1 timer;
 int values[] = new int[81];
 int temp[] = new int[81];
 int timesThrough = 0;
 int check = 1;
 public SudokuBacktrackRecursive(String filename) {
  SimpleReader file = new SimpleReader1L(filename);
  for (int i = 0; i < values.length; i++) {
   if (file.peek() == '\n') {
    file.nextLine();
   }
   String line = Character.toString(file.read());
   values[i] = Integer.parseInt(line);
  }
  file.close();
  temp = values;
  guesses = 0;
 }
 /**
  * Determines whether the given {@code number} can be placed in the given
  * {@code row} without violating the rules of sudoku.
  *
  * @param row    which row to see if the number can go into
  * @param number the number of interest
  *
  * @requires [{@code row} is a valid row index] and [{@code number} is a valid
  *           digit]
  *
  * @return true iff it is possible to place that number in the row without
  *         violating the rule of 1 unique number per row.
  */
 public boolean isValidForRow(int row, int number) {
  assert 0 < number && number <= PUZZLE_HEIGHT_WIDTH : "Violation of valid cadidate";
  assert 0 <= row && row < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
  int num = 0;
  for (int col = 0; col < 9; col++) {
   if (number == element(row, col) && timesThrough != 0)
    num++; // make sure it goes through more than once
   if (number == element(row, col) && timesThrough != 0 && num > 0)
    return false;
   if (number == element(row, col) && timesThrough == 0)
    return false;
  }
  if (num > 1)
   return false;
  else
   return true;
 }
 /**
  * Determines whether the given {@code number} can be placed in the given column
  * without violating the rules of sudoku.
  *
  * @param col    which column to see if the number can go into
  * @param number the number of interest
  *
  * @requires [{@code col} is a valid column index] and [{@code number} is a
  *           valid digit]
  *
  * @return true iff it is possible to place that number in the column without
  *         violating the rule of 1 unique number per row.
  */
 public boolean isValidForColumn(int col, int number) {
  assert 0 <= col && col < PUZZLE_HEIGHT_WIDTH : "Violation of valid column index";
  assert 0 < number && number <= PUZZLE_HEIGHT_WIDTH : "Violation of valid cadidate";
  int num = 0;
  for (int row = 0; row < 9; row++) {
   if (number == element(row, col) && timesThrough != 0)
    num++; //make sure it goes through more than once
   if (number == element(row, col) && timesThrough != 0 && num > 0)
    return false;
   if (number == element(row, col) && timesThrough == 0)
    return false;
  }
  if (num > 1)
   return false;
  else
   return true;
 }
 /**
  * Determines whether the given {@code number} can be placed in "box" starting
  * at the given position without violating the rules of sudoku.
  *
  * The positions marked # are the valid positions of start of a box. They are
  * (0,0), (0,3), (0, 6), (3,0), (3,3), (3,6), (6,0), (6,3), (6,6).
  *
  * <pre>
  * #00|#00|#00|
  * 000|000|000|
  * 000|000|000|
  * ---+---+---+
  * #00|#00|#00|
  * 000|000|000|
  * 000|000|000|
  * ---+---+---+
  * #00|#00|#00|
  * 000|000|000|
  * 000|000|000|
  * ---+---+---+
  * </pre>
  *
  * @param boxStartRow row index at which the box of interest starts
  * @param boxStartCol column index at which the box of interest starts
  * @param number      the number of interest
  *
  * @requires [{@code boxStartRow} and {@code boxStartCol} are valid box start
  *           indices] and [{@code number} is a valid digit]
  *
  * @return true iff it is possible to place that number in the column without
  *         violating the rule of 1 unique number per row.
  */
 public boolean isValidForBox(int boxStartRow, int boxStartCol, int number) {
  assert 0 < number && number <= PUZZLE_HEIGHT_WIDTH : "Violation of valid cadidate";
  assert boxStartRow % BOX_HEIGHT_WIDTH == 0 : "Violation of valid boxStartRow";
  assert boxStartCol % BOX_HEIGHT_WIDTH == 0 : "Violation of valid boxStartCol";
  while (boxStartRow % 3 != 0)
   boxStartRow = boxStartRow - 1;
  while (boxStartCol % 3 != 0)
   boxStartCol = boxStartCol - 1;
  int countCol;
  int countRow;
  int num = 0;
  for (countCol = boxStartCol; countCol < boxStartCol + 3; countCol++) {
   for (countRow = boxStartRow; countRow < boxStartRow + 3; countRow++) {
    if (number == element(countRow, countCol) && timesThrough != 0)
     num++; //make sure it goes through more than once
    if (number == element(countRow, countCol) && timesThrough != 0 && num > 0)
     return false;
    if (number == element(countRow, countCol) && timesThrough == 0)
     return false;
   }
  }
  return true;
 }
 /**
  * Determines whether the given {@code number} can be placed in the given
  * position without violating the rules of sudoku.
  *
  * @param row    which row to see if the number can go into
  * @param col    which column to see if the number can go into
  * @param number the number of interest
  *
  * @requires [{@code row} is a valid row index] and [{@code col} is a valid
  *           column index] and [{@code number} is a valid digit]
  *
  * @return true iff it is possible to place that number in the column without
  *         violating the rule of 1 unique number per row.
  */
 public boolean isValidForPosition(int row, int col, int number) {
  assert 0 <= row && row < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
  assert 0 <= col && col < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
  assert 0 < number && number <= PUZZLE_HEIGHT_WIDTH : "Violation of valid cadidate";

  int boxStartRow = row;
  int boxStartCol = col;
  while (boxStartRow % 3 != 0) //check the validity of the box start rows
   boxStartRow = boxStartRow - 1;
  while (boxStartCol % 3 != 0)
   boxStartCol = boxStartCol - 1;
//  }
  if (isValidForRow(row, number) == true && isValidForColumn(col, number) == true //make sure it works for all three criteria
    && isValidForBox(boxStartRow, boxStartCol, number) == true) {
   return true;
  }else
   return false;
 }

 /*
  * Methods from sudoku interface implemented here
  * Returns the value of the element
  */
 @Override
 public int element(int i, int j) {
  assert 0 <= i && i < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
  assert 0 <= j && j < PUZZLE_HEIGHT_WIDTH : "Violation of valid column index";
  
   return values[((i * 9) + j)];
  }
  
 /*
  * Sets the element with the given number
  */
 @Override
 public void setElement(int i, int j, int number) {
  assert 0 <= i && i < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
  assert 0 <= j && j < PUZZLE_HEIGHT_WIDTH : "Violation of valid column index";
  assert 0 <= number && number <= PUZZLE_HEIGHT_WIDTH : "Violation of valid cadidate";
  if (isValidForPosition(i, j, number) == true) {
   values[(i * 9) + j] = number;
  }
 }
  
 /*
  * Clears the element if backtracking needs to happen
  */
 public void clearElement(int i, int j) {
  values[(i * 9) + j] = 0;
 }
 
 /*
  * Solves the puzzle with recursion, and backtracks until the puzzle is complete
  */
 @Override
 public boolean solve() {
  for (int x = 0; x < 9; x++) {
   for (int y = 0; y < 9; y++) {
    if (element(x, y) == 0) {
      for (int count = 1;count < 10; count++) {
    	  guesses++;
    	  if (isValidForPosition(x, y, count)) {
        values[(x * 9) + y] = count;
        if(solve()) {
         return true;
        }else {
         values[(x * 9) + y] = ZERO;
        }
       }
       
     
    
       } 
    return false;
     }
   }
  }
  if(verify()) {
   return true;
  }else {
  solve();
  }
  return false;
 }
/*
 * Verifies the puzzle is complete
 */
 @Override
 public boolean verify() {
  for (int x = 0; x < 9; x++) {
   for (int y = 0; y < 9; y++) {
    if (values[((x * 9) + y)] == 0)
     return false;
   }
  }
  //checks the boxes and columns to make sure there are no duplicates
  for (int boxStartRow = 0; boxStartRow < 9; boxStartRow += 3) {
   for (int boxStartCol = 0; boxStartCol < 9; boxStartCol += 3) {
    int countCol;
    int countRow;
    for (int num = 1; num < 10; num++) {
     int howMany = 0;
     for (countCol = boxStartCol; countCol < boxStartCol + 3; countCol++) {
      for (countRow = boxStartRow; countRow < boxStartRow + 3; countRow++) {
       if (num == element(countRow, countCol))
        howMany++;
      }
     }
     if (howMany > 1)
      return false;
    }
   }
  }// ensures that it iterates through columns
  for (int number = 1; number < 10; number++) {
   int row;
   for (int col = 0; col < 9; col++) {
    int num = 0;
    for (row = 0; row < 9; row++) {
     if (number == element(row, col)) {
      num++;
     }
    }
    if (num > 1) {
     return false;
    }
   }
  } // ensures that it iterates through the row
  for (int number = 1; number < 10; number++) {
   int col;
   for (int row = 0; row < 9; row++) {
    int num = 0;
    for (col = 0; col < 9; col++) {
     if (number == element(row, col)) {
      num++;
     }
    }
    if (num > 1) {
     return false;
    }
   }
  }
  return true;
 }
 /*
  * Methods inherited from Object
  */
 @Override
 public String toString() {
  String puzzle = "";
  for (int i = 0; i < values.length; i++) {
   if (values[i] == 0) {
    puzzle += " |";
   } else {
    puzzle += values[i] + "|";
   }
   if ((i + 1) % 9 == 0 && i > 1) {
    puzzle += "\n";
   }
   if ((i + 1) % 27 == 0 && i > 1) {
    puzzle += "-----+-----+-----+\n";
   }
  }
  return puzzle;
 }
 /**
  * Main method. Produces the following output. You can modify it to debug and
  * refine your implementation.
  *
  * <pre>
 ===== Sudoku puzzle =====
 
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
 
 
 
 ===== Solving sudoku =====
 
 
 4|8|3|9|2|1|6|5|7|
 9|6|7|3|4|5|8|2|1|
 2|5|1|8|7|6|4|9|3|
 -----+-----+-----+
 5|4|8|1|3|2|9|7|6|
 7|2|9|5|6|4|1|3|8|
 1|3|6|7|9|8|2|4|5|
 -----+-----+-----+
 3|7|2|6|8|9|5|1|4|
 8|1|4|2|5|3|7|6|9|
 6|9|5|4|1|7|3|8|2|
 -----+-----+-----+
  * </pre>
  *
  * @param args command line arguments, not used
  */
 public static void main(String[] args) {
  
	 timer = new Stopwatch1();
	 timer.reset();
	 timer.start();
  SimpleWriter out = new SimpleWriter1L();
  Sudoku s = new SudokuBacktrackRecursive("data/sudoku1.txt");
  Sudoku s2 = new SudokuBacktrackRecursive("data/sudoku2.txt");
  out.println("===== Sudoku puzzle =====\n");
  out.println(s.toString());
  out.println("\n\n===== Solving sudoku =====\n\n");
  //s.solve();
  s2.solve();
  //out.println(s);
  out.println(s2);
  out.close();
  timer.stop();
  System.out.println(timer.elapsed());
  System.out.println(guesses);
 }
}


