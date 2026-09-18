package by.semenyukna.nop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(2, solution.findCircleNum(new int[][]{
        {1, 1, 0},
        {1, 1, 0},
        {0, 0, 1}}));
  }

  @Test
  void testExample2() {
    assertEquals(3, solution.findCircleNum(new int[][]{
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1}}));
  }

  @Test
  void testSingleCity() {
    assertEquals(1, solution.findCircleNum(new int[][]{{1}}));
  }

  @Test
  void testAllConnected() {
    assertEquals(1, solution.findCircleNum(new int[][]{
        {1, 1, 1, 1},
        {1, 1, 1, 1},
        {1, 1, 1, 1},
        {1, 1, 1, 1}}));
  }

  @Test
  void testAllIsolated() {
    assertEquals(4, solution.findCircleNum(new int[][]{
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 1}}));
  }

  @Test
  void testTransitiveConnection() {
    // 0-1 и 1-2 связаны, значит все трое в одной провинции,
    // хотя isConnected[0][2] == 0.
    assertEquals(1, solution.findCircleNum(new int[][]{
        {1, 1, 0},
        {1, 1, 1},
        {0, 1, 1}}));
  }

  @Test
  void testStarThroughOneCity() {
    // Все города связаны только через город 3.
    assertEquals(1, solution.findCircleNum(new int[][]{
        {1, 0, 0, 1},
        {0, 1, 0, 1},
        {0, 0, 1, 1},
        {1, 1, 1, 1}}));
  }

  @Test
  void testTwoPairs() {
    assertEquals(2, solution.findCircleNum(new int[][]{
        {1, 1, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 1, 1},
        {0, 0, 1, 1}}));
  }

  @Test
  void testChain() {
    assertEquals(1, solution.findCircleNum(new int[][]{
        {1, 1, 0, 0, 0},
        {1, 1, 1, 0, 0},
        {0, 1, 1, 1, 0},
        {0, 0, 1, 1, 1},
        {0, 0, 0, 1, 1}}));
  }

  @Test
  void testLargeAllConnected() {
    int n = 500;
    int[][] isConnected = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        isConnected[i][j] = 1;
      }
    }
    assertEquals(1, solution.findCircleNum(isConnected));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(2, solution.findCircleNum(new int[][]{
        {1, 1, 0},
        {1, 1, 0},
        {0, 0, 1}}));
    assertEquals(1, solution.findCircleNum(new int[][]{{1}}));
    assertEquals(3, solution.findCircleNum(new int[][]{
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1}}));
    assertEquals(1, solution.findCircleNum(new int[][]{
        {1, 1, 0},
        {1, 1, 1},
        {0, 1, 1}}));
  }
}
