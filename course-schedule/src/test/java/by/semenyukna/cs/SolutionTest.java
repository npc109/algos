package by.semenyukna.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertTrue(solution.canFinish(2, new int[][]{{1, 0}}));
  }

  @Test
  void testExample2() {
    assertFalse(solution.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
  }

  @Test
  void testExample3() {
    assertFalse(solution.canFinish(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}));
  }

  @Test
  void testExample4() {
    assertTrue(solution.canFinish(5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}}));
  }

  @Test
  void testExample5() {
    assertTrue(solution.canFinish(1, new int[][]{}));
  }

  @Test
  void testSelfDependency() {
    assertFalse(solution.canFinish(1, new int[][]{{0, 0}}));
  }

  @Test
  void testSelfDependencyAmongOthers() {
    assertFalse(solution.canFinish(3, new int[][]{{1, 0}, {2, 2}}));
  }

  @Test
  void testNoEdges() {
    assertTrue(solution.canFinish(5, new int[][]{}));
  }

  @Test
  void testChain() {
    assertTrue(solution.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}));
  }

  @Test
  void testChainWithClosure() {
    assertFalse(solution.canFinish(4, new int[][]{{1, 0}, {2, 1}, {3, 2}, {0, 3}}));
  }

  @Test
  void testDiamond() {
    // Две вершины сходятся в одну — не цикл.
    assertTrue(solution.canFinish(4, new int[][]{{2, 0}, {2, 1}, {3, 0}, {3, 1}}));
  }

  @Test
  void testTwoVertexCycleWithFreeCourses() {
    assertFalse(solution.canFinish(5, new int[][]{{1, 0}, {0, 1}}));
  }

  @Test
  void testDuplicateEdges() {
    assertTrue(solution.canFinish(2, new int[][]{{1, 0}, {1, 0}}));
  }

  @Test
  void testIsolatedCourse() {
    assertTrue(solution.canFinish(3, new int[][]{{2, 1}}));
  }

  @Test
  void testLongChain() {
    int n = 1000;
    int[][] pre = new int[n - 1][2];
    for (int i = 0; i < n - 1; i++) {
      pre[i] = new int[]{i + 1, i};
    }
    assertTrue(solution.canFinish(n, pre));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertTrue(solution.canFinish(2, new int[][]{{1, 0}}));
    assertFalse(solution.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    assertTrue(solution.canFinish(1, new int[][]{}));
    assertFalse(solution.canFinish(1, new int[][]{{0, 0}}));
  }
}
