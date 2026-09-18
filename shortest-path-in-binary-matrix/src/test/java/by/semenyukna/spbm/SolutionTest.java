package by.semenyukna.spbm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(2, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 1},
        new int[]{1, 0})));
  }

  @Test
  void testExample2() {
    assertEquals(4, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 0, 0},
        new int[]{1, 1, 0},
        new int[]{1, 1, 0})));
  }

  @Test
  void testExample3() {
    assertEquals(-1, solution.shortestPathBinaryMatrix(grid(
        new int[]{1, 0, 0},
        new int[]{1, 1, 0},
        new int[]{1, 1, 0})));
  }

  @Test
  void testSingleOpenCell() {
    assertEquals(1, solution.shortestPathBinaryMatrix(grid(new int[]{0})));
  }

  @Test
  void testSingleBlockedCell() {
    assertEquals(-1, solution.shortestPathBinaryMatrix(grid(new int[]{1})));
  }

  @Test
  void testAllOpen() {
    // Из угла в угол по диагонали: ровно n клеток.
    assertEquals(3, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 0, 0},
        new int[]{0, 0, 0},
        new int[]{0, 0, 0})));
    assertEquals(4, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 0, 0, 0},
        new int[]{0, 0, 0, 0},
        new int[]{0, 0, 0, 0},
        new int[]{0, 0, 0, 0})));
  }

  @Test
  void testAllBlocked() {
    assertEquals(-1, solution.shortestPathBinaryMatrix(grid(
        new int[]{1, 1},
        new int[]{1, 1})));
  }

  @Test
  void testStartBlocked() {
    assertEquals(-1, solution.shortestPathBinaryMatrix(grid(
        new int[]{1, 0},
        new int[]{0, 0})));
  }

  @Test
  void testFinishBlocked() {
    assertEquals(-1, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 0},
        new int[]{0, 1})));
  }

  @Test
  void testNoPath() {
    assertEquals(-1, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 1},
        new int[]{1, 1})));
  }

  @Test
  void testPathRequiresDetour() {
    assertEquals(4, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 0, 0},
        new int[]{1, 1, 0},
        new int[]{0, 0, 0})));
  }

  @Test
  void testHorizontalPath() {
    assertEquals(3, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 0, 0})));
  }

  @Test
  void testVerticalPath() {
    assertEquals(3, solution.shortestPathBinaryMatrix(grid(
        new int[]{0},
        new int[]{0},
        new int[]{0})));
  }

  @Test
  void testDiagonalIsShorterThanStraight() {
    // По диагонали 2 клетки, зигзагом было бы больше.
    assertEquals(2, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 0},
        new int[]{0, 0})));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(2, solution.shortestPathBinaryMatrix(grid(
        new int[]{0, 1},
        new int[]{1, 0})));
    assertEquals(1, solution.shortestPathBinaryMatrix(grid(new int[]{0})));
    assertEquals(-1, solution.shortestPathBinaryMatrix(grid(new int[]{1})));
  }

  private static int[][] grid(int[]... rows) {
    int[][] result = new int[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      result[i] = rows[i].clone();
    }
    return result;
  }
}
