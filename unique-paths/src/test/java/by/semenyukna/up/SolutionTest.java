package by.semenyukna.up;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTest {

  // Эталон: число путей по определению — C(m + n - 2, m - 1).
  // Через BigInteger: треугольник Паскаля на long переполняется уже на (8, 70).
  private BigInteger expectedPaths(int m, int n) {
    int total = m + n - 2;
    int k = m - 1;
    BigInteger r = BigInteger.ONE;
    for (int i = 0; i < k; i++) {
      r = r.multiply(BigInteger.valueOf(total - i)).divide(BigInteger.valueOf(i + 1));
    }
    return r;
  }

  private void check(int m, int n) {
    BigInteger expected = expectedPaths(m, n);
    assertTrue(expected.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0,
        "эталон для m = " + m + ", n = " + n + " не влезает в int: " + expected);
    assertEquals(expected.intValueExact(), new Solution().uniquePaths(m, n),
        "m = " + m + ", n = " + n);
  }

  @Test
  void example1() {
    check(3, 7);
  }

  @Test
  void example2() {
    check(3, 2);
  }

  @Test
  void oneByOne() {
    check(1, 1);
  }

  @Test
  void singleRow() {
    check(1, 2);
    check(1, 5);
    check(1, 100);
  }

  @Test
  void singleColumn() {
    check(2, 1);
    check(5, 1);
    check(100, 1);
  }

  @Test
  void twoByTwo() {
    check(2, 2);
  }

  @Test
  void valueAtFiveByFive() {
    // C(8, 4) = 70
    check(5, 5);
  }

  @Test
  void symmetric() {
    // Пути симметричны относительно транспонирования: C(m+n-2, m-1) = C(m+n-2, n-1).
    for (int m = 1; m <= 12; m++) {
      for (int n = 1; n <= 12; n++) {
        assertEquals(new Solution().uniquePaths(m, n), new Solution().uniquePaths(n, m),
            "m = " + m + ", n = " + n);
      }
    }
  }

  @Test
  void allGridsWithinInt() {
    // Проверяем каждую сетку до 100x100, где ответ влезает в int.
    // Первая сетка, где int переполняется, — m = 8, n = 70.
    int checked = 0;
    for (int m = 1; m <= 100; m++) {
      for (int n = 1; n <= 100; n++) {
        if (expectedPaths(m, n).bitLength() > 31) {
          continue;
        }
        check(m, n);
        checked++;
      }
    }
    assertTrue(checked > 1000, "проверено всего " + checked + " сеток — эталон сломан");
  }

  @Test
  void boundaryJustBelowInt() {
    // C(49, 9) = 2 054 455 634 — максимальное значение, влезающее в int
    // среди сеток до 100x100 (m = 10, n = 41, транспонировано).
    BigInteger v = expectedPaths(10, 41);
    assertEquals(2054455634, v.intValueExact());
    check(10, 41);
    check(41, 10);
  }

  @Test
  void largeGridDoesNotTle() {
    // Мемоизация даёт O(m*n) посчитанных состояний; без неё это C(198,99) вызовов.
    assertTimeoutPreemptively(Duration.ofSeconds(2), () -> new Solution().uniquePaths(100, 100));
  }

  @Test
  void noStateLeakBetweenCalls() {
    Solution s = new Solution();
    assertEquals(28, s.uniquePaths(3, 7));
    assertEquals(1, s.uniquePaths(1, 1));
    assertEquals(3, s.uniquePaths(3, 2));
    assertEquals(28, s.uniquePaths(3, 7));
  }
}
