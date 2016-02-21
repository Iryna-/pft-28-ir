package ru.stqa.pft.sandbox;

public class DistanceExamples {

  public static void main(String[] args) {
    Point a = new Point(3, 5);
    Point b = new Point(7, 9);

    System.out.println("The distance between (" + a.x +";" + a.y + ") and (" + b.x +";" + b.y + ") is " + Point.distance(a,b));

  }
}
