package by.semenyukna.np;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class SolutionTest {

  @Test
  public void testSolution() {
    int[] cur = {1, 2, 3};
    int[] need = {1, 3, 2};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution1() {
    int[] cur = {3, 2, 1};
    int[] need = {1, 2, 3};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution2() {
    int[] cur = {1};
    int[] need = {1};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution3() {
    int[] cur = {1, 2};
    int[] need = {2, 1};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution4() {
    int[] cur = {1, 3, 2};
    int[] need = {2, 1, 3};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution5() {
    int[] cur = {1, 2, 3};
    int[] need = {1, 3, 2};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }
  @Test
  public void testSolution6() {
    int[] cur = {2, 3, 1};
    int[] need = {3, 1, 2};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution7() {
    int[] cur = {4,2,0,2,3,2,0};
    // {4,2,0,3,2,2,0}
    int[] need = {4,2,0,3,0,2,2};
    var s = new Solution();
    s.nextPermutation(cur);
    assertArrayEquals(cur, need);
  }
}