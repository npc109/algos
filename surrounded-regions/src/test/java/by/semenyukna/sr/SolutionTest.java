package by.semenyukna.sr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    char[][] board = board("XXXX", "XOOX", "XXOX", "XOXX");
    solution.solve(board);
    assertArrayEquals(board("XXXX", "XXXX", "XXXX", "XOXX"), board);
  }

  @Test
  void testExample2() {
    char[][] board = board("X");
    solution.solve(board);
    assertArrayEquals(board("X"), board);
  }

  @Test
  void testSingleSurvivingCell() {
    char[][] board = board("O");
    solution.solve(board);
    assertArrayEquals(board("O"), board);
  }

  @Test
  void testEverythingCaptured() {
    // Область в центре не соединена с краем — захватывается целиком.
    char[][] board = board("XXXX", "XOOX", "XXOX", "XXXX");
    solution.solve(board);
    assertArrayEquals(board("XXXX", "XXXX", "XXXX", "XXXX"), board);
  }

  @Test
  void testEverythingSurvives() {
    char[][] board = board("OOO", "OOO", "OOO");
    solution.solve(board);
    assertArrayEquals(board("OOO", "OOO", "OOO"), board);
  }

  @Test
  void testEverythingIsX() {
    char[][] board = board("XXX", "XXX");
    solution.solve(board);
    assertArrayEquals(board("XXX", "XXX"), board);
  }

  @Test
  void testSingleRow() {
    char[][] board = board("OOOO");
    solution.solve(board);
    assertArrayEquals(board("OOOO"), board);
  }

  @Test
  void testSingleColumn() {
    char[][] board = board("O", "O", "O");
    solution.solve(board);
    assertArrayEquals(board("O", "O", "O"), board);
  }

  @Test
  void testCornerSurvives() {
    char[][] board = board("OXX", "XXX", "XXX");
    solution.solve(board);
    assertArrayEquals(board("OXX", "XXX", "XXX"), board);
  }

  @Test
  void testHoleConnectedToEdge() {
    // (1,1),(1,2),(2,2) соединены с (3,1),(3,2) на нижнем краю — выживают все.
    char[][] board = board("XXXX", "XOOX", "XXOX", "XOOX");
    solution.solve(board);
    assertArrayEquals(board("XXXX", "XOOX", "XXOX", "XOOX"), board);
  }

  @Test
  void testBigEnclosedRegion() {
    char[][] board = board("XXXXX", "XOOOX", "XOOOX", "XOOOX", "XXXXX");
    solution.solve(board);
    assertArrayEquals(board("XXXXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX"), board);
  }

  @Test
  void testChainFromEdgeIntoCenter() {
    // Путь 'O' тянется от верхнего края в центр — все они выживают.
    char[][] board = board("XOX", "XOX", "XXX");
    solution.solve(board);
    assertArrayEquals(board("XOX", "XOX", "XXX"), board);
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    char[][] first = board("XXXX", "XOOX", "XXOX", "XOXX");
    solution.solve(first);
    assertArrayEquals(board("XXXX", "XXXX", "XXXX", "XOXX"), first);

    char[][] second = board("O");
    solution.solve(second);
    assertArrayEquals(board("O"), second);

    char[][] third = board("XXXX", "XOOX", "XXOX", "XXXX");
    solution.solve(third);
    assertArrayEquals(board("XXXX", "XXXX", "XXXX", "XXXX"), third);
  }

  private static char[][] board(String... rows) {
    char[][] result = new char[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      result[i] = rows[i].toCharArray();
    }
    return result;
  }
}
