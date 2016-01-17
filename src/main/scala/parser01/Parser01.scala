package parser01

import scala.util.parsing.combinator._

object Parser01 extends JavaTokenParsers {

  val sum = decimalNumber ~ "+" ~ decimalNumber

  def apply(input: String) = parseAll(sum, input)

}


//  Parser01("3.1+5") →  Success(~)
//                              / \
//                             /   \
//                            ~    "5"
//                           / \
//                          /   \
//                       "3.1"  "+"
//
//  Parser01("3.1-5") →  Failure("+ expected but - found")
//  Parser01("3.1+x") →  Failure("decimal expected but x found")
