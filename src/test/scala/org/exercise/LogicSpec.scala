package org.exercise

import org.scalatest._
import flatspec._
import matchers._

import org.exercise.Logic._

class LogicSpec extends AnyFlatSpec with should.Matchers {

  "triangleFromString" should "fail when the string is empty" in {

    val testRowStrings = Seq.empty[String]

    Logic.triangleFromRowStrings(testRowStrings) should be (Left(EmptyTriangleRowStrings))
  }

  "triangleFromString" should "fail when a row has the wrong length" in {

    val testRowStrings = Seq(
      "1",
      "3 4",
      "4 4 1 1",
      "6 5 4 3 1"
    )

    Logic.triangleFromRowStrings(testRowStrings) should be (Left(WrongRowLength(2, 4, 3)))
  }

  "triangleFromString" should "fail when a row has the wrong format" in {

    val testRowStrings1 = Seq(
      "1",
      "3 b"
    )

    val testRowStrings2 = Seq(
      "1",
      "3 15",
      "! 12 13"
    )

    Logic.triangleFromRowStrings(testRowStrings1) should be (Left(WrongRowFormat(1, testRowStrings1(1))))
    Logic.triangleFromRowStrings(testRowStrings2) should be (Left(WrongRowFormat(2, testRowStrings2(2))))
  }

  "calculateMinimalPath" should "return the minimal path for the example triangle" in {

    val exampleTriangle = Array(
      Array(7),
      Array(6, 3),
      Array(3, 8, 5),
      Array(11, 2, 10, 9)
    )

    Logic.calculateMinimalPath(exampleTriangle) should be (18)
  }

  "calculateMinimalPath" should "return the minimal path for big triangles" in {

    val testTriangle = Array.tabulate(500) { index =>
      Array.fill(index + 1)(1)
    }

    Logic.calculateMinimalPath(testTriangle) should be (500)
  }
}