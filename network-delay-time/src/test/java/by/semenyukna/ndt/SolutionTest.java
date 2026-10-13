package by.semenyukna.ndt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  // ---------- примеры с LeetCode ----------

  @Test
  void testExample1() {
    assertEquals(2, solution.networkDelayTime(
        new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
  }

  @Test
  void testExample2Unreachable() {
    // Узел 4 недостижим из 2.
    assertEquals(-1, solution.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
  }

  @Test
  void testExample3TwoNodesBothWays() {
    assertEquals(3, solution.networkDelayTime(
        new int[][]{{1, 2, 1}, {2, 1, 3}}, 2, 2));
  }

  // ---------- границы ----------

  @Test
  void testSingleNodeNoEdges() {
    // n = 1, рёбер нет: сигнал уже в единственном узле, время 0.
    assertEquals(0, solution.networkDelayTime(new int[][]{}, 1, 1));
  }

  @Test
  void testStartNodeHasNoOutgoingEdges() {
    assertEquals(-1, solution.networkDelayTime(new int[][]{{1, 2, 5}}, 2, 2));
  }

  @Test
  void testDisconnectedGraph() {
    // Узел 4 не связан ни с чем.
    assertEquals(-1, solution.networkDelayTime(
        new int[][]{{1, 2, 1}, {2, 3, 1}}, 4, 1));
  }

  @Test
  void testZeroWeightEdges() {
    // Нулевые веса: сигнал доходит мгновенно.
    assertEquals(0, solution.networkDelayTime(
        new int[][]{{1, 2, 0}, {2, 3, 0}}, 3, 1));
  }

  @Test
  void testChainWithIncreasingWeights() {
    // 1->2 (1), 2->3 (2), 3->4 (3) => max = 6
    assertEquals(6, solution.networkDelayTime(
        new int[][]{{1, 2, 1}, {2, 3, 2}, {3, 4, 3}}, 4, 1));
  }

  @Test
  void testDirectedEdgeNotTraversableBackwards() {
    // Из 2 нельзя попасть в 1 по ребру 1->2.
    assertEquals(-1, solution.networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2));
  }

  // ---------- суть Дейкстры: прямой путь дороже обходного ----------

  @Test
  void testDetourIsCheaperThanDirectEdge() {
    // 1->3 напрямую 4, но 1->2->3 = 1 + 2 = 3.
    assertEquals(3, solution.networkDelayTime(
        new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 4}}, 3, 1));
  }

  @Test
  void testBfsWouldFailHere() {
    //      1
    //   1/   \1
    //   2     4
    //   |1    |3
    //   3     5
    //    \1  /
    //      5
    // По рёбрам: 1->4->5 = 2 ребра (вес 4). Оптимально: 1->2->3->5 = 3 ребра (вес 3).
    assertEquals(3, solution.networkDelayTime(
        new int[][]{{1, 2, 1}, {1, 4, 1}, {2, 3, 1}, {4, 5, 3}, {3, 5, 1}}, 5, 1));
  }

  @Test
  void testDuplicateEdgesKeepsMinimum() {
    // Узлы 1..5, старт 5. Ребро в один и тот же узел дано дважды —
    // должен использоваться минимальный вес.
    // 5->1: 15 (и дубль 15), 5->4: 31, 1->2: 0, 1->3: 0
    // dist = [15, 15, 15, 31, 0] -> max = 31
    assertEquals(31, solution.networkDelayTime(new int[][]{
        {5, 1, 15}, {5, 4, 31}, {1, 2, 0}, {1, 3, 0}, {1, 2, 5}, {4, 1, 16}
    }, 5, 5));
  }

  @Test
  void testZeroWeightShortcut() {
    // Узлы 1..3, старт 3.
    // 3->1 напрямую 22, но 3->2 (15) + 2->1 (0) = 15 — дешевле.
    // dist[1] = 15, dist[2] = 15, dist[3] = 0 -> max = 15
    assertEquals(15, solution.networkDelayTime(new int[][]{
        {3, 2, 15}, {3, 1, 22}, {2, 1, 0}
    }, 3, 3));
  }

  @Test
  void testLargeGeneratedCase() {
    // Тот же кейс, что разбирали вручную: 20 рёбер, n = 5, k = 5.
    assertEquals(31, solution.networkDelayTime(new int[][]{
        {3, 5, 78}, {2, 1, 1}, {1, 3, 0}, {4, 3, 59}, {5, 3, 85},
        {5, 2, 22}, {2, 4, 23}, {1, 4, 43}, {4, 5, 75}, {5, 1, 15},
        {1, 5, 91}, {4, 1, 16}, {3, 2, 98}, {3, 4, 22}, {5, 4, 31},
        {1, 2, 0}, {2, 5, 4}, {4, 2, 51}, {3, 1, 36}, {2, 3, 59}
    }, 5, 5));
  }

  // ---------- независимая проверка: эталон Дейкстры ----------

  @Test
  void testRandomAgainstReference() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 300; iter++) {
      int n = rnd.nextInt(6) + 1;              // 1..6 узлов
      int edgeCount = rnd.nextInt(10);         // 0..9 рёбер
      List<int[]> edges = new ArrayList<>();
      for (int e = 0; e < edgeCount; e++) {
        int u = rnd.nextInt(n) + 1;
        int v = rnd.nextInt(n) + 1;
        int w = rnd.nextInt(10);               // 0..9, нули разрешены
        edges.add(new int[]{u, v, w});
      }
      int[][] times = edges.toArray(new int[0][]);
      int k = rnd.nextInt(n) + 1;

      int expected = reference(times, n, k);
      int actual = solution.networkDelayTime(times, n, k);
      assertEquals(expected, actual,
          "n=" + n + " k=" + k + " times=" + Arrays.deepToString(times));
    }
  }

  /** Эталон: Дейкстра на матрице смежности, независимая реализация. */
  private int reference(int[][] times, int n, int k) {
    final int INF = Integer.MAX_VALUE / 4;
    int[][] w = new int[n + 1][n + 1];
    for (int[] row : w) {
      Arrays.fill(row, INF);
    }
    for (int[] t : times) {
      w[t[0]][t[1]] = Math.min(w[t[0]][t[1]], t[2]);
    }

    int[] dist = new int[n + 1];
    Arrays.fill(dist, INF);
    dist[k] = 0;
    boolean[] done = new boolean[n + 1];

    for (int iter = 0; iter < n; iter++) {
      int u = -1;
      for (int v = 1; v <= n; v++) {
        if (!done[v] && (u == -1 || dist[v] < dist[u])) {
          u = v;
        }
      }
      if (u == -1 || dist[u] == INF) {
        break;
      }
      done[u] = true;
      for (int v = 1; v <= n; v++) {
        if (w[u][v] != INF && dist[u] + w[u][v] < dist[v]) {
          dist[v] = dist[u] + w[u][v];
        }
      }
    }

    int answer = 0;
    for (int v = 1; v <= n; v++) {
      if (dist[v] == INF) {
        return -1;
      }
      answer = Math.max(answer, dist[v]);
    }
    return answer;
  }
}
