package assignment05;
/**
 * @author James Gibb & John Gibb
 * 
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class MergeSortTest extends SorterTest{

	  @Override
	  protected Sorter<Integer> uutInt() {
	    return new MergeSort<Integer>();
	  }

	  @Override
	  protected Sorter<String> uutString() {
	    return new MergeSort<String>();
	  }

	}



