package by.semenyukna.combinations;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private long binomial(int n, int k) {
    long r = 1;
    for (int i = 0; i < k; i++) {
      r = r * (n - i) / (i + 1);
    }
    return r;
  }

  // Порядок в ответе по условию не определён — сравниваем множество.
  private void check(int n, int k) {
    List<List<Integer>> actual = new Solution().combine(n, k);

    assertEquals(binomial(n, k), actual.size(),
        "число комбинаций C(" + n + "," + k + ")");

    Set<List<Integer>> seen = new HashSet<>();
    for (List<Integer> c : actual) {
      assertEquals(k, c.size(), "длина комбинации " + c + ", n = " + n + ", k = " + k);
      List<Integer> sorted = new ArrayList<>(c);
      java.util.Collections.sort(sorted);
      assertEquals(sorted, c, "элементы комбинации " + c + " не по возрастанию");
      for (int i = 0; i < c.size(); i++) {
        int v = c.get(i);
        assertEquals(true, v >= 1 && v <= n, "значение " + v + " вне [1.." + n + "]");
        if (i > 0) {
          assertEquals(true, c.get(i - 1) < v, "повтор значения в комбинации " + c);
        }
      }
      assertEquals(true, seen.add(List.copyOf(c)), "комбинация " + c + " повторяется");
    }
  }

  @Test
  void example1() {
    check(4, 3);
  }

  @Test
  void example2() {
    check(1, 1);
  }

  @Test
  void kEqualsOne() {
    check(5, 1);
    check(20, 1);
  }

  @Test
  void kEqualsN() {
    check(5, 5);
    check(1, 1);
  }

  @Test
  void kEqualsNMinusOne() {
    check(5, 4);
  }

  @Test
  void twoOfThree() {
    check(3, 2);
  }

  @Test
  void maxN() {
    // Ограничение: n <= 20.
    check(20, 10);
  }

  @Test
  void maxNHalfRange() {
    check(20, 5);
  }

  @Test
  void middleValues() {
    check(6, 3);
    check(7, 4);
  }

  @Test
  void stressRandom() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 300; iter++) {
      int n = 1 + rnd.nextInt(16);
      int k = 1 + rnd.nextInt(n);
      check(n, k);
    }
  }
}
