package by.semenyukna.perm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

  // Порядок перестановок в ответе не определён условием, поэтому сравниваем множества.
  private void check(int[] nums) {
    List<List<Integer>> actual = new Solution().permute(nums);

    long expectedCount = 1;
    for (int i = 2; i <= nums.length; i++) {
      expectedCount *= i;
    }
    assertEquals(expectedCount, actual.size(),
        "число перестановок, nums = " + Arrays.toString(nums));

    Set<List<Integer>> seen = new HashSet<>();
    Set<Integer> source = new HashSet<>();
    for (int v : nums) {
      source.add(v);
    }

    for (List<Integer> p : actual) {
      assertEquals(nums.length, p.size(),
          "длина перестановки " + p + " для nums = " + Arrays.toString(nums));
      assertEquals(source, new HashSet<>(p),
          "перестановка " + p + " не является перестановкой " + Arrays.toString(nums));
      assertEquals(true, seen.add(List.copyOf(p)),
          "перестановка " + p + " повторяется, nums = " + Arrays.toString(nums));
    }
  }

  @Test
  void example1() {
    check(new int[]{1, 2, 3});
  }

  @Test
  void example2() {
    check(new int[]{0, 1});
  }

  @Test
  void example3() {
    check(new int[]{1});
  }

  @Test
  void singleZero() {
    check(new int[]{0});
  }

  @Test
  void negatives() {
    check(new int[]{-1, 0, 1});
    check(new int[]{-10, 10});
  }

  @Test
  void extremes() {
    check(new int[]{-10, 0, 10});
  }

  @Test
  void lengthFour() {
    check(new int[]{1, 2, 3, 4});
  }

  @Test
  void lengthFive() {
    check(new int[]{1, 2, 3, 4, 5});
  }

  @Test
  void maxLength() {
    // Ограничение задачи: 1 <= nums.length <= 6.
    check(new int[]{1, 2, 3, 4, 5, 6});
  }

  @Test
  void maxLengthWithNegatives() {
    check(new int[]{-10, -3, 0, 2, 7, 10});
  }

  @Test
  void doesNotMutateInput() {
    int[] nums = {3, 1, 2};
    new Solution().permute(nums);
    assertEquals("[3, 1, 2]", Arrays.toString(nums));
  }

  @Test
  void stressRandom() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 200; iter++) {
      int n = 1 + rnd.nextInt(6);
      List<Integer> pool = new ArrayList<>();
      for (int i = -10; i <= 10; i++) {
        pool.add(i);
      }
      java.util.Collections.shuffle(pool, rnd);
      int[] nums = new int[n];
      for (int i = 0; i < n; i++) {
        nums[i] = pool.get(i);
      }
      check(nums);
    }
  }
}
