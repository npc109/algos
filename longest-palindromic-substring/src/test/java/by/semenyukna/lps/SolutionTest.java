package by.semenyukna.lps;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  // Эталон: максимальная длина палиндромной подстроки полным перебором O(n^3).
  private int maxLen(String s) {
    int best = 1;
    for (int i = 0; i < s.length(); i++) {
      for (int j = i; j < s.length(); j++) {
        if (isPalindrome(s, i, j)) {
          best = Math.max(best, j - i + 1);
        }
      }
    }
    return best;
  }

  // Ответ не однозначен (babad -> aba или bab), поэтому проверяем:
  // возвращённая подстрока — палиндром максимальной длины, и она есть в s.
  private void check(String s) {
    String actual = new Solution().longestPalindrome(s);
    int expectedLen = maxLen(s);
    assertEquals(expectedLen, actual.length(),
        "длина для \"" + s + "\", получено \"" + actual + "\"");
    assertTrue(isPalindrome(actual, 0, actual.length() - 1),
        "полученная \"" + actual + "\" не палиндром, вход \"" + s + "\"");
    assertTrue(s.contains(actual),
        "полученная \"" + actual + "\" не подстрока \"" + s + "\"");
  }

  @Test
  void example1() {
    check("babad");
  }

  @Test
  void example2() {
    check("cbbd");
  }

  @Test
  void singleChar() {
    check("a");
    check("z");
  }

  @Test
  void twoSame() {
    check("bb");
    check("aa");
  }

  @Test
  void twoDifferent() {
    check("ac");
    check("ab");
  }

  @Test
  void wholeStringPalindrome() {
    // Главный регрессионный случай: dp[l][r] должен выставляться в true,
    // иначе "abcba" считается по dp[1][3] = false.
    check("abcba");
    check("abccba");
    check("racecar");
  }

  @Test
  void allSame() {
    check("aaaa");
    check("aaaaaaaaaa");
  }

  @Test
  void oddLengthCenter() {
    check("aaabaaaa");
    check("forgeeksskeegfor");
  }

  @Test
  void nestedPalindrome() {
    check("abacdfgdcaba");
  }

  @Test
  void noPalindromeLongerThanOne() {
    check("abcdef");
    check("abcd");
  }

  @Test
  void longEvenPalindrome() {
    check("forgeeksskeegfor");
  }

  @Test
  void maxLength() {
    // Ограничение: s.length <= 1000.
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 500; i++) {
      sb.append('a');
    }
    for (int i = 0; i < 500; i++) {
      sb.append('b');
    }
    check(sb.toString());
  }

  @Test
  void maxLengthAllSame() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      sb.append('x');
    }
    check(sb.toString());
  }

  @Test
  void stressRandom() {
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
  void stressRandomBinaryAlphabet() {
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
