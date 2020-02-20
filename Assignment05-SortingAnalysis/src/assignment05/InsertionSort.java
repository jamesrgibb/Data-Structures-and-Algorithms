package assignment05;

import java.util.List;



/**
 * Implements insertion sort using the {@code Sorter} interface.
 * 
 * This class does not implement the {@code setThreshold} method.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class InsertionSort<T extends Comparable<? super T>>
    extends AbstractSorter<T> {

  // TODO add constructor
	public InsertionSort() {
		this.name = "InsertionSort";
	    this.complexity = ComplexityClass.NSQUARED;
	    
	}
  @Override
  public void sort(List<T> list) {
    assert list != null : "Violation of: list is not null";
    List<T> li =  list;
	  for(int count = 0; count<li.size()-1;count++) {
	   int c2 = count+1; 
	   while(c2<li.size()) { // insertion sort loop 
	    if(li.get(count).compareTo(li.get(c2))>0){
	     T num = li.get(c2); //get the index for new value
	     for(int i = c2-1; i >= count; i--) { 
	      li.set(i+1, li.get(i)); // set the index for the new value
	     }
	    li.set(count, num); // insert into the new value
	    }
	    c2++;
	  }
	  }
	  
  }
}
