package org.exercise

object Logic {

  type Triangle = Array[Array[Int]]

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

      //We zip the current row with the adjacent values and calculate the partial/local minimal paths
      currentRow.zip(adjacentElements).map { case (value, Array(leftAdjacentValue, rightAdjacentValue)) =>
        value + Math.min(leftAdjacentValue, rightAdjacentValue)
      }

    }.head
  }
}