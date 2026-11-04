package by.semenyukna.ps;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

  private boolean isPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  // Эталон: считаем все подстроки длиной >= 1 и проверяем каждую по определению.
  // O(n^3) — годится только для маленьких входов, поэтому стресс ограничен.
  private int bruteForce(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      for (int j = i; j < s.length(); j++) {
        if (isPalindrome(s, i, j)) {
          count++;
        }
      }
    }
    return count;
  }

  private void check(String s) {
    assertEquals(bruteForce(s), new Solution().countSubstrings(s), "s = \"" + s + "\"");
  }

  @Test
  void example1() {
    check("abc");
  }

  @Test
  void example2() {
    check("aaa");
  }

  @Test
  void singleChar() {
    check("a");
    check("z");
  }

  @Test
  void twoSame() {
    check("aa");
  }

  @Test
  void twoDifferent() {
    check("ab");
  }

  @Test
  void allSameFour() {
    // "a","a","a","a","aa","aa","aaa","aaaa" = 10
    check("aaaa");
  }

  @Test
  void allSameTen() {
    // n*(n+1)/2 = 55
    assertEquals(55, new Solution().countSubstrings("aaaaaaaaaa"));
  }

  @Test
  void oddPalindromesOnly() {
    check("abcba");
  }

  @Test
  void evenPalindromesOnly() {
    check("cbbd");
  }

  @Test
  void nestedPalindromes() {
    check("abacdfgdcaba");
  }

  @Test
  void noRepeats() {
    check("abcdefgh");
  }

  @Test
  void maxLengthAllSame() {
    // Ограничение s.length <= 1000, все символы одинаковые.
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      sb.append('a');
    }
    assertEquals(1000 * 1001 / 2, new Solution().countSubstrings(sb.toString()));
  }

  @Test
  void maxLengthNoPalindromes() {
    // Разные символы: каждая подстрока длины >= 2 не палиндром.
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      sb.append((char) ('a' + i));
    }
    assertEquals(26, new Solution().countSubstrings(sb.toString()));
  }

  @Test
  void stressRandomTernary() {
    Random rnd = new Random(2024);
    StringBuilder sb = new StringBuilder();
    for (int iter = 0; iter < 20000; iter++) {
      sb.setLength(0);
      int n = 1 + rnd.nextInt(12);
      for (int i = 0; i < n; i++) {
        sb.append((char) ('a' + rnd.nextInt(3)));
      }
      check(sb.toString());
    }
  }

  @Test
  void stressRandomBinary() {
    Random rnd = new Random(7);
    StringBuilder sb = new StringBuilder();
    for (int iter = 0; iter < 20000; iter++) {
      sb.setLength(0);
      int n = 1 + rnd.nextInt(16);
      for (int i = 0; i < n; i++) {
        sb.append((char) ('a' + rnd.nextInt(2)));
      }
      check(sb.toString());
    }
  }
}
