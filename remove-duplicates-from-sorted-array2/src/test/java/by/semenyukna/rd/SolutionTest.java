package by.semenyukna.rd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SolutionTest {

  @Test
  public void test1() {
    int[] cur = {1, 1, 1, 2, 2, 3};
    int[] exp = {1, 1, 2, 2, 3};
    var s = new Solution();
    var res = s.removeDuplicates(cur);
    assertArrayEquals(exp, Arrays.copyOfRange(cur, 0, res));
  }

  @Test
  public void test2() {
    int[] cur = {-1, 0, 0, 0, 0, 3, 3};
    int[] exp = {-1, 0, 0, 3, 3};
    var s = new Solution();
    var res = s.removeDuplicates(cur);
    assertArrayEquals(exp, Arrays.copyOfRange(cur, 0, res));
  }
}