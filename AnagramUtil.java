	package assignment04;
	import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import components.simplereader.*;
import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;
	/**
	* 
	* @author James Gibb & John Gibb
	*/
	public class AnagramUtil{
	 private static ArrayList<String> words = new ArrayList<String>();
	 private static ArrayList<String> words2 = new ArrayList<String>();
	 
	 private static List <String> getWords(String filename){
	    words.clear();
	    words2.clear();//makes sure the list is clear before starting
		 SimpleReader file = new SimpleReader1L(filename);
	    int count = 10000; // limit of words we are testing
	    int count2 = 0; //keeps track of how many words are in the list
		 while(file.atEOS()==false && count2<count) {
	     String s = file.nextLine();
	     words.add(s.toLowerCase()); // converts the word in list word to lowercase
	     words2.add(s.toLowerCase()); //converts the word in list words2 to lowercase
	     count2++;
	    }
	    file.close();
	  return words;
	 }
	 /* *
	 * Reports whether the two input strings are anagrams of each other .
	 *c
	 * @param s1 the first candidate string
	 * @param s2 the second candidate string
	 * @return true iff { @code s1 } and { @code s2 } are anagrams of each other
	 */
	 public static boolean areAnagrams(String s1, String s2) {
		 s1=sort(s1);//sort the letters of each anagram
		 s2=sort(s2);//sort the letters of each anagram
		 
	  if(s1.equals(s2)) { //verify they are equal
	   return true;
	  }else {
	    return false;
	  }
	 }
	 
	 /* *
	 * Returns the largest group of anagrams from the list of words in the given
	 * file , in no particular order .
	 *
	 * It is assumed that the file contains one word per line . If the file is empty ,
	 * the method returns an empty list because there are no anagrams .
	 *
	 * @param filename file to read strings from
	 * @return largest group of anagrams in the input file
	 */
	 public static List <String> getLargestAnagramGroup(String filename) {
		 getWords(filename);
	  for(int count = 0; count < words.size(); count++) {
	   String word = words.get(count);
	   words.set(count,sort(word)); //sort the letters
	  }
	  String bigWord = "";
	  int bigGroup = 0;
	  for(int c1 = 0; c1<words.size();c1++) { // increments the groups of anagrams
	   String word = words.get(c1);
	   int numAnagram = 0;
	    for(int c2 = c1+1;c2<words.size();c2++) {
	     if(areAnagrams(words.get(c1),(words.get(c2)))) { // checks whether they are relevant
	      numAnagram++; 
	    }
	  }
	  if(numAnagram>bigGroup) {
	   bigGroup = numAnagram;
	   bigWord = word;
	  }
	  }
	  ArrayList<String>li = new ArrayList<String>();
	  for(int count = 0;count<words.size();count++) {
	   if(bigWord.equals(words.get(count))) {
	    li.add(words2.get(count));
	   }
	  }
	  return li;
	  
	 }
	 
	 /* *
	 * Sorts the input list using an insertion sort and the input { @code Comparator }
	 * object .
	 *
	 * @param <T> type of the element of the list
	 * @param list input list
	 * @param order comparator used to sort elements
	 *
	 * @modifies { @code list }
	 */
	 public static <T> void insertionSort(List <T> list,
	 Comparator <?super T> order ) {
	  List<T> li =  list;
	  for(int count = 0; count<li.size()-1;count++) {
	   int c2 = count+1; 
	   while(c2<li.size()) { // insertion sort loop 
	    if(order.compare(li.get(count),li.get(c2))>0){
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
	 
	 /* *
	 * Returns a case - insensitive sorted version of the input String . For example ,
	 * if { @code str = " Utah "} , it returns { @code " ahtu "}. This sorting must be
	 * done using insertion sort .
	 *
	 * @param str string to be sorted
	 * @return sorted string
	 */
	 public static String sort(String str) {
	  ArrayList<String> arr = new ArrayList<String>(str.length()); 
	  String word = "";
	  for(int count = 0; count<str.length();count++) { 
	   String letter = Character.toString(str.charAt(count)); //put the characters of the string in the array
	   arr.add(count,letter);
	  }   
	    insertionSort(arr, (String s01, String s02)->s01.compareTo(s02)); //insertion sort loop
	   for(int c=0;c<arr.size();c++) {
	    String letter = arr.get(c); // add the characters back to a string
	    word = word+letter; 
	  }
	 return word;
	 }
	 /* *
	 * Returns the largest group of anagrams in the input list of words , in no
	 * particular order .
	 *
	 * @param input list of strings
	 * @return largest group of anagrams in { @code input }
	 3
	 */
	 public static List <String> getLargestAnagramGroup ( List<String> input ) {
		 return input;
	 }
	 /*
	  * main holds the calls to the file and the calls to find the largest anagram group  
	  */
	 public static void main(String[] args) {
	System.out.println(getLargestAnagramGroup("data/words.txt"));
	System.out.println(getLargestAnagramGroup("data/moderate_word_list.txt"));
	System.out.println(getLargestAnagramGroup("data/words_english.txt"));
	     Stopwatch timer1 = new Stopwatch1();
		 timer1.reset();
		 timer1.start();
	     areAnagrams("barry","cherry"); //timer for testing areAnagrams
		 timer1.stop();
		 System.out.println(timer1.elapsed()/1000);
		 Stopwatch timer = new Stopwatch1();
		 timer.reset();
         timer.start();
         getLargestAnagramGroup("data/words_english.txt"); //timer for testing getLargestAnagramGroup
		 timer.stop();
         System.out.println(timer.elapsed()/1000);
         Stopwatch timer2 = new Stopwatch1();
		 timer2.reset();
         timer2.start();        
         insertionSort(getWords("data/words_english.txt"),(String s01, String s02)->s01.compareTo(s02)); // timer for testing insertionSort
		 timer2.stop();
         System.out.println(timer2.elapsed()/1000);

	 }
	 }


