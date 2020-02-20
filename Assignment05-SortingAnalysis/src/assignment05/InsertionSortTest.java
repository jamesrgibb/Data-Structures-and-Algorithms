package assignment05;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsertionSortTest extends SorterTest {
	

	  @Override
	  protected Sorter<Integer> uutInt() {
	    return new InsertionSort<Integer>();
	  }

	  @Override
	  protected Sorter<String> uutString() {
	    return new InsertionSort<String>();
	  }

	}




