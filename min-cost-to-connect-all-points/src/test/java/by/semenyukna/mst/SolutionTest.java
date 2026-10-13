package by.semenyukna.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import by.semenyukna.mst.kruskal.KruskalSolution;
import by.semenyukna.mst.prim.PrimSolution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SolutionTest {

  private final KruskalSolution kruskal = new KruskalSolution();
  private final PrimSolution prim = new PrimSolution();

  /**
   * Прогоняет ОБА решения и проверяет, что они дают одинаковый результат.
   * Возвращает общий ответ для дальнейшего сравнения с ожиданием.
   */
  private int both(int[][] points) {
    int byKruskal = kruskal.minCostConnectPoints(points);
    int byPrim = prim.minCostConnectPoints(points);
    assertEquals(byKruskal, byPrim,
        "Краскал и Прим разошлись на " + Arrays.deepToString(points)
            + ": краскал=" + byKruskal + ", прим=" + byPrim);
    return byKruskal;
  }

  // ---------- примеры с LeetCode ----------

  @Test
  void testExample1() {
    assertEquals(20, both(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
  }

  @Test
  void testExample2() {
    assertEquals(18, both(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}));
  }

  // ---------- границы ----------

  @Test
  void testSinglePoint() {
    // Одна точка — соединять нечего, стоимость 0.
    assertEquals(0, both(new int[][]{{0, 0}}));
  }

  @Test
  void testTwoPoints() {
    // |0-3| + |0-4| = 7
    assertEquals(7, both(new int[][]{{0, 0}, {3, 4}}));
  }

  @Test
  void testTwoIdenticalPoints() {
    // Совпадающие точки: расстояние 0.
    assertEquals(0, both(new int[][]{{5, 5}, {5, 5}}));
  }

  @Test
  void testAllPointsIdentical() {
    assertEquals(0, both(new int[][]{{1, 1}, {1, 1}, {1, 1}, {1, 1}}));
  }

  @Test
  void testPointsOnALine() {
    // Точки 0, 1, 2, 3 на оси X: MST = 1 + 1 + 1 = 3
    assertEquals(3, both(new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}}));
  }

  @Test
  void testPointsOnALineWithBigGaps() {
    // 0, 10, 11, 12: MST = 10 + 1 + 1 = 12
    assertEquals(12, both(new int[][]{{0, 0}, {10, 0}, {11, 0}, {12, 0}}));
  }

  @Test
  void testNegativeCoordinates() {
    // Точки (-1,-1), (1,1): расстояние 2 + 2 = 4
    assertEquals(4, both(new int[][]{{-1, -1}, {1, 1}}));
  }

  @Test
  void testLargeCoordinates() {
    // |10000 - (-10000)| * 2 = 40000
    assertEquals(40000, both(new int[][]{{-10000, -10000}, {10000, 10000}}));
  }

  @Test
  void testSquare() {
    // Квадрат 2x2: три ребра по 2 = 6
    assertEquals(6, both(new int[][]{{0, 0}, {2, 0}, {2, 2}, {0, 2}}));
  }

  @Test
  void testManyPointsOnSameSpotPlusOne() {
    // Три точки в одном месте и одна в стороне: нужно одно ребро длины 5.
    assertEquals(5, both(new int[][]{{0, 0}, {0, 0}, {0, 0}, {5, 0}}));
  }

  // ---------- проверка на то, что это именно MST, а не что-то другое ----------

  @Test
  void testGreedyByNearestNeighbourIsNotOptimal() {
    // Классический случай, где жадный обход «ближайшего соседа» даёт не MST.
    // Точки: A(0,0), B(1,0), C(5,0), D(6,0)
    // Рёбра: A-B=1, B-C=4, C-D=1, A-C=5, B-D=5, A-D=6
    // MST = A-B(1) + B-C(4) + C-D(1) = 6
    assertEquals(6, both(new int[][]{{0, 0}, {1, 0}, {5, 0}, {6, 0}}));
  }

  @Test
  void testClusterOfPoints() {
    // Два кластера далеко друг от друга, внутри каждого — плотно.
    int[][] points = {
        {0, 0}, {1, 0}, {2, 0},        // кластер 1
        {100, 0}, {101, 0}, {102, 0}   // кластер 2
    };
    // Внутри кластера 1: 1 + 1 = 2
    // Внутри кластера 2: 1 + 1 = 2
    // Между кластерами: минимум 100 - 2 = 98 (точка (2,0) до (100,0))
    // Итого 2 + 2 + 98 = 102
    assertEquals(102, both(points));
  }

  // ---------- независимая проверка ----------

  @Test
  void testRandomAgainstReference() {
    Random rnd = new Random(31337);
    for (int iter = 0; iter < 500; iter++) {
      int n = rnd.nextInt(8) + 1;
      int[][] points = new int[n][2];
      for (int i = 0; i < n; i++) {
        points[i][0] = rnd.nextInt(21) - 10;
        points[i][1] = rnd.nextInt(21) - 10;
      }
      int expected = reference(points);
      int actual = both(points);
      assertEquals(expected, actual, "points=" + Arrays.deepToString(points));
    }
  }

  /**
   * Эталон: Краскал на всех парах точек, независимая реализация.
   * Использует примитивный Union-Find без оптимизаций — лишь бы был верным.
   */
  private int reference(int[][] points) {
    int n = points.length;
    List<int[]> edges = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
        edges.add(new int[]{d, i, j});
      }
    }
    edges.sort((a, b) -> Integer.compare(a[0], b[0]));

    int[] parent = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }

    int total = 0;
    int used = 0;
    for (int[] e : edges) {
      if (used == n - 1) {
        break;
      }
      int ra = root(parent, e[1]);
      int rb = root(parent, e[2]);
      if (ra != rb) {
        parent[ra] = rb;
        total += e[0];
        used++;
      }
    }
    return total;
  }

  private int root(int[] parent, int x) {
    while (parent[x] != x) {
      x = parent[x];
    }
    return x;
  }

  /** Проверка, что ответ действительно соединяет все точки (связность). */
  @Test
  void testResultIsConnecting() {
    // Косвенная проверка: если решение вернуло неверную стоимость,
    // тест на конкретных данных это поймает. Здесь проверяем свойство:
    // для n точек минимальная стоимость не превосходит суммы расстояний
    // до первой точки (звезда — всегда валидное остовное дерево).
    Random rnd = new Random(99);
    for (int iter = 0; iter < 100; iter++) {
      int n = rnd.nextInt(10) + 1;
      int[][] points = new int[n][2];
      for (int i = 0; i < n; i++) {
        points[i][0] = rnd.nextInt(41) - 20;
        points[i][1] = rnd.nextInt(41) - 20;
      }
      int star = 0;
      for (int i = 1; i < n; i++) {
        star += Math.abs(points[i][0] - points[0][0]) + Math.abs(points[i][1] - points[0][1]);
      }
      int got = both(points);
      org.junit.jupiter.api.Assertions.assertTrue(
          got <= star && got >= 0,
          "MST (" + got + ") должен быть не больше звезды (" + star + ") для "
              + Arrays.deepToString(points));
    }
  }
}
