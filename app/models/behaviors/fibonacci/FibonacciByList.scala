package models.behaviors.fibonacci

trait FibonacciByList extends Fibonacci {
  
  private val fibNumbers: LazyList[BigInt] = BigInt(0) #:: fibNumbers.scan(BigInt(1))(_ + _)

  override def fibonacciNumber(n: Int): BigInt = fibNumbers(n)
  
  // TODO: test large lists
  override def fibonacciSequence(n: Int): List[BigInt] = fibNumbers.take(n).toList
    
}