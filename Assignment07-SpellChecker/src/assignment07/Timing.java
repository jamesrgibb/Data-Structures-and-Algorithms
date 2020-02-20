package assignment07;

import java.util.ArrayList;
import java.util.Random;

import components.bintree.BalancedBST1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;

public class Timing {
	static ArrayList<String> loadedWordsForRand = new ArrayList<String>();
	static ArrayList<String> loadedWordsForOrdered = new ArrayList<String>();

	public static void loadWords(String filename) {
		// read in file
		SimpleReader file = new SimpleReader1L(filename);
		// while you are not at the end of the stream add the words from the file to
		// loadedWords
		while (!file.atEOS()) {
			loadedWordsForRand.add(file.nextLine());
		}
		file.close();
		loadedWordsForOrdered = loadedWordsForRand;
	}

	public static void main(String[] args) {
		BinarySearchTreeOfStrings bst1 = new BinarySearchTreeOfStrings();
		BinarySearchTreeOfStrings bst2 = new BinarySearchTreeOfStrings();
		BalancedBST1<String> balancedBST = new BalancedBST1<String>();
		BalancedBST1<String> balancedOrderedBST = new BalancedBST1<String>();

		Stopwatch timer1 = new Stopwatch1();
		Stopwatch timer2 = new Stopwatch1();
		Stopwatch timer3 = new Stopwatch1();
		Stopwatch timer4 = new Stopwatch1();
		Stopwatch timer5 = new Stopwatch1();

		Random rand = new Random();
		loadWords("data/words_english.txt");

		int lengthOfBST = 22500;

		ArrayList<String> listOfRands = new ArrayList<String>();
		// insert into bst in random order
		for (int i = 0; i < lengthOfBST; i++) {
			int randInt = rand.nextInt(loadedWordsForRand.size());
			// get random word from loadedWords
			listOfRands.add(loadedWordsForRand.get(randInt));
			loadedWordsForRand.remove(randInt);
		}

		timer1.start();
		for (int j = 0; j < listOfRands.size(); j++) {
			balancedBST.insert(listOfRands.get(j));
			listOfRands.remove(j);
		}
		timer1.stop();

		timer4.start();
		for (int j = 0; j < listOfRands.size(); j++) {
			int randInt = rand.nextInt(listOfRands.size());
			balancedBST.contains(listOfRands.get(randInt));
			listOfRands.remove(randInt);
		}
		timer4.stop();

		ArrayList<String> listOfOrderedWords = new ArrayList<String>();

		for (int i = 0; i < lengthOfBST; i++) {
			String index = loadedWordsForOrdered.get(i);
			listOfOrderedWords.add(index);
		}
		// insert into bst in sorted order
		timer2.start();
		for (int i = 0; i < listOfOrderedWords.size(); i++) {
			balancedOrderedBST.insert(loadedWordsForOrdered.get(i));
		}
		timer2.stop();
		
		timer3.start();
		for (int i = 0; i < listOfOrderedWords.size(); i++) {
			balancedOrderedBST.contains(loadedWordsForOrdered.get(i));
		}
		timer3.stop();
		System.out.println(lengthOfBST);
		System.out.println("random insert," + timer1.elapsed());
		System.out.println("ordered insert," + timer2.elapsed());
		System.out.println("random contains," + timer4.elapsed());
		System.out.println("ordered contains," + timer3.elapsed());

	}

}