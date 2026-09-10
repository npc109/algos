package by.semenyukna.wd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

  @Test
  void test1() {
    int[] v = {73, 74, 75, 71, 69, 72, 76, 73};
    int[] need = {1, 1, 4, 2, 1, 1, 0, 0};
    var s = new Solution();
    var res = s.solution(v);
    assertArrayEquals(need, res);
  }

  @Test
  void testStrictlyIncreasing() {
    int[] v = {30, 40, 50, 60};
    int[] need = {1, 1, 1, 0};
    var s = new Solution();
    assertArrayEquals(need, s.solution(v));
  }

  @Test
  void testStrictlyDecreasing() {
    int[] v = {70, 60, 50, 40};
    int[] need = {0, 0, 0, 0};
    var s = new Solution();
    assertArrayEquals(need, s.solution(v));
  }

  @Test
  void testAllEqual() {
    int[] v = {50, 50, 50};
    int[] need = {0, 0, 0};
    var s = new Solution();
    assertArrayEquals(need, s.solution(v));
  }

  @Test
  void testSingleDay() {
    int[] v = {50};
    int[] need = {0};
    var s = new Solution();
    assertArrayEquals(need, s.solution(v));
  }

  @Test
  void testEmpty() {
    int[] v = {};
    int[] need = {};
    var s = new Solution();
    assertArrayEquals(need, s.solution(v));
  }

  @Test
  void testEqualThenWarmer() {
    int[] v = {55, 50, 50, 60};
    int[] need = {3, 2, 1, 0};
    var s = new Solution();
    assertArrayEquals(need, s.solution(v));
  }
}