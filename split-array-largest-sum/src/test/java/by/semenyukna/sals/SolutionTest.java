package by.semenyukna.sals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class SolutionTest {

  // Эталон: полный перебор всех k-1 разрезов по определению из условия.
  private int bruteForce(int[] nums, int k) {
    int n = nums.length;
    long[] prefix = new long[n + 1];
    for (int i = 0; i < n; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }
    int best = Integer.MAX_VALUE;
    int[] cut = new int[k + 1];
    for (int mask = 0; mask < (1 << (n - 1)); mask++) {
      if (Integer.bitCount(mask) != k - 1) {
        continue;
      }
      int idx = 1;
      cut[0] = 0;
      cut[k] = n;
      for (int i = 0; i < n - 1; i++) {
        if ((mask & (1 << i)) != 0) {
          cut[idx++] = i + 1;
        }
      }
      long maxSum = 0;
      for (int i = 0; i < k; i++) {
        maxSum = Math.max(maxSum, prefix[cut[i + 1]] - prefix[cut[i]]);
      }
      best = (int) Math.min(best, maxSum);
    }
    return best;
  }

  private void check(int[] nums, int k) {
    assertEquals(bruteForce(nums, k), new Solution().splitArray(nums, k),
        "nums = " + Arrays.toString(nums) + ", k = " + k);
  }

  @Test
  void example1() {
    check(new int[]{7, 2, 5, 10, 8}, 2);
  }

  @Test
  void example2() {
    check(new int[]{1, 2, 3, 4, 5}, 2);
  }

  @Test
  void example3() {
    check(new int[]{1, 4, 4}, 3);
  }

  @Test
  void singleElement() {
    check(new int[]{5}, 1);
  }

  @Test
  void kEqualsOne() {
    // Верхняя граница sum(nums): разбиение на один отрезок.
    check(new int[]{7, 2, 5, 10, 8}, 1);
  }

  @Test
  void kEqualsLength() {
    // Нижняя граница max(nums): каждый элемент — отдельный отрезок.
    check(new int[]{7, 2, 5, 10, 8}, 5);
  }

  @Test
  void kEqualsLengthThree() {
    check(new int[]{1, 4, 4}, 3);
  }

  @Test
  void zerosOnly() {
    // ans = 0, l = r = 0, цикл не выполняется ни разу.
    check(new int[]{0, 0, 0}, 3);
    check(new int[]{0}, 1);
    check(new int[]{0, 0, 0, 0}, 1);
  }

  @Test
  void zerosAndValues() {
    check(new int[]{0, 5, 0, 5, 0}, 2);
    check(new int[]{0, 5, 0, 5, 0}, 3);
  }

  @Test
  void allEqual() {
    check(new int[]{4, 4, 4, 4}, 2);
    check(new int[]{4, 4, 4, 4}, 3);
  }

  @Test
  void increasingStrict() {
    check(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 3);
  }

  @Test
  void decreasingStrict() {
    check(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, 3);
  }

  @Test
  void greedyPrefixWouldFail() {
    // Жадное «закрыть отрезок как можно раньше» даёт 6, оптимум 5.
    check(new int[]{5, 6, 5}, 2);
  }

  @Test
  void largeValueSinglePile() {
    check(new int[]{1000000}, 1);
  }

  @Test
  void maxSumNearInt() {
    // 1000 элементов по 10^6 — сумма 10^9, всё ещё влезает в int.
    int n = 1000;
    int[] nums = new int[n];
    Arrays.fill(nums, 1000000);
    assertEquals(1000000, new Solution().splitArray(nums, n));
    assertEquals(1000000000, new Solution().splitArray(nums, 1));
  }

  @Test
  void maxSumManyOnes() {
    int n = 1000;
    int[] nums = new int[n];
    Arrays.fill(nums, 1);
    assertEquals(500, new Solution().splitArray(nums, 2));
  }

  @Test
  void performanceLargeInput() {
    int n = 1000;
    int[] nums = new int[n];
    Random rnd = new Random(5);
    for (int i = 0; i < n; i++) {
      nums[i] = rnd.nextInt(1000000);
    }
    assertTimeoutPreemptively(java.time.Duration.ofSeconds(2),
        () -> new Solution().splitArray(nums, 500));
  }

  @Test
  void stressAllSplits() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(9);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(21);
      }
      check(nums, 1 + rnd.nextInt(n));
    }
  }

  @Test
  void stressSmallValuesBiasedToDuplicates() {
    Random rnd = new Random(7);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(10);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(4);
      }
      check(nums, 1 + rnd.nextInt(n));
    }
  }
}
