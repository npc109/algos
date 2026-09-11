package by.semenyukna.lls;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

  @Test
  void test1() {
    int[] n = {10, 9, 2, 5, 3, 7, 101, 18};
    var need = 4;
    var s = new Solution();
    var res = s.lengthOfLIS(n);
    assertEquals(need, res);
  }

  @Test
  void test2() {
    int[] n = {0, 1, 0, 3, 2, 3};
    var need = 4;
    var s = new Solution();
    var res = s.lengthOfLIS(n);
    assertEquals(need, res);
  }

  @Test
  void test3() {
    int[] n = {10, 9, 2, 5, 3, 7, 101, 18};
    var need = 4;
    var s = new Solution();
    var res = s.lengthOfLIS(n);
    assertEquals(need, res);
  }
}