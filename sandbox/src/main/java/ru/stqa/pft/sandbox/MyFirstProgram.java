package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("User");
    hello("Iryna");


    Square s = new Square(5);
    System.out.println("The area of a square with a side lenght of " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(3, 6);
    System.out.println("The area of a rectangle with side lenghts of " + r.a +" and "+ r.b + " = " + r.area());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}