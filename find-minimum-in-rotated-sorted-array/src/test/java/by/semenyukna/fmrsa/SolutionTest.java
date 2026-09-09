package by.semenyukna.fmrsa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {
  @Test
  public void test1() {
    int[] l = {4,5,6,7,0,1,2};
    var s = new Solution();
    var res = s.find(l);
    assertEquals(0, res);
  }

  @Test
  public void test2() {
    int[] l = {0,1,2,3,4};
    var s = new Solution();
    var res = s.find(l);
    assertEquals(0, res);
  }

  @Test
  public void test3() {
    int[] l = {3,1,2};
    var s = new Solution();
    var res = s.find(l);
    assertEquals(1, res);
  }
  @Test
  public void test4() {
    int[] l = {10,11,12,13,14,15,16,17,1,2,3};
    var s = new Solution();
    var res = s.find(l);
    assertEquals(1, res);
  }
  @Test
  public void test5() {
    int[] l = {Integer.MAX_VALUE, Integer.MIN_VALUE};
    var s = new Solution();
    var res = s.find(l);
    assertEquals(Integer.MIN_VALUE, res);
  }
  @Test
  public void test6() {
    int[] l = {3,4,5,1,2};
    var s = new Solution();
    var res = s.find(l);
    assertEquals(1, res);
  }
}