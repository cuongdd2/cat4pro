
package part1

object _4Kleisli extends App {

  var logger = "Log"

  def impure(i: Int): Int = {
    logger += " i"
    i + i
  }

  // by adding 1 more layer of indirection / extra level / temporary value. We can solve every problems
  def pure(i: Int, logger: String): (Int, String) = {
    (i + i, logger + " i")
  }

  // we delegate the logging to function call site
  def pure2(i: Int): (Int, String) = {
    (i + i, " i")
  }

  def safeRoot(x: Double): Opt[Double] = {
    if (x < 0.0) Non
    else Som(math.sqrt(x))
  }

  def safeReciprocal(n: Double): Opt[Double] = {
    if (n == 0.0) Non
    else Som(1 / n)
  }

  val safeRR = Opt.compose(safeReciprocal, safeRoot)
  println(safeRR(0.25))
  println(safeRR(-1))
  println(safeRR(0))

}

object Opt {

  def compose[A, B, C](oa: A => Opt[B], ob: B => Opt[C]): A => Opt[C] = a => {
      if (oa(a).isValid) ob(oa(a).value)
      else Non
    }

  def identity[A](a: A): Opt[A] = Som(a)
}

trait Opt[+A] {
  def isValid: Boolean
  def value: A

}

case object Non extends Opt[Nothing] {
  val isValid = false
  def value = throw new Exception("Empty.")
}
case class Som[+A](value: A) extends Opt[A] {
  val isValid = true
}



