package org.exercise

import io.StdIn.readLine

import org.exercise.Logic._

object Main extends App {

  val input = Iterator
    .continually(readLine)
    .takeWhile(_ != "EOF")
    .toSeq

  Logic.triangleFromRowStrings(input) match {
    case Left(EmptyTriangleRowStrings) =>
      println("ERROR: The input is empty")
    case Left(WrongRowLength(index, length, expectedLength)) =>
      println(s"ERROR: Line number ${index+1} has the length $length but it should be $expectedLength")
    case Left(WrongRowFormat(index, row)) =>
      println(s"ERROR: Line number ${index+1} has the wrong format: $row")
    case Right(triangle) =>
      println(Logic.calculateMinimalPath(triangle))
  }

}