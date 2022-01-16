package models.behaviors.fibonacci

trait FibonacciByLazyList extends Fibonacci {

  // private val numbers: LazyList[BigInt] = 
  //   BigInt(0) #:: 
  //     BigInt(1) #:: 
  //     numbers.zip(numbers.tail).map(p => p._1 + p._2)
  
  private val numbers: LazyList[BigInt] = BigInt(0) #:: numbers.scan(BigInt(1))(_ + _)

  override def fibonacci(n: Int): BigInt = numbers(n)
    
}