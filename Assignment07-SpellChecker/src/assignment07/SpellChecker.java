package assignment07;

import components.list.List;

/**
 * Public interface for a simple spell checker.
 * 
 * @author Swaroop Joshi
 *
 */
public interface SpellChecker {
  /**
   * Loads the list of valid words from the given file.
   * 
   * @param filename relative or absolute path to the file from which to lead
   *                 valid words
   * 
   * @requires file contains one word per line, i.e., anything that appears on a
   *           line, a group of letters, numbers, special characters, etc. will be
   *           considered a valid word.
   */
  void loadValidWords(String filename);

  /**
   * Returns a list of misspelled words in the given file, based on the valid
   * words for this instance of the spell checker.
   * 
   * @param filename relative or absolute path to the file to be spell checked
   * @return list of invalid words
   */
  List<String> misspelledWords(String filename);

  /**
   * Clears this instance of the spell checker, i.e., resets the valid words set
   * to empty.
   */
  void clear();
}
