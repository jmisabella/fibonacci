
package models.behaviors.fibonacci

import scala.annotation.tailrec

trait FibonacciByRecursion extends Fibonacci {

  override def fibonacci(n: Int): BigInt = {
    @tailrec 
    def fib(count: BigInt, previous: BigInt, acc: BigInt): BigInt = count match {
      case i if (i < 0) => throw new IllegalArgumentException(s"Cannot determine Fibonacci number if negative value [$i]") 
      case i if (i == 0) => acc 
      case _ => fib(count - 1, acc, acc + previous)
    }
    fib(n, BigInt(1), BigInt(0))
  }

}