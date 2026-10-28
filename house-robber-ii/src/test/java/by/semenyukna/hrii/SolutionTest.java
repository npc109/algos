package by.semenyukna.hrii;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

  // Эталон: перебор всех подмножеств домов с проверкой, что нет соседних
  // и что пара (0, n-1) не взята вместе — дома стоят в круг.
  private int bruteForce(int[] nums) {
    int n = nums.length;
    int best = 0;
    for (int mask = 0; mask < (1 << n); mask++) {
      boolean ok = true;
      for (int i = 0; i < n && ok; i++) {
        if ((mask & (1 << i)) != 0) {
          if (i > 0 && (mask & (1 << (i - 1))) != 0) {
            ok = false;
          }
          if (i == 0 && n > 1 && (mask & (1 << (n - 1))) != 0) {
            ok = false;
          }
        }
      }
      if (!ok) {
        continue;
      }
      int sum = 0;
      for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
          sum += nums[i];
        }
      }
      best = Math.max(best, sum);
    }
    return best;
  }

  private void check(int[] nums) {
    assertEquals(bruteForce(nums), new Solution().rob(nums), "nums = " + Arrays.toString(nums));
  }

  @Test
  void example1() {
    check(new int[]{2, 3, 2});
  }

  @Test
  void example2() {
    check(new int[]{1, 2, 3, 1});
  }

  @Test
  void example3() {
    check(new int[]{1, 2, 3});
  }

  @Test
  void singleHouse() {
    // Оба диапазона пустые: [0, -1] и [1, 0] — нужна отдельная ветка.
    check(new int[]{5});
    check(new int[]{0});
  }

  @Test
  void twoHouses() {
    // Дома 0 и 1 соседи в обе стороны; берём максимум, а не сумму.
    check(new int[]{5, 6});
    check(new int[]{1, 1});
    check(new int[]{0, 7});
  }

  @Test
  void threeHousesCircle() {
    // Круг: 2 и 2 соседи, ответ 3.
    check(new int[]{2, 3, 2});
    check(new int[]{2, 1, 2});
    check(new int[]{1, 5, 1});
  }

  @Test
  void allZeros() {
    check(new int[]{0, 0, 0, 0});
  }

  @Test
  void allEqual() {
    check(new int[]{7, 7, 7, 7, 7});
  }

  @Test
  void lastAndFirstConflict() {
    // Задача совпадает по звучанию с House Robber, но круг меняет ответ.
    check(new int[]{100, 1, 1, 100});
  }

  @Test
  void increasing() {
    check(new int[]{1, 2, 3, 4, 5});
  }

  @Test
  void decreasing() {
    check(new int[]{5, 4, 3, 2, 1});
  }

  @Test
  void alternating() {
    check(new int[]{5, 1, 5, 1, 5});
  }

  @Test
  void maxLengthAllHundreds() {
    int n = 100;
    int[] nums = new int[n];
    Arrays.fill(nums, 1000);
    int solution = new Solution().rob(nums);
    assertEquals(bruteForce(Arrays.copyOf(nums, 18)), new Solution().rob(Arrays.copyOf(nums, 18)));
    // При чётном n в круге можно взять n/2 домов не подряд.
    assertEquals(50 * 1000, solution);
  }

  @Test
  void stressRandom() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 30000; iter++) {
      int n = 1 + rnd.nextInt(10);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(21);
      }
      check(nums);
    }
  }

  @Test
  void stressRandomZeros() {
    Random rnd = new Random(7);
    for (int iter = 0; iter < 10000; iter++) {
      int n = 1 + rnd.nextInt(12);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(3);
      }
      check(nums);
    }
  }
}
