package parser02

import scala.util.parsing.combinator._

case class Plus(left: Double, right: Double)
case class Minus(left: Double, right: Double)

object Parser02 extends JavaTokenParsers {

  val number = decimalNumber ^^ { _.toDouble }

  val sum = number ~ "+" ~ number ^^ { case a ~ _ ~ b ⇒ Plus(a, b)  }
  val sub = number ~ "-" ~ number ^^ { case a ~ _ ~ b ⇒ Minus(a, b) }

  val op = sum | sub | number

  def apply(input: String): ParseResult[Any] = parseAll(op, input)

}


//  Parser02("3.1+5") → Success(Plus)
//                               / \
//                              /   \
//                            3.1    5
//
//  Parser02("3.1-5") → Success(Minus)
//                               / \
//                              /   \
//                            3.1    5
//
//  Parser02("3.1") → Success(3.1)
//
