package com.scalalearn.scala.main

//import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert._

/**
  * Created by hjw on 16/8/8.
  */
object ScalaPrintTest {
  def main(args: Array[String]) {

    assertEquals("i love scala",ScalaPrint.print())


//    var sb: StringBuilder = _
//    var lb: ListBuffer[String] = _
//
//    @Before def initialize() {
//      sb = new StringBuilder("ScalaTest is ")
//      lb = new ListBuffer[String]
//    }

//    @Test def verifyEasy() { // Uses JUnit-style assertions
//      sb.append("easy!")
//      assertEquals("ScalaTest is easy!", sb.toString)
//      assertTrue(lb.isEmpty)
//      lb += "sweet"
//      try {
//        "verbose".charAt(-1)
//        fail()
//      }
//      catch {
//        case e: StringIndexOutOfBoundsException => // Expected
//      }
//    }
//
//    @Test def verifyFun() { // Uses ScalaTest assertions
//      sb.append("fun!")
//      assert(sb.toString === "ScalaTest is fun!")
//      assert(lb.isEmpty)
//      lb += "sweeter"
//      intercept[StringIndexOutOfBoundsException] {
//        "concise".charAt(-1)
//      }
//    }
  }

}






