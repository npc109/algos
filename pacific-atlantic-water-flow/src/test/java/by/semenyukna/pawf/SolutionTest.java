package by.semenyukna.pawf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Порядок клеток в ответе не определён, поэтому результат нормализуется перед сравнением.
 */
public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(
        norm(List.of(List.of(0, 4), List.of(1, 3), List.of(1, 4), List.of(2, 2),
            List.of(3, 0), List.of(3, 1), List.of(4, 0))),
        norm(solution.pacificAtlantic(new int[][]{
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}})));
  }

  @Test
  void testSingleCell() {
    assertEquals(norm(List.of(List.of(0, 0))),
        norm(solution.pacificAtlantic(new int[][]{{1}})));
  }

  @Test
  void testTwoByTwo() {
    // (0,0) не доходит до Атлантического: вода не может подняться с 1 до 3 или 2.
    assertEquals(
        norm(List.of(List.of(0, 1), List.of(1, 0), List.of(1, 1))),
        norm(solution.pacificAtlantic(new int[][]{{1, 2}, {3, 4}})));
  }

  @Test
  void testAllEqual() {
    assertEquals(
        norm(List.of(List.of(0, 0), List.of(0, 1), List.of(0, 2),
            List.of(1, 0), List.of(1, 1), List.of(1, 2),
            List.of(2, 0), List.of(2, 1), List.of(2, 2))),
        norm(solution.pacificAtlantic(new int[][]{
            {3, 3, 3},
            {3, 3, 3},
            {3, 3, 3}})));
  }

  @Test
  void testSingleRow() {
    assertEquals(
        norm(List.of(List.of(0, 0), List.of(0, 1), List.of(0, 2), List.of(0, 3))),
        norm(solution.pacificAtlantic(new int[][]{{1, 2, 3, 4}})));
  }

  @Test
  void testSingleColumn() {
    assertEquals(
        norm(List.of(List.of(0, 0), List.of(1, 0), List.of(2, 0), List.of(3, 0))),
        norm(solution.pacificAtlantic(new int[][]{{1}, {2}, {3}, {4}})));
  }

  @Test
  void testCenterIsHighest() {
    assertEquals(
        norm(List.of(List.of(0, 2), List.of(1, 0), List.of(1, 1), List.of(1, 2),
            List.of(2, 0), List.of(2, 1), List.of(2, 2))),
        norm(solution.pacificAtlantic(new int[][]{
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}})));
  }

  @Test
  void testIncreasingToTheRight() {
    // Вода не может подниматься вправо и вверх: до Атлантического дотягиваются только
    // правый столбец и нижняя строка, до Тихого — верхняя строка и левый столбец.
    assertEquals(
        norm(List.of(List.of(0, 2), List.of(1, 2), List.of(2, 0), List.of(2, 1), List.of(2, 2))),
        norm(solution.pacificAtlantic(new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}})));
  }

  @Test
  void testDecreasingToTheRight() {
    assertEquals(
        norm(List.of(List.of(0, 0), List.of(0, 1), List.of(0, 2), List.of(1, 0), List.of(2, 0))),
        norm(solution.pacificAtlantic(new int[][]{
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}})));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(norm(List.of(List.of(0, 0))),
        norm(solution.pacificAtlantic(new int[][]{{1}})));
    assertEquals(
        norm(List.of(List.of(0, 0), List.of(0, 1), List.of(1, 0), List.of(1, 1))),
        norm(solution.pacificAtlantic(new int[][]{{5, 5}, {5, 5}})));
    assertEquals(
        norm(List.of(List.of(0, 1), List.of(1, 0), List.of(1, 1))),
        norm(solution.pacificAtlantic(new int[][]{{1, 2}, {3, 4}})));
  }

  private static String norm(List<List<Integer>> cells) {
    List<String> pairs = new ArrayList<>();
    for (List<Integer> cell : cells) {
      pairs.add(cell.get(0) + "," + cell.get(1));
    }
    Collections.sort(pairs);
    return pairs.toString();
  }
}
