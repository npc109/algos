package by.semenyukna.ts;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

  // Эталон: перебор всех 2^n расстановок знаков по определению из условия.
  private int bruteForce(int[] nums, int target) {
    int n = nums.length;
    int count = 0;
    for (int mask = 0; mask < (1 << n); mask++) {
      int sum = 0;
      for (int i = 0; i < n; i++) {
        sum += ((mask & (1 << i)) != 0) ? nums[i] : -nums[i];
      }
      if (sum == target) {
        count++;
      }
    }
    return count;
  }

  private void check(int[] nums, int target) {
    assertEquals(bruteForce(nums, target), new Solution().findTargetSumWays(nums, target),
        "nums = " + Arrays.toString(nums) + ", target = " + target);
  }

  @Test
  void example1() {
    check(new int[]{1, 1, 1, 1, 1}, 3);
  }

  @Test
  void example2() {
    check(new int[]{1}, 1);
  }

  @Test
  void singleElementNoWay() {
    check(new int[]{1}, 2);
  }

  @Test
  void singleNegativeTarget() {
    check(new int[]{100}, -100);
  }

  @Test
  void singleZero() {
    // +0 и -0 дают два способа.
    check(new int[]{0}, 0);
  }

  @Test
  void allZeros() {
    // Все 2^5 расстановок дают 0.
    check(new int[]{0, 0, 0, 0, 0}, 0);
  }

  @Test
  void zerosNoWay() {
    check(new int[]{0, 0, 0}, 1);
  }

  @Test
  void twoEqual() {
    check(new int[]{1, 1}, 0);
  }

  @Test
  void mixed() {
    check(new int[]{1, 2, 3}, 0);
  }

  @Test
  void targetOutOfRange() {
    check(new int[]{1, 2, 3}, 100);
    check(new int[]{1, 2, 3}, -100);
  }

  @Test
  void maxTarget() {
    check(new int[]{1000, 1000, 1000}, 1000);
    check(new int[]{1000, 1000, 1000}, 3000);
  }

  @Test
  void maxLengthSmallValues() {
    int[] nums = new int[20];
    Arrays.fill(nums, 1);
    check(nums, 0);
    check(nums, 20);
  }

  @Test
  void maxLengthDoesNotTle() {
    // Без мемоизации 2^20 = 1 048 576 путей; с кэшем — O(n * sum).
    Random rnd = new Random(5);
    int[] nums = new int[20];
    for (int i = 0; i < 20; i++) {
      nums[i] = rnd.nextInt(1001);
    }
    new Solution().findTargetSumWays(nums, 0);
  }

  @Test
  void noStateLeakBetweenCalls() {
    Solution s = new Solution();
    assertEquals(5, s.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    assertEquals(0, s.findTargetSumWays(new int[]{1, 2, 3}, 100));
    assertEquals(5, s.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
  }

  @Test
  void stressRandom() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(10);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(11);
      }
      check(nums, rnd.nextInt(61) - 30);
    }
  }

  @Test
  void stressRandomSmallValues() {
    // Много нулей — важный случай для числа способов.
    Random rnd = new Random(7);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(9);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(3);
      }
      check(nums, rnd.nextInt(15) - 7);
    }
  }
}
