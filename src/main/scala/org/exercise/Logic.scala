package org.exercise

object Logic {

  type Triangle = Array[Array[Int]]

  sealed trait ParsingError
  case object EmptyTriangleRowStrings extends ParsingError
  case class WrongRowLength(index: Int, length: Int, expectedLength: Int) extends ParsingError
  case class WrongRowFormat(index: Int, row: String) extends ParsingError

  type ParsingResult = Either[ParsingError, Triangle]

  /*
    Parses a sequence of lines into a Triangle or returns a ParsingError value if something went wrong.
   */
  def triangleFromRowStrings(rowStrings: Seq[String]): ParsingResult = {

    //We exit here with a error value as there is no point doing anything further
    if(rowStrings.isEmpty) return Left(EmptyTriangleRowStrings)

    val triangle = Array.ofDim[Array[Int]](rowStrings.length)

    //Regex that checks if a given string is a valid triangle row
    val validRowRegex = raw"\d+( \d+)*".r

    rowStrings.zipWithIndex.foldLeft[ParsingResult](Right(triangle)) { case (currentTriangleResult, (currentRow, index)) =>

      val expectedLength = index + 1

      currentTriangleResult match {
        case Left(error) => Left(error)
        case Right(currentTriangle) =>
          val rowSplit = currentRow.split(" ")
          if(rowSplit.length != expectedLength) {
            Left(WrongRowLength(index, rowSplit.length, expectedLength))
          } else if(!validRowRegex.matches(currentRow)) {
            Left(WrongRowFormat(index, currentRow))
          } else {
            currentTriangle(index) = rowSplit.map(Integer.valueOf(_))
            Right(currentTriangle)
          }
      }
    }
  }

  /*
    Calculate the minimal path for a given triangle. We assume the triangle is valid.
    The algorithm starts calculating the minimal local paths from the bottom eventually collapsing into a single
    number which will be the minimal path. This avoids the performance drawbacks of using recursion.
    The first (and only) element of the resulting fold will be the minimal path.
   */
  def calculateMinimalPath(triangle: Triangle): Int = {
    triangle.reverse.reduce[Array[Int]] { case (prevRow, currentRow) =>
      //Create pairs of two elements that are adjacent to the upper element in the triangle
      val adjacentElements = prevRow.sliding(2)

      //println(currentRow.mkString(" "))

      //We zip the current row with the adjacent values and calculate the partial/local minimal paths
      currentRow.zip(adjacentElements).map { case (value, Array(leftAdjacentValue, rightAdjacentValue)) =>
        value + Math.min(leftAdjacentValue, rightAdjacentValue)
      }

    }.head
  }
}