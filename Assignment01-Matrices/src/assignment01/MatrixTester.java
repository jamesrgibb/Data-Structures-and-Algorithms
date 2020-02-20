package assignment01;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Skeleton to test your matrix implementation. Some test cases are provided.
 * You need to add many more.
 * 
 * @author Swaroop Joshi
 *
 */
public class MatrixTester {
	public static void main(String[] args) {
		SimpleWriter out = new SimpleWriter1L();

		/*
		 * Test case 1: constructing a matrix from an empty array should throw an
		 * IllegalArgumentException.
		 */
		int[][] d = new int[0][0];
		boolean caughtRightException = false;
		Matrix m;
		try {
			m = new Matrix(d);
		} catch (IllegalArgumentException e) {
			out.println("Success: constructing a matrix from an empty array");
			caughtRightException = true;
		}
		if (!caughtRightException) {
			out.print("Fail: constructing a matrix from an empty array");
			caughtRightException = false; // reset for next such test case
		}

		/*
		 * Test case 2: a valid matrix multiplication
		 */
		Matrix m1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		/*
		 * Test case 3: Testing toString
		 */
		String m1StrExp = "1 2 3 \n2 5 6 \n";
		String m1StrActual = m1.toString();
		if (!m1StrExp.equals(m1StrActual)) {
			out.println("toString Error.\nGot:\n" + m1StrActual + "\nExpected:\n" + m1StrExp);
		}
		
		/*
		 * Test case 4: Testing long matrice for toString
		 */
		Matrix longM = new Matrix(new int[][] {{1, 2, 3},{2, 3, 4}, {5, 6, 6}, {6, 7, 8}});
		String longStr = "1 2 3 \n2 3 4 \n5 6 6 \n6 7 8 \n";
		String longAct = longM.toString();
		if(!longStr.equals(longAct)) {
			System.out.println("Error: toString() is not working");
		}
		else {
			System.out.println("Success: the code implemented toString() with long Matrices");
		}
		/*
		 * Test case 5: Testing  equals
		 */
		Matrix m1Expected = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		if (!m1Expected.equals(m1)) {
			out.println("Equals error: m1 and m1Expected not equal\nGot:" + m1 + "\nexpected:" + m1Expected + ")");
		}

		/*
		 * Test case 7: Testing null values with plus()
		 */
		Matrix m1Null = null;
		if (m1.equals(m1Null)) {
			System.out.println("Error: the code is not checking the empyt array properly");
		}
		else {
			System.out.println("Success: the code detected null arrays successfully with equals()");
		}
		/*
		 * Test case 8: Test null values 
		 */
		boolean catchNull = false;
		try {
			m1.plus(m1Null);
		}catch(NullPointerException e) {
			System.out.println("Success: plus() caught an empty Matrice");
			catchNull = true;
		}
		if(catchNull!=true) {
			System.out.println("Error: plus() did not catch an empty Matrice");
		}

		/*
		 * Test case 9: Testing for adding uneven arrays
		 */
		Matrix uneven = new Matrix(new int[][] { { 2, 2 }, { 1, 2 }, { 1, 2 }, { 1, 2 } });
		boolean catchPlus = false;
		try {
			m1.plus(uneven);
		} catch (IllegalArgumentException e) {
			System.out.println("Success: the plus function is catching errors");
			catchPlus = true;
		}
		if (catchPlus != true) {
			System.out.println("Error: the plus function is not catching uneven Matrices errors");
		}

		
		/*
		 * Test case 10: Testing for multiplying null values with times()
		 */
		boolean catchNullTimes = false;
		try {
		m1.plus(m1Null);
		}catch(NullPointerException e) {
		System.out.println("Success: times() caught an empty Matrice");
		catchNullTimes = true;
		}
		if(catchNullTimes!=true) {
		System.out.println("Error: times() did not catch an empty Matrice");
		}
		
		/*
		 * Test case 11: Testing for multiplying arrays with incorrect parameters
		 */
		boolean catchTimes = false;
		try {
			System.out.println(uneven.times(m1));
		} catch (IllegalArgumentException e) {
			System.out.print("Success: the  function is catching errors");
			catchTimes = true;
		}
		if (catchTimes != true) {
			System.out.println("Error: the times function is not catching uneven Matrices errors");
		}
		
		/*
		 * Test case 12: Testing times (!! Pun unintended !!)
		 */
		Matrix m2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
		Matrix actualProduct = m1.times(m2);
		Matrix expectedProduct = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } }); // we know this from math
		if (!expectedProduct.equals(actualProduct)) {
			out.println("equals error (m1 * m2 not equal to expected result)");
		}
		out.close();
	}
}
