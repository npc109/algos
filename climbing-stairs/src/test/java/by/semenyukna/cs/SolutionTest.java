package by.semenyukna.cs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

  // Эталон: число способов по определению — перебор всех последовательностей шагов 1 и 2.
  // Наивная рекурсия без мемоизации — экспоненциальная, поэтому кэш обязателен даже в эталоне.
  private int bruteForce(int n) {
    int[] cache = new int[n + 2];
    Arrays.fill(cache, -1);
    return ways(n, cache);
  }

  private int ways(int n, int[] cache) {
    if (n == 0) {
      return 1;
    }
    if (n < 0) {
      return 0;
    }
    if (cache[n] >= 0) {
      return cache[n];
    }
    cache[n] = ways(n - 1, cache) + ways(n - 2, cache);
    return cache[n];
  }

  private void check(int n) {
    assertEquals(bruteForce(n), new Solution().climbStairs(n), "n = " + n);
  }

  @Test
  void example1() {
    check(2);
  }

  @Test
  void example2() {
    check(3);
  }

  @Test
  void nEqualsOne() {
    check(1);
  }

  @Test
  void smallValues() {
    for (int n = 1; n <= 12; n++) {
      check(n);
    }
  }

  @Test
  void nEqualsFive() {
    // 11111, 1112, 1121, 1211, 2111, 122, 212, 221
    assertEquals(8, new Solution().climbStairs(5));
  }

  @Test
  void nEqualsFortyFive() {
    // Верхняя граница ограничения; в int влезает с запасом 310 млн.
    assertEquals(1836311903, new Solution().climbStairs(45));
  }

  @Test
  void nEqualsFortyFour() {
    assertEquals(1134903170, new Solution().climbStairs(44));
  }

  @Test
  void fibonacciShift() {
    // climbStairs(n) == fib(n + 1), где fib(0) = 0, fib(1) = 1.
    // fib[n] считаем в массиве, чтобы индекс был явным и не зависел от сдвига переменных.
    int[] fib = new int[47];
    fib[1] = 1;
    for (int i = 2; i < fib.length; i++) {
      fib[i] = fib[i - 1] + fib[i - 2];
    }
    for (int n = 1; n <= 45; n++) {
      assertEquals(fib[n + 1], new Solution().climbStairs(n), "n = " + n);
    }
  }

  @Test
  void mediumValues() {
    for (int n = 13; n <= 30; n++) {
      check(n);
    }
  }

  @Test
  void noStateLeakBetweenCalls() {
    Solution s = new Solution();
    assertEquals(1, s.climbStairs(1));
    assertEquals(1836311903, s.climbStairs(45));
    assertEquals(2, s.climbStairs(2));
    assertEquals(8, s.climbStairs(5));
  }
}
