package by.semenyukna.kleia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Краевые случаи, которые ломают наивные реализации:
 * <ul>
 *   <li>дубликаты — ответ не обязан быть уникальным ({@code [7,7,7,7]}, k=2);</li>
 *   <li>{@code k == 1} — это максимум, а не «первый по возрастанию»;</li>
 *   <li>{@code k == n} — это минимум всего массива;</li>
 *   <li>элемент, вытесняющий из кучи равный ему ({@code [6,4,4,4,5]}, k=4).</li>
 * </ul>
 */
public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample() {
    assertEquals(5, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
  }

  @Test
  void testExampleWithDuplicates() {
    // Отсортировано: [1,2,2,3,3,4,5,5,6], 4-й по величине — 4.
    assertEquals(4, solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
  }

  @Test
  void testSingleElement() {
    assertEquals(1, solution.findKthLargest(new int[]{1}, 1));
  }

  @Test
  void testAllEqual() {
    assertEquals(7, solution.findKthLargest(new int[]{7, 7, 7, 7}, 1));
    assertEquals(7, solution.findKthLargest(new int[]{7, 7, 7, 7}, 2));
    assertEquals(7, solution.findKthLargest(new int[]{7, 7, 7, 7}, 4));
  }

  @Test
  void testKEqualsOne() {
    assertEquals(6, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 1));
  }

  @Test
  void testKEqualsN() {
    // k == n — это минимум массива.
    assertEquals(1, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 6));
  }

  @Test
  void testDuplicatesWithLargerElement() {
    // Отсортировано: [4,4,4,5,6]. При k=4 ответ 4: три копии четвёрки,
    // одна вытесняется пятёркой, но минимумом кучи остаётся 4.
    assertEquals(4, solution.findKthLargest(new int[]{6, 4, 4, 4, 5}, 4));
  }

  @Test
  void testNegativeNumbers() {
    assertEquals(-1, solution.findKthLargest(new int[]{-1, -1}, 1));
    assertEquals(-1, solution.findKthLargest(new int[]{-1, -1}, 2));
    assertEquals(2, solution.findKthLargest(new int[]{-5, 2, -3}, 1));
    assertEquals(-5, solution.findKthLargest(new int[]{-5, 2, -3}, 3));
  }

  @Test
  void testZeros() {
    assertEquals(0, solution.findKthLargest(new int[]{0, 0, 0}, 2));
  }

  @Test
  void testAlreadySortedAscending() {
    assertEquals(3, solution.findKthLargest(new int[]{1, 2, 3, 4, 5}, 3));
  }

  @Test
  void testAlreadySortedDescending() {
    assertEquals(3, solution.findKthLargest(new int[]{5, 4, 3, 2, 1}, 3));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    // Один экземпляр на несколько вызовов: куча обязана быть локальной.
    assertEquals(5, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    assertEquals(1, solution.findKthLargest(new int[]{1}, 1));
    assertEquals(7, solution.findKthLargest(new int[]{7, 7, 7, 7}, 4));
    assertEquals(6, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 1));
  }
}
