package assignment05;

/**
 * @author James Gibb & John Gibb
 * 
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuickSortPivotFirstTest extends SorterTest{

          @Override
          protected Sorter<Integer> uutInt() {
            return new QuickSortPivotFirst<Integer>();
          }

          @Override
          protected Sorter<String> uutString() {
            return new QuickSortPivotFirst<String>();
          }


}
