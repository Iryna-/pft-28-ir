package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTests {

  @Test
  public void primeTest (){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void primeFastTest (){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void primeVeryFastTest (){
    Assert.assertTrue(Primes.isPrimeVeryFast(Integer.MAX_VALUE));
  }

  @Test (enabled = false)
  public void primeLongTest (){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }


  @Test
  public void nonPrimeTest (){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }
}
