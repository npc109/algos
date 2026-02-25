package by.semenyukna.lcs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

  @Test
  public void testSolution() {
    int[] cur = {1, 2, 3};
    var s = new Solution();
    assertEquals(3, s.longestConsecutive(cur));
  }

  @Test
  public void testSolution2() {
    int[] cur = {100, 4, 200, 1, 3, 2};
    var s = new Solution();
    assertEquals(4, s.longestConsecutive(cur));
  }

  @Test
  public void testSolution3() {
    int[] cur = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
    var s = new Solution();
    assertEquals(9, s.longestConsecutive(cur));
  }
}