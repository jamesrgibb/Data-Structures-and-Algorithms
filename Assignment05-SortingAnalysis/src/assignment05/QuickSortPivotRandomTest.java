package assignment05;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * @author James Gibb & John Gibb
 * 
 */
public class QuickSortPivotRandomTest extends SorterTest{

        @Override
          protected Sorter<Integer> uutInt() {
            return new QuickSortPivotRandom<Integer>();
          }

          @Override
          protected Sorter<String> uutString() {
            return new QuickSortPivotRandom<String>();
          }

        }