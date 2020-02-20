package lab10;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import components.map.Map;

public class PizzaOrderManagerTest {

  private Map<String, Integer> toppingMenu;
  private Map<String, Integer> sizeMenu;

  @Before
  public void setup() {
    sizeMenu = PizzaOrderManager.getPriceMap("data/lab10-sizes.txt");
    toppingMenu = PizzaOrderManager.getPriceMap("data/lab10-toppings.txt");
  }

  @Test
  public void testFormatPrice2019() {
    String actual = PizzaOrderManager.formatPrice(2019);
    assertEquals("$20.19", actual);
  }

  @Test
  public void testFormatPrice1805() {
    String actual = PizzaOrderManager.formatPrice(1805);
    assertEquals("$18.05", actual);
  }

  @Test
  public void testFormatPrice0() {
    String actual = PizzaOrderManager.formatPrice(0);
    assertEquals("$0.00", actual);
  }

  @Test
  public void testSetup() {
    assertEquals(
        "{(great biggie,1995),(small,795),(biggie,1595),(large,1295),(medium,995)}",
        sizeMenu.toString());

    assertEquals(
        "{(green pepper,95),(pineapple,95),(pepperoni,120),(mushroom,95),(extra cheese,75),(ham,120),(potato,95),(tomato,95),(onion,95),(anchovy,150),(sausage,120)}",
        toppingMenu.toString());
  }

  @Test
  public void testOrder1() {
    assertEquals(1715, PizzaOrderManager.getOrderPrice("biggie,pepperoni",
        sizeMenu, toppingMenu));
  }

  @Test
  public void testOrder2() {
    assertEquals(1295,
        PizzaOrderManager.getOrderPrice("large", sizeMenu, toppingMenu));
  }

  @Test
  public void testOrder3() {
    assertEquals(1305, PizzaOrderManager.getOrderPrice(
        "medium,green pepper,mushroom,pepperoni", sizeMenu, toppingMenu));
  }

  @Test
  public void testOrder4() {
    assertEquals(1060, PizzaOrderManager.getOrderPrice(
        "small,onion,extra cheese,pineapple", sizeMenu, toppingMenu));
  }

  @Test
  public void testOrder5() {
    assertEquals(2500,
        PizzaOrderManager.getOrderPrice(
            "great biggie,extra cheese,sausage,pepperoni,potato,green pepper",
            sizeMenu, toppingMenu));
  }

  @Test
  public void testOrder6() {
    assertEquals(1995, PizzaOrderManager.getOrderPrice("great biggie",
        sizeMenu, toppingMenu));
  }

}
