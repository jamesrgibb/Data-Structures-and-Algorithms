package assignment05;

/**
 * @author James Gibb & John Gibb
 * 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuickSortPivotM3Test extends SorterTest{

        @Override
          protected Sorter<Integer> uutInt() {
            return new QuickSortPivotM3<Integer>();
          }

          @Override
          protected Sorter<String> uutString() {
            return new QuickSortPivotM3<String>();
          }


}