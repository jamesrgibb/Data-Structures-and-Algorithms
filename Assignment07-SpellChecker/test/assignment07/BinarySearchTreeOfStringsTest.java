package assignment07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinarySearchTreeOfStringsTest {

  private BinarySearchTreeOfStrings uutFromArgs(String... args) {
    BinarySearchTreeOfStrings t = new BinarySearchTreeOfStrings();
    for (String s : args)
      t.insert(s);
    return t;
  }

  @Test
  public void testConstructorString() {
    BinarySearchTreeOfStrings t = new BinarySearchTreeOfStrings();
    assertEquals("[]", t.toString());
    assertEquals(0, t.size());
  }

  @Test
  public void insertIntoEmptyTree() {
    BinarySearchTreeOfStrings t = new BinarySearchTreeOfStrings();
    t.insert("red");
    assertEquals("[red]", t.toString());
    assertEquals(1, t.size());
  }

  @Test
  public void insertSingleNodeLeft() {
    BinarySearchTreeOfStrings t = uutFromArgs("red");
    t.insert("blue");
    assertEquals("[blue,red]", t.toString());
    assertEquals(2, t.size());
  }

  @Test
  public void insertSingleNodeRight() {
    BinarySearchTreeOfStrings t = uutFromArgs("red");
    t.insert("white");
    assertEquals("[red,white]", t.toString());
    assertEquals(2, t.size());
  }

  @Test
  public void insertNonEmtpyTreeSmallest() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "yellow", "blue",
        "green", "purple", "teal");
    t.insert("black");
    assertEquals("[black,blue,green,purple,red,teal,yellow]", t.toString());
    assertEquals(7, t.size());
  }

  @Test
  public void insertNonEmtpyTreeLargest() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "yellow", "blue",
        "green", "purple", "teal");
    t.insert("white");
    assertEquals("[blue,green,purple,red,teal,white,yellow]", t.toString());
    assertEquals(7, t.size());
  }

  @Test
  public void removeRootWithLeftRightBothNonEmpty() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "yellow", "blue");
    t.remove("red");
    assertEquals("[blue,yellow]", t.toString());
    assertEquals(2, t.size());
  }

  @Test
  public void removeToEmpty() {
    BinarySearchTreeOfStrings t = uutFromArgs("red");
    t.remove("red");
    assertEquals("[]", t.toString());
    assertEquals(0, t.size());
  }

  @Test
  public void removeLeftLeaf() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "blue");
    t.remove("blue");
    assertEquals("[red]", t.toString());
    assertEquals(1, t.size());
  }

  @Test
  public void removeRightLeaf() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "yellow");
    t.remove("yellow");
    assertEquals("[red]", t.toString());
    assertEquals(1, t.size());
  }

  @Test
  public void removeNodeWithOnlyLeftChild() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "green", "blue");
    t.remove("green");
    assertEquals("[blue,red]", t.toString());
    assertEquals(2, t.size());
  }

  @Test
  public void removeNodeWithOnlyRightChild() {
    BinarySearchTreeOfStrings t = uutFromArgs("blue", "green", "red");
    t.remove("green");
    assertEquals("[blue,red]", t.toString());
    assertEquals(2, t.size());
  }

  @Test
  public void containsEmptyFalse() {
    BinarySearchTreeOfStrings t = uutFromArgs();
    assertEquals(false, t.contains("red"));
    assertEquals(0, t.size());
  }

  @Test
  public void containsSingleNodeFalse() {
    BinarySearchTreeOfStrings t = uutFromArgs("red");
    assertEquals(false, t.contains("blue"));
    assertEquals(1, t.size());
  }

  @Test
  public void containsSingleNodeTrue() {
    BinarySearchTreeOfStrings t = uutFromArgs("red");
    assertEquals(true, t.contains("red"));
    assertEquals(1, t.size());
  }

  @Test
  public void containsSmallest() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "blue", "white");
    assertEquals(true, t.contains("blue"));
    assertEquals(3, t.size());
  }

  @Test
  public void containsLargest() {
    BinarySearchTreeOfStrings t = uutFromArgs("red", "blue", "white");
    assertEquals(true, t.contains("white"));
    assertEquals(3, t.size());
  }

  @Test
  public void testRoot() {
    BinarySearchTreeOfStrings t = uutFromArgs("red");
    assertEquals("red", t.root());
    assertEquals(1, t.size());
  }

}
