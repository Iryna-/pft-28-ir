package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance1(){
    Point p1 = new Point(1,2);
    Point p2 = new Point(2,1);
    Assert.assertEquals(Point.distance(p1,p2), 1.4142135623730951);

  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(1,2);
    Point p2 = new Point(3,2);
    Assert.assertEquals(Point.distance(p1,p2), 2.0);

  }
}
