package by.semenyukna.atn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {

  private ListNode listNode(int... vals) {
    ListNode head = null;
    ListNode cur = null;
    for (int val : vals) {
      var node = new ListNode(val);
      if (head == null) {
        head = node;
        cur = node;
      } else {
        cur.next = node;
        cur = cur.next;
      }
    }
    return head;
  }

  @Test
  public void test342plus465() {
    var l1 = listNode(2, 4, 3);
    var l2 = listNode(5, 6, 4);
    var expected = listNode(7, 0, 8);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

  @Test
  public void testZeroPlusZero() {
    var l1 = listNode(0);
    var l2 = listNode(0);
    var expected = listNode(0);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

  @Test
  public void testLongNumbers() {
    var l1 = listNode(9, 9, 9, 9, 9, 9, 9);
    var l2 = listNode(9, 9, 9, 9);
    var expected = listNode(8, 9, 9, 9, 0, 0, 0, 1);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

  @Test
  public void testDifferentLengths() {
    var l1 = listNode(1, 8, 3);
    var l2 = listNode(7, 1);
    var expected = listNode(8, 9, 3);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

  @Test
  public void testSingleDigitWithOverflow() {
    var l1 = listNode(5);
    var l2 = listNode(5);
    var expected = listNode(0, 1);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

  @Test
  public void testOneNumberIsEmpty() {
    var l1 = listNode(9, 9);
    var l2 = listNode(1);
    var expected = listNode(0, 0, 1);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

  @Test
  public void testNoOverflow() {
    var l1 = listNode(1, 2, 3);
    var l2 = listNode(4, 5, 6);
    var expected = listNode(5, 7, 9);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

  @Test
  public void testAllNines() {
    var l1 = listNode(9);
    var l2 = listNode(9);
    var expected = listNode(8, 1);
    assertEquals(expected, new Solution().addTwoNumbers(l1, l2));
  }

}
