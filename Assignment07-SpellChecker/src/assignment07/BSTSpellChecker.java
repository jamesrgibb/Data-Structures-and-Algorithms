package assignment07;

import components.list.List;
import components.list.ListOnArrays;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import java.util.ArrayList;
import java.util.Arrays;

public class BSTSpellChecker implements SpellChecker {
	public BSTSpellChecker() {
		clear();
	}
	BinarySearchTreeOfStrings bstValidList;
	List<String> misspelledList = new ListOnArrays<>();
	

	/*
	 * Loads the list of valid words from the given file.
	 * 
	 * @param filename relative or absolute path to the file from which to lead
	 * valid words
	 * 
	 * @requires file contains one word per line, i.e., anything that appears on a
	 * line , a group of letters, numbers, special characters, etc. will be
	 * considered a valid word.
	 */
	@Override
	public void loadValidWords(String filename) {
		SimpleReader file = new SimpleReader1L(filename);
		while (file.atEOS() == false) {
			String s = file.nextLine();
			bstValidList.insert(s);
		}
		file.close();
		bstValidList.toString();
		System.out.println("valid" + bstValidList.toString());
		return;
	}

	/**
	 * Returns a list of misspelled words in the given file, based on the valid
	 * words for this instance of the spell checker.
	 * 
	 * @param filename relative or absolute path to the file to be spell checked
	 * @return list of invalid words
	 **/
	@Override
	public List<String> misspelledWords(String filename) {
		SimpleReader file = new SimpleReader1L(filename);
		ArrayList<String> allWordsInFile = new ArrayList<String>();
		while(!file.atEOS()) {
				String s = file.nextLine();
				String[] allWordsInLine = s.split(" ");
				allWordsInFile.addAll(Arrays.asList(allWordsInLine));
		}
		file.close();
		for(int i =0; i<allWordsInFile.size();i++) {
			if(bstValidList.contains(allWordsInFile.get(i)) != true) {
				misspelledList.add(allWordsInFile.get(i));
			}
		}
		return misspelledList;
				}


	/**
	 * Clears this instance of the spell checker, i.e., resets the valid words set *
	 * to empty.
	 */
	@Override
	public void clear() {
		bstValidList = new BinarySearchTreeOfStrings();

	}
	

}
