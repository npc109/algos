package by.semenyukna.pp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SolutionTest{

  @Test
  public void testSolution(){
    int[] cur = {3, 2, 1};
    int[] need = {3, 1, 2};
    var s = new Solution();
    s.prevPermOpt1(cur);
    System.out.println(Arrays.toString(cur));
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution2(){
    int[] cur = {3,1,1,3};
    int[] need = {1,3,1,3};
    var s = new Solution();
    s.prevPermOpt1(cur);
    System.out.println(Arrays.toString(cur));
    assertArrayEquals(cur, need);
  }
  @Test
  public void testSolution3(){
    int[] cur = {1,9,4,6,7};
    int[] need = {1,7,4,6,9};
    var s = new Solution();
    s.prevPermOpt1(cur);
    System.out.println(Arrays.toString(cur));
    assertArrayEquals(cur, need);
  }

  @Test
  public void testSolution4(){
    int[] cur = {1,9,6,7,9,6,4,4,2,2,7,7,7,6,3,5,7,7,3,8,8,4,4,1,5,4,7,4,7,3,7,5,4,1,7,4,9,6,5,9,8,9,9,4,6,6,5,5,7,7,8,1,4,6,4,5,4,4,8,9,5,7,2,4};
    int[] need = {1,9,6,7,9,6,4,4,2,2,7,7,7,6,3,5,7,7,3,8,8,4,4,1,5,4,7,4,7,3,7,5,4,1,7,4,9,6,5,9,8,9,9,4,6,6,5,5,7,7,8,1,4,6,4,5,4,4,8,9,5,4,2,7};
    var s = new Solution();
    s.prevPermOpt1(cur);
    System.out.println(Arrays.toString(cur));
    assertArrayEquals(cur, need);

  }
}