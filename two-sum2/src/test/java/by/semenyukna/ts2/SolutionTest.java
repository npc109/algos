package by.semenyukna.ts2;

import by.semenyukna.ts2.Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

  @Test
  public void test1() {
    int[] val = {2, 7, 11, 15};
    int[] need = {1, 2};
    var s = new Solution();
    var cur = s.twoSum(val, 9);
    assertArrayEquals(need, cur);
  }

  @Test
  public void test2() {
    int[] val = {2, 3, 4};
    int[] need = {1, 3};
    var s = new Solution();
    var cur = s.twoSum(val, 6);
    assertArrayEquals(need, cur);
  }

  @Test
  public void test3() {
    int[] val = {-1, 0};
    int[] need = {1, 2};
    var s = new Solution();
    var cur = s.twoSum(val, -1);
    assertArrayEquals(need, cur);
  }

}