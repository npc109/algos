package by.semenyukna.zom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertArrayEquals(grid(
            new int[]{0, 0, 0},
            new int[]{0, 1, 0},
            new int[]{1, 2, 1}),
        solution.updateMatrix(grid(
            new int[]{0, 0, 0},
            new int[]{0, 1, 0},
            new int[]{1, 1, 1})));
  }

  @Test
  void testExample2() {
    assertArrayEquals(grid(
            new int[]{0, 0, 0},
            new int[]{0, 1, 0},
            new int[]{0, 0, 0}),
        solution.updateMatrix(grid(
            new int[]{0, 0, 0},
            new int[]{0, 1, 0},
            new int[]{0, 0, 0})));
  }

  @Test
  void testSingleZero() {
    assertArrayEquals(grid(new int[]{0}), solution.updateMatrix(grid(new int[]{0})));
  }

  @Test
  void testSingleRow() {
    assertArrayEquals(grid(new int[]{0, 1, 2, 3}),
        solution.updateMatrix(grid(new int[]{0, 1, 1, 1})));
  }

  @Test
  void testSingleColumn() {
    assertArrayEquals(grid(new int[]{0}, new int[]{1}, new int[]{2}),
        solution.updateMatrix(grid(new int[]{0}, new int[]{1}, new int[]{1})));
  }

  @Test
  void testAllZeros() {
    assertArrayEquals(grid(
            new int[]{0, 0},
            new int[]{0, 0}),
        solution.updateMatrix(grid(
            new int[]{0, 0},
            new int[]{0, 0})));
  }

  @Test
  void testZeroInCorner() {
    assertArrayEquals(grid(
            new int[]{0, 1, 2},
            new int[]{1, 2, 3},
            new int[]{2, 3, 4}),
        solution.updateMatrix(grid(
            new int[]{0, 1, 1},
            new int[]{1, 1, 1},
            new int[]{1, 1, 1})));
  }

  @Test
  void testTwoZerosAtEnds() {
    assertArrayEquals(grid(new int[]{0, 1, 2, 1, 0}),
        solution.updateMatrix(grid(new int[]{0, 1, 1, 1, 0})));
  }

  @Test
  void testCheckerboard() {
    assertArrayEquals(grid(
            new int[]{0, 1, 0},
            new int[]{1, 0, 1},
            new int[]{0, 1, 0}),
        solution.updateMatrix(grid(
            new int[]{0, 1, 0},
            new int[]{1, 0, 1},
            new int[]{0, 1, 0})));
  }

  @Test
  void testZeroInTheMiddle() {
    assertArrayEquals(grid(
            new int[]{2, 1, 2},
            new int[]{1, 0, 1},
            new int[]{2, 1, 2}),
        solution.updateMatrix(grid(
            new int[]{1, 1, 1},
            new int[]{1, 0, 1},
            new int[]{1, 1, 1})));
  }

  @Test
  void testNearestZeroWins() {
    assertArrayEquals(grid(
            new int[]{0, 1, 1, 0},
            new int[]{1, 2, 2, 1},
            new int[]{2, 3, 3, 2}),
        solution.updateMatrix(grid(
            new int[]{0, 1, 1, 0},
            new int[]{1, 1, 1, 1},
            new int[]{1, 1, 1, 1})));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertArrayEquals(grid(new int[]{0, 1}), solution.updateMatrix(grid(new int[]{0, 1})));
    assertArrayEquals(grid(new int[]{0}), solution.updateMatrix(grid(new int[]{0})));
    assertArrayEquals(grid(
            new int[]{0, 1, 2},
            new int[]{1, 2, 3}),
        solution.updateMatrix(grid(
            new int[]{0, 1, 1},
            new int[]{1, 1, 1})));
  }

  private static int[][] grid(int[]... rows) {
    int[][] result = new int[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      result[i] = rows[i].clone();
    }
    return result;
  }
}
