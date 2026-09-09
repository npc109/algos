package by.semenyukna.combinations;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {
  @Test
  void test1() {
    var n = 4;
    var k = 3;
    var needed = new ArrayList<ArrayList<Integer>>();
    needed.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
    needed.add(new ArrayList<>(Arrays.asList(1, 2, 4)));
    needed.add(new ArrayList<>(Arrays.asList(1, 3, 4)));
    needed.add(new ArrayList<>(Arrays.asList(2, 3, 4)));
    var s = new Solution();
    var res = s.combine(4, 3);
    assertEquals(needed, res);
  }
}