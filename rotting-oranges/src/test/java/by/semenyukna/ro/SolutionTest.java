package by.semenyukna.ro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(4, solution.orangesRotting(grid(
        new int[]{2, 1, 1},
        new int[]{1, 1, 0},
        new int[]{0, 1, 1})));
  }

  @Test
  void testExample2() {
    assertEquals(-1, solution.orangesRotting(grid(
        new int[]{2, 1, 1},
        new int[]{0, 1, 1},
        new int[]{1, 0, 1})));
  }

  @Test
  void testExample3() {
    assertEquals(0, solution.orangesRotting(grid(new int[]{0, 2})));
  }

  @Test
  void testSingleFreshOrange() {
    assertEquals(-1, solution.orangesRotting(grid(new int[]{1})));
  }

  @Test
  void testSingleRottenOrange() {
    assertEquals(0, solution.orangesRotting(grid(new int[]{2})));
  }

  @Test
  void testSingleEmptyCell() {
    assertEquals(0, solution.orangesRotting(grid(new int[]{0})));
  }

  @Test
  void testEmptyGrid() {
    assertEquals(0, solution.orangesRotting(new int[0][0]));
  }

  @Test
  void testAllRotten() {
    assertEquals(0, solution.orangesRotting(grid(
        new int[]{2, 2},
        new int[]{2, 2})));
  }

  @Test
  void testAllEmpty() {
    assertEquals(0, solution.orangesRotting(grid(
        new int[]{0, 0},
        new int[]{0, 0})));
  }

  @Test
  void testFreshWithoutSource() {
    assertEquals(-1, solution.orangesRotting(grid(
        new int[]{1, 1},
        new int[]{1, 1})));
  }

  @Test
  void testBlockedByEmptyCells() {
    assertEquals(-1, solution.orangesRotting(grid(
        new int[]{2, 0},
        new int[]{0, 1})));
  }

  @Test
  void testTwoSourcesInParallel() {
    assertEquals(2, solution.orangesRotting(grid(
        new int[]{2, 1, 1},
        new int[]{0, 0, 0},
        new int[]{2, 1, 1})));
  }

  @Test
  void testThreeMinutes() {
    assertEquals(3, solution.orangesRotting(grid(
        new int[]{2, 1, 1},
        new int[]{1, 1, 1})));
  }

  @Test
  void testSingleRow() {
    assertEquals(3, solution.orangesRotting(grid(new int[]{2, 1, 1, 1})));
    assertEquals(0, solution.orangesRotting(grid(new int[]{2, 2, 2})));
    assertEquals(-1, solution.orangesRotting(grid(new int[]{0, 1})));
  }

  @Test
  void testSingleColumn() {
    assertEquals(3, solution.orangesRotting(grid(
        new int[]{2},
        new int[]{1},
        new int[]{1},
        new int[]{1})));
  }

  @Test
  void testSourceInTheMiddle() {
    assertEquals(1, solution.orangesRotting(grid(new int[]{1, 2, 1})));
  }

  @Test
  void testUnreachableFreshOranges() {
    assertEquals(-1, solution.orangesRotting(grid(new int[]{2, 1, 0, 1, 1})));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(2, solution.orangesRotting(grid(
        new int[]{2, 1},
        new int[]{1, 1})));
    assertEquals(0, solution.orangesRotting(grid(new int[]{2})));
    assertEquals(-1, solution.orangesRotting(grid(new int[]{1})));
    assertEquals(4, solution.orangesRotting(grid(
        new int[]{2, 1, 1},
        new int[]{1, 1, 0},
        new int[]{0, 1, 1})));
  }

  private static int[][] grid(int[]... rows) {
    int[][] result = new int[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      result[i] = rows[i].clone();
    }
    return result;
  }
}
