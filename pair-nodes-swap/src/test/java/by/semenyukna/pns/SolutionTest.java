package by.semenyukna.pns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  @Test
  public void testSolution() {
    var head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
    var need = new ListNode(2, new ListNode(1, new ListNode(4, new ListNode(3))));
    var s = new Solution().swapPairs(head);
    assertEquals(need, s);
  }
  @Test
  public void testSolution2() {
    var head = new ListNode(1);
    var need = new ListNode(1);
    var s = new Solution().swapPairs(head);
    assertEquals(need, s);
  }

}