package parser01

import org.scalatest.FunSpec

class Parser01Spec extends FunSpec {
  describe("Parser01") {

    it("parses a sum") {
      val r = Parser01("1.2+3.4")
      val parseTree = Parser01.~(Parser01.~("1.2", "+"), "3.4")
      assert(r.successful)
      assert(r.get === parseTree)
    }

    it("parses a sum with lots of whitespace") {
      val r = Parser01("   1.2+  3.4       ")
      val parseTree = Parser01.~(Parser01.~("1.2", "+"), "3.4")
      assert(r.successful)
      assert(r.get === parseTree)
    }

    it("fails to parse some invalid inputs") {
      val inputs = List( "1.2-3.4"
                       , "1.2+-3.4"
                       , "-1.2+3.4"
                       , "x+3.4"
                       , "1.2+x"
                       , "1. 2+3.4"
                       )
      for { s ← inputs } {
        assert(!Parser01(s).successful)
      }
    }


  }
}


