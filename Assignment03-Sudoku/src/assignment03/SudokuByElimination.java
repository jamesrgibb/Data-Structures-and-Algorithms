package assignment03;

import java.util.ArrayList;





import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stopwatch.Stopwatch1;

/**
 * Iterative implementation of the sudoku solver.
 * 
 * @author James Gibb & John Gibb
 *
 */
public class SudokuByElimination implements Sudoku {
	/*
	 * Private fields and methods if required
	 */
	/**
	 * Each element is represented as a set of intgers {1,..,9}
	 */
	private static int guesses;
	private static Stopwatch1 timer;
	private ArrayList<Set<Integer>> data;
	private Set<Integer> values;
	private int[] template;
	private int[] copy;

	/**
	 * Creates a new puzzle by reading a file.
	 *
	 * @param filename Relative path of the file containing the puzzle in the given
	 *                 format
	 * @requires The file must be 9 rows of 9 numbers separated by whitespace the
	 *           numbers should be 1-9 or 0 representing an empty square
	 */
	public SudokuByElimination(String filename) {
		SimpleReader file = new SimpleReader1L(filename);
		this.template = new int[81]; // create the array to hold the values of the sets
		for (int count = 0; count < 81; count++) {// iterate through the set
			int num = (int) file.read() - '0';
			if (num > -1) { // if the char doesn't read as newline
				this.template[count] = num;
			} else { // if char is new line go to the next line
				count = count - 1;
			}
		}
		this.data = new ArrayList<Set<Integer>>();
		for (int i = 0; i < 81; i++) { // create the sets to hold the 81 values
			values = new Set1L<Integer>();
			for (int digit = 1; digit <= 9; digit++) {
				values.add(digit); // add the numbers to each spot
			}
			this.data.add(values); // add the values to data
		}

		file.close();
		copy = template;
		guesses = 0;
	}

	/**
	 * Eliminates the {@code element} from {@code row}, {@code col}, and Box
	 * containing position (row,col).
	 * 
	 * @param row     row index
	 * @param col     column index
	 * @param element to be eliminated
	 * 
	 * @requires [{@code row} and {@code col} are valid indices]
	 */
	public void eliminate(int row, int col, int element) {
		assert 0 <= row && row < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
		assert 0 <= col && col < PUZZLE_HEIGHT_WIDTH : "Violation of valid col index";
		int i = row;
		int j = col;
		Set<Integer> original = new Set1L<Integer>();
		original = data.get((row*9)+col);
		for (col = 0; col < 9; col++) { // iterate through the rows to make sure there are no duplicates
			Set<Integer> nums = new Set1L<Integer>();
			nums = data.get((i*9)+col);
			if (nums.contains(element)) {
			nums.remove(element);
			data.add(nums);
// if there are duplicates remove them
		}
		}
		for (row = 0; row < 9; row++) { // iterate through the cols to make sure there are no duplicates
			Set<Integer> nums = new Set1L<Integer>();
			nums = data.get((row*9)+j);
			if (nums.contains(element)) {
			nums.remove(element);
			data.add(nums);
			}// if there are duplicates remove them.
		}
		  int boxStartRow = i;
		  int boxStartCol = j;
		  while (boxStartRow % 3 != 0)  //check the validity of the box start rows
		   boxStartRow = boxStartRow - 1;
		  while (boxStartCol % 3 != 0) 
		   boxStartCol = boxStartCol - 1;
		   int countRow;
		   int countCol;

		for (countRow = boxStartRow; (countRow < boxStartRow + 3 && countRow<9); countRow++) { // iterate through the boxes to make sure there are no duplicates
			for (countCol = boxStartCol; (countCol < boxStartCol + 3 && countCol<9); countCol++) {
				Set<Integer> nums = new Set1L<Integer>();
				nums = data.get((countRow*9)+countCol);
				if (nums.contains(element)) {
					nums.remove(element);
					data.add(nums);
					
				}
			}
			
		}
		original.add(element(i, j));
	}

	@Override
	public int element(int row, int col) {
		assert 0 <= row && row < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
		assert 0 <= col && col < PUZZLE_HEIGHT_WIDTH : "Violation of valid col index";
		int value = template[((row * 9) + col)];
		return value; // return calculated value from template
	}

	@Override
	public void setElement(int row, int col, int number) {
		assert 0 <= row && row < PUZZLE_HEIGHT_WIDTH : "Violation of valid row index";
		assert 0 <= col && col < PUZZLE_HEIGHT_WIDTH : "Violation of valid col index";
		assert 0 <= number && number <= PUZZLE_HEIGHT_WIDTH : "Violation of valid number";
		// set the element equal to the number passed in

		if(copy[(row*9)+col]==0 ) { // verifies that the number is not zero
			template[(row*9)+col] = number;
		}
	}

	/*
	 * IsValid makes sure that element can be placed in the given spot
	 * 
	 */
	public boolean isValid(int row, int col, int element) {
		int i = row;
		int j = col;
		for (row = 0; row < 9; row++) {
			
			// iterate through the rows to make sure there are no duplicates
			if (element == element(row, j)) {
			return false; // if there are duplicates return false
			}
		}
		for (col = 0; col < 9; col++) { // iterate through the cols to make sure there are no duplicates
			
			if (element == element(i, col) ) {
				return false; // if there are duplicates return false.
				}
			}
		  int boxStartRow = i;
		  int boxStartCol = j;
		  while (boxStartRow % 3 != 0) { //check the validity of the box start rows
			  boxStartRow = boxStartRow - 1;}
		  while (boxStartCol % 3 != 0)
		   boxStartCol = boxStartCol - 1;
		  int countRow;
		  int countCol;
		for (countRow = boxStartRow; (countRow < boxStartRow + 3 && countRow<9); countRow++) { // iterate through the boxes to make sure there															// are no duplicates
			for (countCol = boxStartCol; (countCol < boxStartCol + 3 && countCol < 9); countCol++) {
			
				if (element == element(countRow,countCol)) {
					return false; // return false for duplicates
				}
			}

		}
		return true; //else return true
	}
	
	/*
	 * checks the verify method if false, iterates over the puzzle until all values are put into place 
	 * 
	 */
	@Override
	public boolean solve() {
		while(!verify()) {
		for(int row = 0;row<9;row++) { // iterates over the values for imported numbers and deletes other numbers
			for(int col = 0; col<9;col++) {
				if(element(row,col)!=0) {
					eliminate(row,col,element(row,col)); //eliminates duplicates in the corresponding row, box, and column
					
				}
			}
		}
			for(int row = 0;row<9;row++) { // loops over the new numbers and if the condition is passed sets the element
				for(int col = 0; col<9;col++) {
					Set<Integer> num = new Set1L<Integer>();
					num = data.get((row*9)+col);
					for(int count = 1; count<10;count++) {
					guesses++;
						if(num.contains(count)) {
						if(isValid(row,col,count)&& num.size()==1) { ////eliminates duplicates in the corresponding row, box, and column
							eliminate(row,col,count);
							setElement(row,col,count);
							
						}
				}
				}
		}
			}
		}
		if(verify()) {
			return true;
		}
	return false;
	}
	/*
	 * verifies that the puzzle is correct and passes a boolean value
	 */
	@Override
	public boolean verify() {
		  for (int x = 0; x < 9; x++) {
			   for (int y = 0; y < 9; y++) {
			    if (template[((x * 9) + y)] == 0) {
			    	return false;
			     }
			    	
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
	 * Iterates through the array and then converts the array to a String 
	 */
	@Override
	public String toString() {
		String puzzle = "";
		for (int i = 0; i < template.length; i++) {
			if (template[i] == 0) {
				puzzle += " |";
			} else {
				puzzle += template[i] + "|";
			}
			if ((i + 1) % 9 == 0 && i > 1) {
				puzzle += "\n";
			}
			if ((i + 1) % 27 == 0 && i > 1) {
				puzzle += "-----+-----+-----+\n";
			}
		}
		return puzzle;
	} // TODO: placeholder, remove comments when done

	/**
	 * Main method, similar to the one in the other class.
	 * 
	 * @param args command line arguments, not used
	 */
	public static void main(String[] args) {
		 timer = new Stopwatch1();
		 timer.reset();
		 timer.start();
		SimpleWriter out = new SimpleWriter1L();
		Sudoku s = new SudokuByElimination("data/sudoku4.txt");
		out.println("===== Sudoku puzzle =====\n");

		out.println(s.toString());
		out.println("\n\n===== Solving sudoku =====\n\n");
		s.solve();
		out.println(s);
		out.close();
		  timer.stop();
		  System.out.println(timer.elapsed());
		  System.out.println(guesses);
	}
}