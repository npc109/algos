package by.semenyukna.pess;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

  // Эталон: полный перебор подмножеств по определению из условия.
  private boolean bruteForce(int[] nums) {
    int total = 0;
    for (int n : nums) {
      total += n;
    }
    if (total % 2 != 0) {
      return false;
    }
    int target = total / 2;
    int n = nums.length;
    for (int mask = 0; mask < (1 << n); mask++) {
      int sum = 0;
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
          sum += nums[i];
        }
      }
      if (sum == target) {
        return true;
      }
    }
    return false;
  }

  private void check(int[] nums) {
    assertEquals(bruteForce(nums), new Solution().canPartition(nums),
        "nums = " + Arrays.toString(nums));
  }

  @Test
  void example1() {
    check(new int[]{1, 5, 11, 5});
  }

  @Test
  void example2() {
    check(new int[]{1, 2, 3, 5});
  }

  @Test
  void singleElement() {
    check(new int[]{1});
    check(new int[]{2});
  }

  @Test
  void twoEqual() {
    check(new int[]{1, 1});
    check(new int[]{100, 100});
  }

  @Test
  void twoDifferent() {
    check(new int[]{1, 2});
  }

  @Test
  void oddTotal() {
    // Нечётная сумма — сразу false, независимо от элементов.
    check(new int[]{1, 2, 4});
    check(new int[]{1, 1, 1});
    check(new int[]{1, 2, 5});
  }

  @Test
  void allEqualEvenCount() {
    check(new int[]{1, 1, 1, 1});
    check(new int[]{7, 7, 7, 7});
  }

  @Test
  void allEqualOddCount() {
    check(new int[]{1, 1, 1});
    check(new int[]{7, 7, 7});
  }

  @Test
  void greedyCounterexample() {
    // Жадность «брать крупные» даёт ложный false: 8+7 = 15 и 6+5+4 = 15.
    check(new int[]{8, 7, 6, 5, 4});
  }

  @Test
  void greedyCounterexampleTwo() {
    // 2+2+2 = 6 и 3+3 = 6.
    check(new int[]{2, 2, 2, 3, 3});
  }

  @Test
  void oneElementDominates() {
    // [1,2,5] сумма 8, цель 4: не набирается.
    check(new int[]{1, 1, 2, 4});
  }

  @Test
  void requiresSecondSubset() {
    check(new int[]{1, 3, 4, 4});
  }

  @Test
  void maxValueMaxCount() {
    // 200 элементов по 100 — сумма 20000, цель 10000.
    int[] nums = new int[200];
    Arrays.fill(nums, 100);
    assertEquals(true, new Solution().canPartition(nums));
  }

  @Test
  void maxValueOddCount() {
    // 199 по 100 — сумма 19900, цель 9950: нечётная, false.
    int[] nums = new int[199];
    Arrays.fill(nums, 100);
    assertEquals(false, new Solution().canPartition(nums));
  }

  @Test
  void largeInputDoesNotTle() {
    int[] nums = new int[200];
    Random rnd = new Random(3);
    for (int i = 0; i < nums.length; i++) {
      nums[i] = 1 + rnd.nextInt(100);
    }
    int total = 0;
    for (int v : nums) {
      total += v;
    }
    if (total % 2 == 0) {
      new Solution().canPartition(nums);
    }
    // Главное — не упасть по времени: O(n * sum/2) <= 200 * 10000.
    nums[0] = total % 2 == 0 ? nums[0] : nums[0] + 1;
    new Solution().canPartition(nums);
  }

  @Test
  void stressRandom() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 30000; iter++) {
      int n = 1 + rnd.nextInt(12);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = 1 + rnd.nextInt(20);
      }
      check(nums);
    }
  }

  @Test
  void stressRandomWithDuplicates() {
    // Дубликаты — главный подозрительный случай для сжатия dp в одномерный.
    Random rnd = new Random(7);
    for (int iter = 0; iter < 30000; iter++) {
      int n = 1 + rnd.nextInt(12);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = 1 + rnd.nextInt(4);
      }
      check(nums);
    }
  }

  @Test
  void stressRandomFibonacciValues() {
    // Значения, неудобные для жадности.
    int[] values = {1, 2, 3, 5, 8, 13, 21};
    Random rnd = new Random(11);
    for (int iter = 0; iter < 10000; iter++) {
      int n = 1 + rnd.nextInt(10);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = values[rnd.nextInt(values.length)];
      }
      check(nums);
    }
  }
}
