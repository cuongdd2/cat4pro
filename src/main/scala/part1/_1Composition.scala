
package part1

object _1Composition extends App {

  def id[A](a: A): A = a

  def compose[A, B, B2 >: B, C](f: A => B, g: B2 => C): A => C = a => g(f(a))

  def f(i: Int): String = s"[$i]"

  def fact(n: Int): Long = (1 to n) product

}
