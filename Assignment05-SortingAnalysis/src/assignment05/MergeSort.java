package assignment05;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the merge sort using the {@code Sorter} interface.
 * 
 * This class must implement the {@code setThreshold, threshold} methods.
 * 
 * @author James Gibb & John Gibb
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class MergeSort<T extends Comparable<? super T>>
    extends AbstractSorter<T> {
List<T>list;
private int threshold;
private int index;
 public MergeSort() {
	 list = new ArrayList<T>();
	 this.name = "Merge Sort";
	    this.complexity = ComplexityClass.NLOGN;
	   threshold = 3;
	   index = 0;
 }

  @Override
  public void sort(List<T> list) {
    assert list != null : "Violation of: list is not null";
    setThreshold(6);
    mergeSort(list, 0,list.size()); 
  }
  
  public void mergeSort(List<T> list, int start,int size) {
	int mid = (start + size) / 2;
	if(SortUtils.isSorted(list) && size - start > threshold()) {
	mergeSort(list, start, mid);
	mergeSort(list, mid+1,size);
	merge(list, start, mid, size);
	}else 
		insertionMerge(list, start, size);
	}
  
  
  
  public void merge(List<T> list, int startOfLeft, int startOfRight, int endOfRight) {
	  List<T> left = list.subList(startOfLeft, startOfRight);
	  List<T> right = list.subList(startOfRight, endOfRight);
	  List<T> sorted = new ArrayList<T>();
	  int currL = 0;
	  int currR = 0;
	  while(currL<left.size() && currR<right.size()) { 
		  if(left.get(currL).compareTo(right.get(currR))<0) {
			  sorted.add(left.get(currL));
			  currL++;	  
		  }else{
			  sorted.add(right.get(currR));
			  currR++;
		  }
	  }  
				  while(currR<right.size()) {
				  sorted.add(right.get(currR));
				  currR++;
			  }
				  while(currL<left.size()) {
				  sorted.add(left.get(currL));
				  currL++;
			  
		  }
		  for(int i=startOfLeft;i<sorted.size();i++) {
			  list.set(i, sorted.get(i));
		  }

		  } 
  @Override
  public int threshold() {
	  return threshold;
  }
  @Override
  public void setThreshold(int threshold1) {
	  threshold = threshold1; 
  }
  public void insertionMerge(List<T> list, int start, int end){
	    assert list != null : "Violation of: list is not null";
	    List<T> li =  list;
		  for(int count = start; count<end;count++) {
		   int c2 = count+1; 
		   while(c2<end) { // insertion sort loop 
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
		  list = li;
}

  }