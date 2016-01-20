package parser03

import org.scalatest.FunSpec

class Parser03Spec extends FunSpec {

  describe("Parser03") {

    it("parses a sum") {
      val r = Parser03(" 1.2 + 3.4")
      assert(r.successful)
      assert(r.get === Plus(1.2, 3.4))
    }

    it("parses a substraction") {
      val r = Parser03("3.14  -12 ")
      assert(r.successful)
      assert(r.get === Minus(3.14, 12.0))
    }

    it("parses a number") {
      val r = Parser03("12.345")
      assert(r.successful)
      assert(r.get === Number(12.345))
    }

    it("fails to parse some invalid inputs") {
      """-1.2-3.4
        |1.2+-3.4
        |-1.2+3.4
        |x+3.4
        |1.2+x
        |1. 2+3.4
        |15f
        |-15"""
      .stripMargin
      .split("\n")
      .foreach { s ⇒
        assert(!Parser03(s).successful)
      }
    }

  }

  describe("Evaluator") {
    it("works") {
      assert(Evaluator(Number(3.14159)) === 3.14159)
      assert(Evaluator(Plus(-5.0, 3.0)) === -2.0)
      assert(Evaluator(Minus(1.25, 0.6)) === 0.65)
    }
  }

}


