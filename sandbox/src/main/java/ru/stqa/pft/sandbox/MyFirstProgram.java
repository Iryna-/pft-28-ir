package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("User");
    hello("Iryna");

    double l = 5;
    System.out.println("The area of a square with a side lenght of " + l + " = " + area(l));
    double a = 3;
    double b = 6;
    System.out.println("The area of a rectangle with side lenghts of " + a +" and "+ b + " = " + area(a,b));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double len){
    return len * len;
  }

  public static double area(double a, double b){
    return a * b;
  }
}