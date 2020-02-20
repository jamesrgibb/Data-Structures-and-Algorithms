package assignment01;

import java.util.Arrays;

/**
 * Represents a 2-d integer matrix as a 2-d int array.
 * 
 * @author Swaroop Joshi
 *
 */
public class Matrix {
	/**
	 * Holds the number of rows of this matrix
	 */
	private int numRows;
	/**
	 * Holds the number of columns of this matrix
	 */
	private int numColumns;
	/**
	 * Holds the actual data of this matrix
	 */
	private int data[][];

	/**
	 * Constructor from a 2D array -- automatically determines the dimensions.
	 * 
	 * @param d 2D array to construct the matrix
	 * @throws IllegalArgumentException if {@code d} is empty or null
	 */
	public Matrix(int d[][]) throws IllegalArgumentException {
		if (d == null || d.length == 0) {
			throw new IllegalArgumentException();
		}
		this.numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		this.numColumns = d[0].length; // d[0] is the first 1D array

		// create a new matrix to hold the data
		this.data = new int[this.numRows][this.numColumns];
		// copy the data over
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				this.data[i][j] = d[i][j];
			}
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false; // Why?
		}
		if (!(o instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		} else {
			String m2 = "";
			Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
			for (int i = 0; i < m.numRows; i++) {
				for (int j = 0; j < m.numColumns; j++) {
					if (data[i][j] != this.data[i][j]) {
						return false;
					}
					m2 += this.data[i][j] + " ";
				}
				m2 += "\n";
			}
			return true;
		} // placeholder
	}

	@Override
	public String toString() {
		int i = 0;
		int j = 0;
		String matrix = "";
		for (i = 0; i < numRows; i++) {
			for (j = 0; j < numColumns; j++) {
				matrix += data[i][j] + " ";
			}
			matrix += "\n";
		}
		return matrix;
	}

	/**
	 * Multiplies {@code this} by {@code m} and returns the result as a new
	 * {@code Matrix}. If {@code m} does not have appropriate dimensions to multiply
	 * to {@code this}, it throws an {@code IllegalArgumentException}.
	 * 
	 * @param m matrix to multiply {@code this} with
	 * @return result of the product
	 * 
	 * @throws IllegalArgumentException if dimensions of the matrices are not
	 *                                  compatible
	 */
	public Matrix times(Matrix m) throws IllegalArgumentException {
		Matrix ret = new Matrix(data);
		ret.numRows = this.numRows;
		ret.numColumns = m.numColumns;
		if (m.numColumns != this.numRows) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < this.numRows - 1; i++) {
			for (int j = 0; j < this.numColumns - 1; j++) {

				ret.data[i][j] = m.data[i][j] * this.data[i][j];

			}

		}
		return ret; // placeholder; don't use explicit nulls otherwise
	}

	/**
	 * Adds {@code m} to {@code this} and returns the result as a new
	 * {@code Matrix}. If {@code m} does not have the same dimensions as
	 * {@code this}, it throws an {@code IllegalArgumentException}.
	 * 
	 * @param m matrix to add to {@code this} with
	 * @return result of the addition
	 *
	 * @throws IllegalArgumentException if dimensions of the matrices are not
	 *                                  compatible
	 */
	public Matrix plus(Matrix m) throws IllegalArgumentException {
		Matrix sum = new Matrix(data);
		if (this.numColumns != m.numColumns || this.numRows != m.numRows) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				sum.data[i][j] = this.data[i][j] + m.data[i][j];
			}
		}
		return sum; // placeholder; don't use explicit nulls otherwise
	}
}
