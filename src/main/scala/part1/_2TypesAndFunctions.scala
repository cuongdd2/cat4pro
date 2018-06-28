
package part1

import scala.collection.concurrent.TrieMap
import scala.collection.mutable
import scala.util.Random

object _2TypesAndFunctions extends App {

  /**
    * Define a higher-order function (or a function object) memoize in your favorite language.
    * This function takes a pure function f as an argument and returns a function that behaves almost exactly like f,
    * except that it only calls the original function once for every argument, stores the result internally,
    * and subsequently returns this stored result every time it’s called with the same argument.
    * You can tell the memoized function from the original by watching its performance.
    * For instance, try to memoize a function that takes a long time to evaluate.
    * You’ll have to wait for the result the first time you call it, but on subsequent calls, with the same argument,
    * you should get the result immediately
    */
  def memoize[A, B](f: A => B): A => B = {
    val map = new TrieMap[A, B]()
    a => map.getOrElseUpdate(a, f(a))
  }

  def f(n: Int): Int = {
    for {
      x <- 1 to n
      y <- 1 to x
    } yield x * y + x + y
  }.sum

  val sum = memoize(f)

  def g(n: Int, seed: Long): Int = {
    val rnd = new Random(seed)
    val xs = for {
      x <- 1 to n
      y <- 1 to x
    } yield x * y + rnd.nextInt(100)
    xs.sum
  }

  val rndSum = memoize(g _ tupled)

  val n = 5000
  var time = System.currentTimeMillis()
  rndSum(n, 100)
  println(System.currentTimeMillis() - time)
  time = System.currentTimeMillis()
  rndSum(n, 100)
  println(System.currentTimeMillis() - time)
}
