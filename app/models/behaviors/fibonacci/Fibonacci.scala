package models.behaviors.fibonacci

trait Fibonacci {
  def fibonacciNumber(n: Int): BigInt
  def fibonacciSequence(n: Int): List[BigInt]
}