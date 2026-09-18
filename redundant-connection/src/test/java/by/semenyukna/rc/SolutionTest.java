package by.semenyukna.rc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertArrayEquals(new int[]{2, 3},
        solution.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
  }

  @Test
  void testExample2() {
    assertArrayEquals(new int[]{1, 4},
        solution.findRedundantConnection(
            new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));
  }

  @Test
  void testMinimumInput() {
    assertArrayEquals(new int[]{1, 3},
        solution.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {1, 3}}));
  }

  @Test
  void testWholeGraphIsOneCycle() {
    assertArrayEquals(new int[]{5, 1},
        solution.findRedundantConnection(
            new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 1}}));
  }

  @Test
  void testCycleInTheMiddleThenTail() {
    assertArrayEquals(new int[]{4, 2},
        solution.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 2}}));
  }

  @Test
  void testRedundantEdgeNotInOrder() {
    // Лишнее ребро идёт раньше, чем замыкающие его рёбра дерева.
    assertArrayEquals(new int[]{1, 3},
        solution.findRedundantConnection(new int[][]{{2, 3}, {1, 2}, {1, 3}}));
  }

  @Test
  void testStarWithClosure() {
    assertArrayEquals(new int[]{2, 3},
        solution.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {1, 4}, {2, 3}}));
  }

  @Test
  void testLongTailThenCycle() {
    assertArrayEquals(new int[]{7, 4},
        solution.findRedundantConnection(
            new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 4}}));
  }

  @Test
  void testLongChain() {
    int n = 1000;
    int[][] edges = new int[n][2];
    for (int i = 0; i < n - 1; i++) {
      edges[i] = new int[]{i + 1, i + 2};
    }
    edges[n - 1] = new int[]{1, n};
    assertArrayEquals(new int[]{1, n}, solution.findRedundantConnection(edges));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertArrayEquals(new int[]{2, 3},
        solution.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
    assertArrayEquals(new int[]{1, 3},
        solution.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {1, 3}}));
    assertArrayEquals(new int[]{1, 4},
        solution.findRedundantConnection(
            new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));
  }
}
