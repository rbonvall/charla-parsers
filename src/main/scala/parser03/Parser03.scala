package parser03

import scala.util.parsing.combinator._

sealed trait Expression
case class Number(x: Double) extends Expression
case class Plus (left: Double, right: Double) extends Expression
case class Minus(left: Double, right: Double) extends Expression

object Parser03 extends JavaTokenParsers {

  val number: Parser[Number] = decimalNumber ^^ { x ⇒ Number(x.toDouble) }

  val sum: Parser[Plus]  = number ~ "+" ~ number ^^ { case a ~ _ ~ b ⇒ Plus(a.x, b.x)  }
  val sub: Parser[Minus] = number ~ "-" ~ number ^^ { case a ~ _ ~ b ⇒ Minus(a.x, b.x) }

  val op: Parser[Expression] = sum | sub | number

  def apply(input: String): ParseResult[Expression] = parseAll(op, input)

}

object Evaluator {
  def apply(expr: Expression): Double = expr match {
    case Number(x)   ⇒ x
    case Plus (x, y) ⇒ x + y
    case Minus(x, y) ⇒ x - y
  }
}
