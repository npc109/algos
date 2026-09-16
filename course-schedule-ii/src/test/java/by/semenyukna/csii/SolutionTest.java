package by.semenyukna.csii;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Порядков может быть несколько валидных, поэтому вместо сравнения с эталонным массивом
 * проверяется сам инвариант: каждая вершина встречается один раз и её предусловия идут раньше.
 */
public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertTrue(isValid(solution.findOrder(2, new int[][]{{1, 0}}), 2, new int[][]{{1, 0}}));
  }

  @Test
  void testExample2() {
    assertTrue(isValid(
        solution.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}),
        4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
  }

  @Test
  void testExample3() {
    assertTrue(isValid(solution.findOrder(1, new int[][]{}), 1, new int[][]{}));
  }

  @Test
  void testCycleReturnsEmpty() {
    assertArrayEquals(new int[]{}, solution.findOrder(2, new int[][]{{1, 0}, {0, 1}}));
  }

  @Test
  void testSelfDependency() {
    assertArrayEquals(new int[]{}, solution.findOrder(1, new int[][]{{0, 0}}));
  }

  @Test
  void testCycleAmongFreeCourses() {
    assertArrayEquals(new int[]{}, solution.findOrder(5, new int[][]{{1, 0}, {0, 1}}));
  }

  @Test
  void testNoEdges() {
    int[] result = solution.findOrder(3, new int[][]{});
    assertEquals(3, result.length);
    assertEquals(3, uniqueCount(result));
  }

  @Test
  void testChain() {
    assertTrue(isValid(
        solution.findOrder(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}),
        4, new int[][]{{1, 0}, {2, 1}, {3, 2}}));
  }

  @Test
  void testDiamond() {
    assertTrue(isValid(
        solution.findOrder(4, new int[][]{{2, 0}, {2, 1}, {3, 0}, {3, 1}}),
        4, new int[][]{{2, 0}, {2, 1}, {3, 0}, {3, 1}}));
  }

  @Test
  void testIsolatedCourses() {
    assertTrue(isValid(solution.findOrder(4, new int[][]{{3, 2}}), 4, new int[][]{{3, 2}}));
  }

  @Test
  void testDuplicateEdges() {
    assertTrue(isValid(
        solution.findOrder(2, new int[][]{{1, 0}, {1, 0}}),
        2, new int[][]{{1, 0}, {1, 0}}));
  }

  @Test
  void testLongChain() {
    int n = 1000;
    int[][] pre = new int[n - 1][2];
    for (int i = 0; i < n - 1; i++) {
      pre[i] = new int[]{i + 1, i};
    }
    int[] result = solution.findOrder(n, pre);
    assertEquals(n, result.length);
    for (int i = 0; i < n; i++) {
      assertEquals(i, result[i]);
    }
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertTrue(isValid(solution.findOrder(2, new int[][]{{1, 0}}), 2, new int[][]{{1, 0}}));
    assertArrayEquals(new int[]{}, solution.findOrder(2, new int[][]{{1, 0}, {0, 1}}));
    assertTrue(isValid(solution.findOrder(1, new int[][]{}), 1, new int[][]{}));
    assertArrayEquals(new int[]{}, solution.findOrder(1, new int[][]{{0, 0}}));
  }

  /** Каждая вершина ровно один раз, и для каждой пары `[a, b]` вершина `b` стоит раньше `a`. */
  private static boolean isValid(int[] order, int numCourses, int[][] prerequisites) {
    if (order.length != numCourses || uniqueCount(order) != numCourses) {
      return false;
    }
    int[] position = new int[numCourses];
    for (int i = 0; i < order.length; i++) {
      position[order[i]] = i;
    }
    for (int[] p : prerequisites) {
      if (position[p[1]] > position[p[0]]) {
        return false;
      }
    }
    return true;
  }

  private static int uniqueCount(int[] values) {
    Set<Integer> seen = new HashSet<>();
    for (int v : values) {
      seen.add(v);
    }
    return seen.size();
  }
}
