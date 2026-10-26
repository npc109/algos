package by.semenyukna.lls;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

  // Эталон: O(n^2) DP по определению из условия.
  private int bruteForce(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    int[] dp = new int[n];
    int best = 1;
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      best = Math.max(best, dp[i]);
    }
    return best;
  }

  private void check(int[] nums) {
    assertEquals(bruteForce(nums), new Solution().lengthOfLIS(nums),
        "nums = " + java.util.Arrays.toString(nums));
  }

  @Test
  void example1() {
    check(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
  }

  @Test
  void example2() {
    check(new int[]{0, 1, 0, 3, 2, 3});
  }

  @Test
  void example3() {
    check(new int[]{7, 7, 7, 7, 7, 7, 7});
  }

  @Test
  void singleElement() {
    check(new int[]{5});
  }

  @Test
  void twoEqual() {
    check(new int[]{4, 4});
  }

  @Test
  void twoAscending() {
    check(new int[]{4, 5});
  }

  @Test
  void twoDescending() {
    check(new int[]{5, 4});
  }

  @Test
  void strictlyAscending() {
    check(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
  }

  @Test
  void strictlyDescending() {
    check(new int[]{8, 7, 6, 5, 4, 3, 2, 1});
  }

  @Test
  void duplicatesInterleaved() {
    check(new int[]{4, 10, 4, 3, 8, 9});
  }

  @Test
  void extremes() {
    check(new int[]{-10000, 10000, -10000, 10000});
  }

  @Test
  void alternating() {
    check(new int[]{1, 3, 2, 4, 3, 5, 4, 6});
  }

  @Test
  void stressRandomSmallValues() {
    Random rnd = new Random(42);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(12);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(7) - 3;
      }
      check(nums);
    }
  }

  @Test
  void stressRandomWideRange() {
    Random rnd = new Random(7);
    for (int iter = 0; iter < 5000; iter++) {
      int n = 1 + rnd.nextInt(30);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(20001) - 10000;
      }
      check(nums);
    }
  }
}
