package by.semenyukna.lswrc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
  }

  @Test
  void testExample2() {
    assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
  }

  @Test
  void testExample3() {
    assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
  }

  @Test
  void testEmpty() {
    assertEquals(0, solution.lengthOfLongestSubstring(""));
  }

  @Test
  void testSingleChar() {
    assertEquals(1, solution.lengthOfLongestSubstring("a"));
  }

  @Test
  void testAllUnique() {
    assertEquals(5, solution.lengthOfLongestSubstring("abcde"));
  }

  @Test
  void testAllSame() {
    assertEquals(1, solution.lengthOfLongestSubstring("aaaaaaa"));
  }

  @Test
  void testTwoChars() {
    assertEquals(2, solution.lengthOfLongestSubstring("ab"));
    assertEquals(1, solution.lengthOfLongestSubstring("aa"));
  }

  @Test
  void testRepeatAtEnd() {
    assertEquals(3, solution.lengthOfLongestSubstring("dvdf"));
  }

  @Test
  void testRepeatAtStart() {
    assertEquals(2, solution.lengthOfLongestSubstring("abba"));
  }

  @Test
  void testLastCharIsDuplicate() {
    assertEquals(3, solution.lengthOfLongestSubstring("abcb"));
  }

  @Test
  void testSpacesAndSymbols() {
    assertEquals(1, solution.lengthOfLongestSubstring(" "));
    assertEquals(4, solution.lengthOfLongestSubstring("a b!a"));
  }

  @Test
  void testLongerRunAfterShrink() {
    assertEquals(5, solution.lengthOfLongestSubstring("tmmzuxt"));
  }

  @Test
  void testAlternating() {
    assertEquals(2, solution.lengthOfLongestSubstring("abababab"));
  }

  @Test
  void testGapBetweenDuplicates() {
    assertEquals(4, solution.lengthOfLongestSubstring("abcabcabcd"));
  }

  @Test
  void testSingleRepeatFarApart() {
    assertEquals(5, solution.lengthOfLongestSubstring("abcdae"));
  }

  @Test
  void testAllSameLong() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 1000; i++) sb.append('x');
    assertEquals(1, solution.lengthOfLongestSubstring(sb.toString()));
  }

  @Test
  void testAlphabetTwice() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 3; i++) sb.append("abcdefghijklmnopqrstuvwxyz");
    assertEquals(26, solution.lengthOfLongestSubstring(sb.toString()));
  }

  @Test
  void testOverlappingWindow() {
    assertEquals(4, solution.lengthOfLongestSubstring("abcad"));
  }

  @Test
  void testRepeatedPair() {
    assertEquals(2, solution.lengthOfLongestSubstring("abba"));
  }
}
