package parser05

sealed trait Expr
case class Num(x: Double) extends Expr
case class Plus (left: Expr, right: Expr) extends Expr
case class Minus(left: Expr, right: Expr) extends Expr
case class Times(left: Expr, right: Expr) extends Expr
case class Div  (left: Expr, right: Expr) extends Expr

object Parser05 extends scala.util.parsing.combinator.JavaTokenParsers {

  val number = decimalNumber ^^ { x ⇒ Num(x.toDouble) }

  lazy val sum: Parser[Expr] = (term | factor) ~ ("+" | "-") ~ (sum | term | factor) ^^ {
    case a ~ "+" ~ b ⇒ Plus (a, b)
    case a ~ "-" ~ b ⇒ Minus(a, b)
  }

  lazy val term: Parser[Expr] = factor ~ ("*" | "/") ~ (term | factor) ^^ {
    case a ~ "*" ~ b ⇒ Times(a, b)
    case a ~ "/" ~ b ⇒ Div  (a, b)
  }

  lazy val factor = bracketed | number
  val bracketed: Parser[Expr] = "(" ~> expr <~ ")"
  val expr = sum | term | factor

  def apply(input: String) = parseAll(expr, input)

}

// Parser05("1 + 2 * (3 - 4) / 5") → Success(Plus)
//                                           /  \
//                                          /    \
//                                     Num(1)   Times
//                                              /   \
//                                             /     \
//                                         Num(2)    Div
//                                                   / \
//                                                  /   \
//                                               Minus  Num(5)
//                                                / \
//                                               /   \
//                                           Num(3) Num(4)


object Evaluator {
  def apply(expr: Expr): Double = expr match {
    case Num(x) ⇒ x
    case Plus (a, b) ⇒ apply(a) + apply(b)
    case Minus(a, b) ⇒ apply(a) - apply(b)
    case Times(a, b) ⇒ apply(a) * apply(b)
    case Div  (a, b) ⇒ apply(a) / apply(b)
  }
}
