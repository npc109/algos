package by.semenyukna.igb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertFalse(solution.isBipartite(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
  }

  @Test
  void testExample2() {
    assertTrue(solution.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
  }

  @Test
  void testSingleEdge() {
    assertTrue(solution.isBipartite(new int[][]{{1}, {0}}));
  }

  @Test
  void testSingleVertex() {
    assertTrue(solution.isBipartite(new int[][]{{}}));
  }

  @Test
  void testTwoVerticesNoEdge() {
    assertTrue(solution.isBipartite(new int[][]{{}, {}}));
  }

  @Test
  void testPath() {
    assertTrue(solution.isBipartite(new int[][]{{1}, {0, 2}, {1, 3}, {2}}));
  }

  @Test
  void testTwoNeighboursOfSameVertex() {
    // 0 и 1 не соседи, они оба соединены только с 2 — цвета у них совпадают.
    assertTrue(solution.isBipartite(new int[][]{{2}, {2}, {0, 1}}));
  }

  @Test
  void testStar() {
    assertTrue(solution.isBipartite(new int[][]{{1, 2, 3}, {0}, {0}, {0}}));
  }

  @Test
  void testTriangle() {
    assertFalse(solution.isBipartite(new int[][]{{1, 2}, {0, 2}, {0, 1}}));
  }

  @Test
  void testOddCycleOfFive() {
    assertFalse(solution.isBipartite(new int[][]{{1, 4}, {0, 2}, {1, 3}, {2, 4}, {3, 0}}));
  }

  @Test
  void testEvenCycleOfSix() {
    assertTrue(solution.isBipartite(new int[][]{{1, 5}, {0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 0}}));
  }

  @Test
  void testDisconnectedEdgePlusTriangle() {
    // Компонента 0-1 двудольная, компонента 2-3-4 — треугольник.
    assertFalse(solution.isBipartite(new int[][]{{1}, {0}, {3, 4}, {2, 4}, {2, 3}}));
  }

  @Test
  void testDisconnectedBothBipartite() {
    assertTrue(solution.isBipartite(new int[][]{{1}, {0}, {3}, {2}}));
  }

  @Test
  void testCompleteGraphK4() {
    assertFalse(solution.isBipartite(new int[][]{{1, 2, 3}, {0, 2, 3}, {0, 1, 3}, {0, 1, 2}}));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertFalse(solution.isBipartite(new int[][]{{1, 2}, {0, 2}, {0, 1}}));
    assertTrue(solution.isBipartite(new int[][]{{1}, {0}}));
    assertTrue(solution.isBipartite(new int[][]{{2}, {2}, {0, 1}}));
    assertFalse(solution.isBipartite(new int[][]{{1}, {0}, {3, 4}, {2, 4}, {2, 3}}));
  }
}
