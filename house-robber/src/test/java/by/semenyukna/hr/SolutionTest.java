package by.semenyukna.hr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(4, solution.rob(new int[]{1, 2, 3, 1}));
  }

  @Test
  void testExample2() {
    assertEquals(12, solution.rob(new int[]{2, 7, 9, 3, 1}));
  }

  @Test
  void testEmptyArray() {
    assertEquals(0, solution.rob(new int[]{}));
  }

  @Test
  void testSingleHouse() {
    assertEquals(5, solution.rob(new int[]{5}));
  }

  @Test
  void testSingleZeroHouse() {
    assertEquals(0, solution.rob(new int[]{0}));
  }

  @Test
  void testTwoHousesTakesLarger() {
    assertEquals(2, solution.rob(new int[]{2, 1}));
    assertEquals(2, solution.rob(new int[]{1, 2}));
  }

  @Test
  void testTwoEqualHouses() {
    assertEquals(7, solution.rob(new int[]{7, 7}));
  }

  @Test
  void testAllZeros() {
    assertEquals(0, solution.rob(new int[]{0, 0, 0, 0}));
  }

  @Test
  void testTakeBiggestSingleHouse() {
    // Выгоднее взять только дом 1 (7), чем 2 + 1 = 3.
    assertEquals(7, solution.rob(new int[]{2, 7, 1}));
  }

  @Test
  void testTakeFirstAndLastHouse() {
    // Дома 0 и 3 не соседи: 2 + 2 = 4 против 1 + 1 = 2.
    assertEquals(4, solution.rob(new int[]{2, 1, 1, 2}));
  }

  @Test
  void testTwoBigAtTheEnds() {
    // 100 + 100, середину пропускаем.
    assertEquals(200, solution.rob(new int[]{100, 1, 1, 100}));
  }

  @Test
  void testStrictlyIncreasing() {
    // 1 + 3 + 5 = 9 против 2 + 4 = 6.
    assertEquals(9, solution.rob(new int[]{1, 2, 3, 4, 5}));
  }

  @Test
  void testStrictlyDecreasing() {
    assertEquals(9, solution.rob(new int[]{5, 4, 3, 2, 1}));
  }

  @Test
  void testAlternatingHighLow() {
    assertEquals(15, solution.rob(new int[]{5, 1, 5, 1, 5}));
  }

  @Test
  void testLongArray() {
    int n = 1000;
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = (i % 2 == 0) ? 100 : 1;
    }
    // 500 домов по 100, все на чётных позициях — они не соседи.
    assertEquals(50000, solution.rob(nums));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(4, solution.rob(new int[]{1, 2, 3, 1}));
    assertEquals(0, solution.rob(new int[]{}));
    assertEquals(5, solution.rob(new int[]{5}));
    assertEquals(12, solution.rob(new int[]{2, 7, 9, 3, 1}));
  }
}
