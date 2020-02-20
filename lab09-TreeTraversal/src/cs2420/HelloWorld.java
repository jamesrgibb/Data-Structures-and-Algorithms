package cs2420;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class HelloWorld {
	static List<String> list = new ArrayList<String>();
	 
	public static <E extends Comparable<E>> E min(E[] list) {
         E minValue = list[0];
         for(int i = 1; i < list.length; i++) {
         if(minValue.compareTo(list[i])>0) {   
             minValue = list[i];
         }
         }
         return minValue;
       		}
         public static int digitSum(int num) {
        	 int sum = 0;
        	    for(int i = num; i > 0; i = i / 10){
        	        sum += i % 10;
        	    }
        	    return sum;
        	}
         
       public static void main(String[] args) { 
		Integer[] arr = { 45, 2, 6, 15};
			System.out.print(min(arr));
			System.out.println(digitSum(432));
			} 





}