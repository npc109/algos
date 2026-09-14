package by.semenyukna.maoi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Кейсы подобраны так, чтобы ловить конкретные ошибки, а не только примеры с LeetCode.
 *
 * <p><b>Внимание:</b> решение мутирует входную матрицу, поэтому каждая матрица создаётся
 * отдельным вызовом {@link #grid(int[]...)} — переиспользовать одну матрицу в нескольких
 * assert'ах нельзя, второй вызов вернёт {@code 0}.
 */
public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testLeetCodeExampleOne() {
    assertEquals(6, solution.maxAreaOfIsland(grid(
        new int[]{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
        new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        new int[]{0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
        new int[]{0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
        new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
        new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0})));
  }

  @Test
  void testLeetCodeExampleTwo() {
    assertEquals(0, solution.maxAreaOfIsland(grid(
        new int[]{0, 0, 0, 0, 0, 0, 0, 0})));
  }

  @Test
  void testEmptyGrid() {
    assertEquals(0, solution.maxAreaOfIsland(new int[0][0]));
  }

  @Test
  void testAllLand() {
    // Вся матрица — один остров, ответ m * n.
    assertEquals(9, solution.maxAreaOfIsland(grid(
        new int[]{1, 1, 1},
        new int[]{1, 1, 1},
        new int[]{1, 1, 1})));
  }

  @Test
  void testAllWater() {
    assertEquals(0, solution.maxAreaOfIsland(grid(
        new int[]{0, 0},
        new int[]{0, 0})));
  }

  @Test
  void testSingleCellLand() {
    assertEquals(1, solution.maxAreaOfIsland(grid(new int[]{1})));
  }

  @Test
  void testSingleCellWater() {
    assertEquals(0, solution.maxAreaOfIsland(grid(new int[]{0})));
  }

  @Test
  void testDiagonalIsNotConnected() {
    // Диагональ не считается связностью: площадь максимального острова 1, а не 2.
    // В задаче про количество островов этот же вход дал бы 2 — вопрос другой.
    assertEquals(1, solution.maxAreaOfIsland(grid(
        new int[]{1, 0},
        new int[]{0, 1})));
  }

  @Test
  void testDifferentAreasPicksMax() {
    // Острова площадью 4 и 3: проверяет, что берётся максимум, а не первый/последний.
    assertEquals(4, solution.maxAreaOfIsland(grid(
        new int[]{1, 1, 1, 0, 0},
        new int[]{1, 0, 0, 0, 0},
        new int[]{0, 0, 0, 1, 1},
        new int[]{0, 0, 0, 1, 0})));
  }

  @Test
  void testMaxIslandIsLast() {
    // Максимум найден только в самом конце обхода.
    assertEquals(5, solution.maxAreaOfIsland(grid(
        new int[]{1, 0, 0, 0, 0},
        new int[]{0, 0, 0, 0, 0},
        new int[]{0, 0, 1, 1, 1},
        new int[]{0, 0, 1, 1, 0})));
  }

  @Test
  void testSingleRow() {
    assertEquals(3, solution.maxAreaOfIsland(grid(new int[]{1, 1, 1})));
    assertEquals(1, solution.maxAreaOfIsland(grid(new int[]{1, 0, 1, 0, 1})));
    assertEquals(0, solution.maxAreaOfIsland(grid(new int[]{0, 0})));
  }

  @Test
  void testSingleColumn() {
    assertEquals(3, solution.maxAreaOfIsland(grid(
        new int[]{1},
        new int[]{1},
        new int[]{1})));
    assertEquals(1, solution.maxAreaOfIsland(grid(
        new int[]{1},
        new int[]{0},
        new int[]{1})));
  }

  @Test
  void testLakeInsideIsland() {
    // Остров с «дыркой» внутри — всё равно одна связная компонента.
    assertEquals(8, solution.maxAreaOfIsland(grid(
        new int[]{1, 1, 1},
        new int[]{1, 0, 1},
        new int[]{1, 1, 1})));
  }

  @Test
  void testSnakeShape() {
    assertEquals(6, solution.maxAreaOfIsland(grid(
        new int[]{1, 1, 0, 0},
        new int[]{0, 1, 0, 0},
        new int[]{0, 1, 1, 1})));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    // Каждый вызов — свежая матрица: решение не хранит состояние между вызовами.
    assertEquals(9, solution.maxAreaOfIsland(grid(
        new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1})));
    assertEquals(0, solution.maxAreaOfIsland(grid(new int[]{0, 0})));
    assertEquals(1, solution.maxAreaOfIsland(grid(new int[]{1})));
    assertEquals(4, solution.maxAreaOfIsland(grid(
        new int[]{1, 1, 1, 0, 0},
        new int[]{1, 0, 0, 0, 0},
        new int[]{0, 0, 0, 1, 1},
        new int[]{0, 0, 0, 1, 0})));
  }

  /**
   * Собирает матрицу из строк: {@code grid(new int[]{1,0}, new int[]{0,1})}.
   */
  private static int[][] grid(int[]... rows) {
    int[][] result = new int[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      result[i] = rows[i].clone();
    }
    return result;
  }
}
