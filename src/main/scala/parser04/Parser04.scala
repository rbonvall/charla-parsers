package parser04

import scala.util.parsing.combinator.JavaTokenParsers

sealed trait Expr
case class Num(x: Double) extends Expr
case class Plus (left: Expr, right: Expr) extends Expr
case class Minus(left: Expr, right: Expr) extends Expr
case class Times(left: Expr, right: Expr) extends Expr
case class Div  (left: Expr, right: Expr) extends Expr

object Parser04 extends JavaTokenParsers {

  val number = decimalNumber ^^ { x ⇒ Num(x.toDouble) }

  val term: Parser[Expr] = number ~ ("*" | "/") ~ (term | number) ^^ {
    case a ~ "*" ~ b ⇒ Times(a, b)
    case a ~ "/" ~ b ⇒ Div  (a, b)
  }

  val sum: Parser[Expr] = (term | number) ~ ("+" | "-") ~ (sum | term | number) ^^ {
    case a ~ "+" ~ b ⇒ Plus (a, b)
    case a ~ "-" ~ b ⇒ Minus(a, b)
  }

  val expr = sum | term | number

  def apply(input: String) = parseAll(expr, input)

}

// Parser04("1 + 2 * 3 - 4 / 5") → Success(Plus)
//                                         /  \
//                                        /    \
//                                   Num(1)    Minus
//                                            /     \
//                                           /       \
//                                       Times        Div
//                                       / \          / \
//                                      /   \        /   \
//                                 Num(2) Num(3)  Num(4) Num(5)


object Evaluator {
  def apply(expr: Expr): Double = expr match {
    case Num(x) ⇒ x
    case Plus (a, b) ⇒ apply(a) + apply(b)
    case Minus(a, b) ⇒ apply(a) - apply(b)
    case Times(a, b) ⇒ apply(a) * apply(b)
    case Div  (a, b) ⇒ apply(a) / apply(b)
  }
}
