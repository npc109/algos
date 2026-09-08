package by.semenyukna.ts2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {
  @Test
  public void test1() {
    int[] l = {2, 5, 5, 1, 2, 3, 4};
    var s = new Solution();
    var res = s.getLongest(l);
    assertEquals(5, res);
  }

  @Test
  public void test2() {
    int[] l = {1, 2, 3, 1, 4, 5};
    var s = new Solution();
    var res = s.getLongest(l);
    assertEquals(5, res);
  }

  @Test
  public void test3() {
    int[] l = {7, 7, 7};
    var s = new Solution();
    var res = s.getLongest(l);
    assertEquals(1, res);
  }
  @Test
  public void test4() {
    int[] l = {1, 2, 3, 4, 1, 2, 5, 6};
    var s = new Solution();
    var res = s.getLongest(l);
    assertEquals(6, res);
  }
  @Test
  public void test5() {
    int[] l = {1, 2, 3, 4, 2, 5, 6, 7, 8, 3, 9};
    var s = new Solution();
    var res = s.getLongest(l);
    assertEquals(8, res);
  }
  @Test
  public void test6() {
    int[] l = {2, 5, 5, 1, 2, 3, 4};
    var s = new Solution();
    var res = s.getLongest(l);
    assertEquals(5, res);
  }
}