package org.exercise

import org.scalatest._
import flatspec._
import matchers._

class LogicSpec extends AnyFlatSpec with should.Matchers {

  "calculateMinimalPath" should "return the minimal path for the example triangle" in {

    val exampleTriangle = Array(
      Array(7),
      Array(6, 3),
      Array(3, 8, 5),
      Array(11, 2, 10, 9)
    )

    Logic.calculateMinimalPath(exampleTriangle) should be (18)
  }
}