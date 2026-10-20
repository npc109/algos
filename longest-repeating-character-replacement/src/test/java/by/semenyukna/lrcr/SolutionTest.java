package by.semenyukna.lrcr;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(4, solution.characterReplacement("ABAB", 2));
  }

  @Test
  void testExample2() {
    assertEquals(4, solution.characterReplacement("AABABBA", 1));
  }

  @Test
  void testAllSameNoReplacementNeeded() {
    assertEquals(4, solution.characterReplacement("AAAA", 0));
  }

  @Test
  void testAllDifferentK1() {
    assertEquals(2, solution.characterReplacement("ABCDE", 1));
  }

  @Test
  void testAllDifferentK0() {
    assertEquals(1, solution.characterReplacement("ABCDEFGHIJKLMNOP", 0));
  }

  @Test
  void testSingleChar() {
    assertEquals(1, solution.characterReplacement("A", 0));
    assertEquals(1, solution.characterReplacement("A", 1));
  }

  @Test
  void testTwoChars() {
    assertEquals(1, solution.characterReplacement("AB", 0));
    assertEquals(2, solution.characterReplacement("AB", 1));
  }

  @Test
  void testTripleAtEnd() {
    assertEquals(4, solution.characterReplacement("ABBB", 2));
  }

  @Test
  void testTripleInMiddle() {
    assertEquals(5, solution.characterReplacement("BAAAB", 2));
  }

  @Test
  void testTwoRunsOneReplacementEach() {
    assertEquals(2, solution.characterReplacement("AABABBA", 0));
  }

  @Test
  void testWindowLongerThanK() {
    assertEquals(6, solution.characterReplacement("ABAAAB", 2));
  }

  @Test
  void testLargeMixedString() {
    assertEquals(7, solution.characterReplacement("EOEMQLLQTRQDDCOERARHGAAARR", 3));
  }

  @Test
  void testKExceedsLength() {
    assertEquals(5, solution.characterReplacement("ABCDE", 10));
  }

  @Test
  void testAlternatingWithBigK() {
    assertEquals(6, solution.characterReplacement("ABABAB", 3));
  }

  @Test
  void testDenseRepeats() {
    assertEquals(7, solution.characterReplacement("AAABAAA", 1));
  }

  @Test
  void testAgainstBruteForce() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 2000; iter++) {
      int len = rnd.nextInt(8);
      int alphabet = rnd.nextInt(3) + 1;
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < len; i++) {
        sb.append((char) ('A' + rnd.nextInt(alphabet)));
      }
      String s = sb.toString();
      int k = rnd.nextInt(3);
      assertEquals(bruteForce(s, k), solution.characterReplacement(s, k),
          "s=" + s + " k=" + k);
    }
  }

  /** Эталон: перебор всех подстрок, для каждой — длина минус частота самого частого символа. */
  private int bruteForce(String s, int k) {
    int best = 0;
    for (int i = 0; i < s.length(); i++) {
      Map<Character, Integer> freq = new HashMap<>();
      for (int j = i; j < s.length(); j++) {
        freq.merge(s.charAt(j), 1, Integer::sum);
        int len = j - i + 1;
        int maxFreq = 0;
        for (int f : freq.values()) {
          maxFreq = Math.max(maxFreq, f);
        }
        if (len - maxFreq <= k) {
          best = Math.max(best, len);
        }
      }
    }
    return best;
  }
}
