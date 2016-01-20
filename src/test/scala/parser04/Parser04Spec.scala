package parser04

import org.scalatest.FunSpec
import org.scalatest.prop.TableDrivenPropertyChecks._


class Parser04Spec extends FunSpec {

  describe("Parser04") {

    val goodStuff = Table(
      ("input",         "parseTree"),
      ("1",             Num(1.0)),
      ("1 + 2",         Plus(Num(1.0), Num(2.0))),
      ("1 - 2",         Minus(Num(1.0), Num(2.0))),
      ("1 * 2",         Times(Num(1.0), Num(2.0))),
      ("1.5 / 2.5",     Div(Num(1.5), Num(2.5))),
      ("  1.5/  2.5 ",  Div(Num(1.5), Num(2.5))),
      ("1 + 2 * 3 + 4", Plus(Num(1.0),
                             Plus(Times(Num(2.0),
                                        Num(3.0)),
                                  Num(4.0)))),
      ("1 * 2 + 3 * 4", Plus(Times(Num(1.0),
                                   Num(2.0)),
                             Times(Num(3.0),
                                   Num(4.0))))
    )

    val badStuff = Table(
      "input",
      "1 +",
      "+ 1",
      "1 * * 2",
      "+ - + -",
      "3 + 2 * 4 % 3",
      "10 + x / 2"
    )

    it("works for good input") {
      forAll (goodStuff) { (input: String, parseTree: Expr) ⇒
        val output = Parser04(input)
        assert(output.successful)
        assert(output.get === parseTree)
      }
    }

    it("fails for bad stuff") {
      forAll (badStuff) { (input: String) ⇒
        assert(!Parser04(input).successful)
      }
    }

  }

}


