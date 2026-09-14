package by.semenyukna.noi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Кейсы подобраны так, чтобы ловить конкретные ошибки реализации, а не только примеры с LeetCode.
 *
 * <p><b>Внимание:</b> решение мутирует входную матрицу, поэтому каждая матрица создаётся
 * отдельным вызовом {@link #grid(String...)} — переиспользовать одну и ту же матрицу
 * в нескольких assert'ах нельзя, второй вызов вернёт {@code 0}.
 */
public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExampleThreeIslands() {
    assertEquals(3, solution.numIslands(grid(
        "11000",
        "11000",
        "00100",
        "00011")));
  }

  @Test
  void testExampleOneIsland() {
    assertEquals(1, solution.numIslands(grid(
        "11110",
        "11010",
        "11000",
        "00000")));
  }

  @Test
  void testSingleIslandSurroundedByWater() {
    // Центральный ноль не разрывает связность: всё соединено через стороны.
    assertEquals(1, solution.numIslands(grid(
        "111",
        "010",
        "111")));
  }

  @Test
  void testEmptyGrid() {
    assertEquals(0, solution.numIslands(new char[0][0]));
  }

  @Test
  void testEmptyRows() {
    assertEquals(0, solution.numIslands(new char[][]{{}, {}}));
  }

  @Test
  void testSingleCellLand() {
    assertEquals(1, solution.numIslands(grid("1")));
  }

  @Test
  void testSingleCellWater() {
    assertEquals(0, solution.numIslands(grid("0")));
  }

  @Test
  void testAllLand() {
    // Если считать «все суша → 0», тест падает: это ОДИН остров.
    assertEquals(1, solution.numIslands(grid(
        "11",
        "11")));
  }

  @Test
  void testAllWater() {
    assertEquals(0, solution.numIslands(grid(
        "000",
        "000")));
  }

  @Test
  void testDiagonalIsNotConnected() {
    // По диагонали связности нет: два отдельных острова, а не один.
    assertEquals(2, solution.numIslands(grid(
        "10",
        "01")));
  }

  @Test
  void testDiagonalsPattern() {
    assertEquals(5, solution.numIslands(grid(
        "101",
        "010",
        "101")));
  }

  @Test
  void testSingleRow() {
    // 1xN: проверяет выход за границы по горизонтали.
    assertEquals(1, solution.numIslands(grid("111")));
    assertEquals(3, solution.numIslands(grid("10101")));
    assertEquals(0, solution.numIslands(grid("000")));
  }

  @Test
  void testSingleColumn() {
    // Nx1: проверяет выход за границы по вертикали.
    assertEquals(1, solution.numIslands(grid("1", "1", "1")));
    assertEquals(2, solution.numIslands(grid("1", "0", "1")));
  }

  @Test
  void testTwoSeparateIslandsInRow() {
    assertEquals(2, solution.numIslands(grid("110011")));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    // Каждый вызов — свежая матрица: решение не хранит состояние между вызовами.
    assertEquals(1, solution.numIslands(grid("111", "111")));
    assertEquals(0, solution.numIslands(grid("000")));
    assertEquals(2, solution.numIslands(grid("10", "01")));
    assertEquals(3, solution.numIslands(grid(
        "11000",
        "11000",
        "00100",
        "00011")));
  }

  /**
   * Собирает матрицу из строк: {@code grid("110", "001")} → {@code [['1','1','0'], ['0','0','1']]}.
   */
  private static char[][] grid(String... rows) {
    char[][] result = new char[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      result[i] = rows[i].toCharArray();
    }
    return result;
  }
}
