package lab10;

import components.map.Map;
import components.map.MapOnHashTable;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;


/**
 * Simple pizza order manager.
 * 
 * @author James Gibb
 * 
 */
public final class PizzaOrderManager {

  /**
   * Private constructor so this utility class cannot be instantiated.
   */
  private PizzaOrderManager() {
  }
  
  /**
   * Inputs a "menu" from the given file and returns it as a {@code Map}.
   * 
   * @param fileName the name of the input file
   * @return the word -> price map
   * @requires the given file is in proper format: each line contains two "words"
   *           separated by a comma. The first "word" is a size or a topping, the
   *           second "word" is its price in cents. The first "word" can contain
   *           any characters, except a comma. The second "word" can only contain
   *           digits.
   * 
   */
  public static Map<String, Integer> getPriceMap(String fileName) {
	  //create new hash map
	  MapOnHashTable<String, Integer> map = new MapOnHashTable<>();
	  //initialize the simple reader
	  SimpleReader file = new SimpleReader1L(fileName);
		while (file.atEOS() == false) {
			//load the next line
			String s = file.nextLine();
			//split the strings
			String [] sa = s.split(",",2);
			String key = sa[0];
			String val = sa[1];
			// change val to an int
			int num = Integer.parseInt(val);
			//add to the map
			map.add(key,num);
		}
		//close file
		file.close();
    return map; 
  }

  /**
   * Computes and returns the price of the given pizza order.
   * 
   * @param input           the order
   * @param sizePriceMap    the size -> price map
   * @param toppingPriceMap the topping -> price map
   * @return the total price (in cents)
   * @requires input is in proper format: a list of "words" separated by commas.
   *           The first word is a size and the rest of the words (0 or more) are
   *           toppings.
   * 
   */
  public static int getOrderPrice(String input,
      Map<String, Integer> sizePriceMap,
      Map<String, Integer> toppingPriceMap) {
	  // Initialize the cost
	 int cost = 0;
	 //split the array
	 String[] inputA = input.split(",");
	 //Initialize the size and add to cost 
	 int size = sizePriceMap.value(inputA[0]);
	 cost+=size;
	 for(int i = 1;i<inputA.length;i++) {
		 //loop through all the toppings and add their cost to cost
		 int topping = toppingPriceMap.value(inputA[i]);
		 cost += topping;
	 }
	 //return cost
    return cost; 
  }

  /**
   * Returns the given integer amount in cents to a dollar and cent format.
   * 
   * For example, if price = 1099, it returns the String "$10.99"
   * 
   * @param price amount in cents
   * @requires price >= 0
   * @return formatted amount
   */
  public static String formatPrice(int price) {
	  // change price to a string 
		String p = Integer.toString(price);
		System.out.println(p.length());
		String dollar ="";
		//check to see how many digits there are 
		if(p.length() == 4) {
			//append string to be formatted as currency
			dollar += "$"+p.substring(0, 2) +"."+p.substring(2,4);
		}
		if(p.length() == 3) {
			//append string to be formatted as currency
			dollar += "$"+ p.substring(0,1)+"."+p.substring(1, 3); 
		}
		if(p.length() == 2) {
			//append string to be formatted as currency
			dollar += "$0."+ p; 
		}
		if(p.length() == 1) {
			//append string to be formatted as currency
			dollar += "$0.0"+ p; 
		}
    return dollar; // TODO implement this method
  }

}
