package by.semenyukna.cf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import by.semenyukna.cf.bellman.BellmanFordSolution;
import by.semenyukna.cf.dfs.DfsSolution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  @Test
  void testExample1() {
    // 0->1->3 = 700 (1 остановка), 0->1->2->3 = 400 (2 остановки, не влезает в k=1)
    assertEquals(700, solve(4, new int[][]{
        {0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}
    }, 0, 3, 1));
  }

  @Test
  void testExample2() {
    // k = 1 разрешает одну остановку: 0->1->2 = 200 дешевле прямого 0->2 = 500.
    assertEquals(200, solve(3, new int[][]{
        {0, 1, 100}, {1, 2, 100}, {0, 2, 500}
    }, 0, 2, 1));
  }

  @Test
  void testExample3SameGraphWithKZero() {
    // k = 0: остановок нет, только прямой рейс 0->2 = 500.
    assertEquals(500, solve(3, new int[][]{
        {0, 1, 100}, {1, 2, 100}, {0, 2, 500}
    }, 0, 2, 0));
  }

  @Test
  void testExample4KIsZero() {
    // k = 0 — только прямой рейс. Прямого 0->2 нет, поэтому -1,
    // даже если через 1 доехать дешевле.
    assertEquals(-1, solve(3, new int[][]{
        {0, 1, 100}, {1, 2, 100}
    }, 0, 2, 0));
  }

  @Test
  void testDirectFlightWithKZero() {
    assertEquals(300, solve(2, new int[][]{{0, 1, 300}}, 0, 1, 0));
  }

  @Test
  void testDirectIsCheaperThanDetourWithKZero() {
    // k = 0: только прямое ребро 0->1 за 300.
    assertEquals(300, solve(2, new int[][]{{0, 1, 300}, {0, 1, 500}}, 0, 1, 0));
  }

  @Test
  void testSrcEqualsDst() {
    // Уже на месте: стоимость 0, остановки не нужны.
    assertEquals(0, solve(3, new int[][]{{0, 1, 100}, {1, 2, 100}}, 1, 1, 0));
  }

  @Test
  void testSrcEqualsDstWithKZero() {
    assertEquals(0, solve(2, new int[][]{{0, 1, 100}}, 0, 0, 0));
  }

  @Test
  void testNoFlightsAtAll() {
    assertEquals(-1, solve(3, new int[0][], 0, 2, 5));
  }

  @Test
  void testSingleFlight() {
    assertEquals(100, solve(2, new int[][]{{0, 1, 100}}, 0, 1, 0));
  }

  @Test
  void testCheaperLongerPathNeedsMoreStops() {
    // k = 2 хватает на 0->1->2->3 = 300, это дешевле прямого 0->3 = 1000.
    assertEquals(300, solve(4, new int[][]{
        {0, 1, 100}, {1, 2, 100}, {2, 3, 100}, {0, 3, 1000}
    }, 0, 3, 2));
  }

  @Test
  void testCheaperLongerPathDoesNotFitK() {
    // k = 1: длинный путь за 300 не влезает, берём прямой 1000.
    assertEquals(1000, solve(4, new int[][]{
        {0, 1, 100}, {1, 2, 100}, {2, 3, 100}, {0, 3, 1000}
    }, 0, 3, 1));
  }

  @Test
  void testZeroPriceFlights() {
    assertEquals(0, solve(3, new int[][]{{0, 1, 0}, {1, 2, 0}}, 0, 2, 1));
  }

  @Test
  void testMultipleRoutesWithDifferentStopCounts() {
    // 0->3 напрямую 100 (0 остановок); 0->1->3 = 20 + 30 = 50 (1 остановка).
    assertEquals(50, solve(4, new int[][]{
        {0, 3, 100}, {0, 1, 20}, {1, 3, 30}
    }, 0, 3, 1));
    // При k = 0 только прямой.
    assertEquals(100, solve(4, new int[][]{
        {0, 3, 100}, {0, 1, 20}, {1, 3, 30}
    }, 0, 3, 0));
  }

  @Test
  void testCycleInGraph() {
    // Цикл 0->1->2->0 не должен приводить к бесконечному обходу.
    assertEquals(200, solve(3, new int[][]{
        {0, 1, 100}, {1, 2, 100}, {2, 0, 100}
    }, 0, 2, 1));
  }

  @Test
  void testLongerPathThroughManyStops() {
    int n = 10;
    List<int[]> edges = new ArrayList<>();
    for (int i = 0; i < n - 1; i++) {
      edges.add(new int[]{i, i + 1, 1});
    }
    // 0->1->...->9: 9 рёбер, 8 остановок.
    assertEquals(9, solve(n, edges.toArray(new int[0][]), 0, 9, 8));
    // Не хватает остановок.
    assertEquals(-1, solve(n, edges.toArray(new int[0][]), 0, 9, 7));
  }

  @Test
  void testLargeKDoesNotBreak() {
    assertEquals(3, solve(4, new int[][]{
        {0, 1, 1}, {1, 2, 1}, {2, 3, 1}
    }, 0, 3, 100));
  }

  @Test
  void testDestinationUnreachableWithEnoughK() {
    // Остров 5 недостижим.
    assertEquals(-1, solve(6, new int[][]{
        {0, 1, 1}, {1, 2, 1}, {3, 4, 1}
    }, 0, 5, 10));
  }

  // ---------- сверка с независимым эталоном ----------

  @Test
  void testRandomAgainstReference() {
    Random rnd = new Random(20240921);
    for (int iter = 0; iter < 400; iter++) {
      int n = rnd.nextInt(6) + 1;
      int m = rnd.nextInt(10);
      int[][] flights = new int[m][];
      for (int i = 0; i < m; i++) {
        flights[i] = new int[]{
            rnd.nextInt(n), rnd.nextInt(n), rnd.nextInt(20) + 1
        };
      }
      int src = rnd.nextInt(n);
      int dst = rnd.nextInt(n);
      int k = rnd.nextInt(4);

      assertEquals(
          reference(n, flights, src, dst, k),
          solve(n, flights, src, dst, k),
          "n=" + n + " src=" + src + " dst=" + dst + " k=" + k
              + " flights=" + Arrays.deepToString(flights));
    }
  }

  /**
   * Эталон: Bellman-Ford с ограничением на число рёбер.
   * Независимая реализация — массив расстояний и k+1 проход по всем рёбрам.
   */
  private int reference(int n, int[][] flights, int src, int dst, int k) {
    final int INF = Integer.MAX_VALUE / 4;
    int[] dist = new int[n];
    Arrays.fill(dist, INF);
    dist[src] = 0;

    for (int step = 0; step <= k; step++) {
      int[] next = dist.clone();
      for (int[] f : flights) {
        int u = f[0], v = f[1], w = f[2];
        if (dist[u] != INF && dist[u] + w < next[v]) {
          next[v] = dist[u] + w;
        }
      }
      dist = next;
    }
    return dist[dst] == INF ? -1 : dist[dst];
  }

  /**
   * Прогоняет ОБА решения и сверяет их между собой.
   * Каждый вызов создаёт свежий экземпляр — состояние не течёт между тестами
   * (у DFS-решения `res` и `visited` это поля класса).
   */
  private int solve(int n, int[][] flights, int src, int dst, int k) {
    int byDfs = new DfsSolution().findCheapestPrice(n, flights, src, dst, k);
    int byBellman = new BellmanFordSolution().findCheapestPrice(n, flights, src, dst, k);
    assertEquals(byBellman, byDfs,
        "DFS и Bellman-Ford разошлись: dfs=" + byDfs + ", bellman=" + byBellman
            + " n=" + n + " src=" + src + " dst=" + dst + " k=" + k
            + " flights=" + Arrays.deepToString(flights));
    return byBellman;
  }
}
