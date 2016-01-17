package parser02

import org.scalatest.FunSpec

class Parser02Spec extends FunSpec {
  describe("Parser02") {

    it("parses a sum") {
      val r = Parser02(" 1.2 + 3.4")
      assert(r.successful)
      assert(r.get === Plus(1.2, 3.4))
    }

    it("parses a substraction") {
      val r = Parser02("3.14  -12 ")
      assert(r.successful)
      assert(r.get === Minus(3.14, 12.0))
    }

    it("parses a number") {
      val r = Parser02("12.345")
      assert(r.successful)
      assert(r.get === 12.345)
    }

    it("fails to parse some invalid inputs") {
      List( "-1.2-3.4"
          , "1.2+-3.4"
          , "-1.2+3.4"
          , "x+3.4"
          , "1.2+x"
          , "1. 2+3.4"
          , "15f"
          , "-15"
          )
      .foreach { s ⇒ assert(!Parser02(s).successful) }
    }


  }
}


