package by.semenyukna.tkf;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {

  // Проверка по определению из условия, а не по конкретному набору:
  // ответ — k РАЗНЫХ значений, и никакое значение вне ответа не может быть чаще
  // любого значения из ответа. При равных частотах годится любой набор.
  private void check(int[] nums, int k) {
    int[] actual = new Solution().topKFrequent(nums, k);

    Map<Integer, Integer> freq = new HashMap<>();
    for (int n : nums) {
      freq.merge(n, 1, Integer::sum);
    }

    assertEquals(k, actual.length, "длина ответа, nums = " + Arrays.toString(nums) + ", k = " + k);

    Set<Integer> seen = new HashSet<>();
    for (int v : actual) {
      assertTrue(freq.containsKey(v),
          "значение " + v + " отсутствует во входе: " + Arrays.toString(nums));
      assertTrue(seen.add(v),
          "значение " + v + " повторяется в ответе: " + Arrays.toString(actual));
    }

    int minInAnswer = Integer.MAX_VALUE;
    for (int v : actual) {
      minInAnswer = Math.min(minInAnswer, freq.get(v));
    }
    for (var e : freq.entrySet()) {
      if (!seen.contains(e.getKey())) {
        assertTrue(e.getValue() <= minInAnswer,
            "значение " + e.getKey() + " с частотой " + e.getValue()
                + " не попало в ответ, но чаще значения из ответа (частота " + minInAnswer + "); "
                + "nums = " + Arrays.toString(nums) + ", k = " + k
                + ", actual = " + Arrays.toString(actual));
      }
    }
  }

  @Test
  void example1() {
    check(new int[]{1, 1, 1, 2, 2, 3}, 2);
  }

  @Test
  void example2() {
    check(new int[]{1}, 1);
  }

  @Test
  void example3() {
    check(new int[]{1, 2}, 2);
  }

  @Test
  void kEqualsOne() {
    // У 1 частота 3 — самая высокая, ответ однозначен.
    check(new int[]{1, 1, 1, 2, 2, 3}, 1);
  }

  @Test
  void kEqualsUniqueCount() {
    check(new int[]{1, 1, 1, 2, 2, 3}, 3);
    check(new int[]{5, 5, 4, 4, 3, 3}, 3);
  }

  @Test
  void allSame() {
    check(new int[]{7, 7, 7, 7, 7}, 1);
  }

  @Test
  void allDistinct() {
    // Все частоты равны: любой набор из k годится.
    check(new int[]{1, 2, 3, 4, 5}, 3);
    check(new int[]{1, 2, 3, 4, 5}, 5);
    check(new int[]{1, 2, 3, 4, 5}, 1);
  }

  @Test
  void negatives() {
    check(new int[]{-1, -1, -2, -2, -2, -3}, 2);
    check(new int[]{-10000, 10000, -10000}, 1);
  }

  @Test
  void extremeValues() {
    check(new int[]{-10000, -10000, 10000, 10000, 10000, 0}, 2);
  }

  @Test
  void zeroIsAValue() {
    check(new int[]{0, 0, 1, 2}, 1);
  }

  @Test
  void uniqueHeadThenOddTail() {
    // 42 встречается 50000 раз, остальные — по одному.
    int n = 100000;
    int[] nums = new int[n];
    for (int i = 0; i < n / 2; i++) {
      nums[i] = 42;
    }
    for (int i = n / 2; i < n; i++) {
      nums[i] = i;
    }
    int[] actual = new Solution().topKFrequent(nums, 1);
    assertEquals(42, actual[0]);
  }

  @Test
  void stressRandom() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 20000; iter++) {
      int n = 1 + rnd.nextInt(15);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(6) - 3;
      }
      int unique = (int) Arrays.stream(nums).distinct().count();
      check(nums, 1 + rnd.nextInt(unique));
    }
  }

  @Test
  void stressRandomDistinctCount() {
    Random rnd = new Random(7);
    for (int iter = 0; iter < 5000; iter++) {
      int n = 1 + rnd.nextInt(30);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = rnd.nextInt(21);
      }
      int unique = (int) Arrays.stream(nums).distinct().count();
      check(nums, 1 + rnd.nextInt(unique));
    }
  }
}
