package assignment04;
/**
 * @author James Gibb & John Gibb
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;
import assignment04.AnagramUtil;
public class AnagramUtilTest {

/*
 * Test's the sort method with a variety of inputs, with double letters and empty strings
 */
	@Test
	public void testSort() {
		String s1 = "utah";
		
		assertEquals("ahtu",AnagramUtil.sort(s1));
		assertEquals("bkoo",AnagramUtil.sort("book"));
		assertEquals("ooz",AnagramUtil.sort("zoo"));
		assertEquals("aaat",AnagramUtil.sort("ataa"));
		assertEquals(s1.isEmpty(),AnagramUtil.sort(s1).isEmpty());
		assertEquals((" ").isEmpty(),AnagramUtil.sort(" ").isEmpty());
	}
	
	
	/*
	 * Test's different insertion sort inputs with empty and unsorted strings
	 */
	@Test
	public void testInsertionSort() {
		 ArrayList<String> arr = new ArrayList<String>();
		 arr.add("u");
		 arr.add("t");
		 arr.add("a");
		 arr.add("h");
		 AnagramUtil.insertionSort(arr, (String s01, String s02)->s01.compareTo(s02));
		 ArrayList<String> control = new ArrayList<String>();
		 control.add("a");
		 control.add("h");
		 control.add("t");
		 control.add("u");
		assertEquals(control,arr);
		ArrayList<String> a = new ArrayList<String>();
		AnagramUtil.insertionSort(a, (String s01, String s02)->s01.compareTo(s02));
		assertEquals("[]",a.toString());
		
	}
	/*
	 * Test's different inputs to see if they are anagrams with empty and unsorted strings
	 */
	@Test
	public void testAreAnagrams() {
		assertFalse(AnagramUtil.areAnagrams("cat","dog"));
		assertTrue(AnagramUtil.areAnagrams("carrace","racecar"));
		assertFalse(AnagramUtil.areAnagrams("oo","o"));
		assertFalse(AnagramUtil.areAnagrams("catt","cat"));
		assertFalse(AnagramUtil.areAnagrams("cattle","catttle"));
		assertTrue(AnagramUtil.areAnagrams("react","recat"));
		assertTrue(AnagramUtil.areAnagrams("tattle","attlet"));
		assertTrue(AnagramUtil.areAnagrams("rope","pore"));
		
	}
	
	/*
	 * Test inputs with ranging lengths of unsorted and empty lists 
	 */
	@Test
	public void testLargestAnagramGroup() {
		String ret = "[carets, caters, caster, crates, reacts, recast, traces]";
		String comp = AnagramUtil.getLargestAnagramGroup("data/words.txt").toString();
		String comp2 = AnagramUtil.getLargestAnagramGroup("data/moderate_word_list.txt").toString();
		String ret2 = "[act, cat]";
		String empty = "[]";
		String emptyComp = AnagramUtil.getLargestAnagramGroup("data/empty.txt").toString();
		assertEquals(ret,comp);
		assertEquals(ret2,comp2);
		assertEquals(empty,emptyComp);
	}
	


}
