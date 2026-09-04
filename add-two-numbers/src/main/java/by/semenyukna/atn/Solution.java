package by.semenyukna.atn;


public class Solution {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null;
    ListNode cur = null;
    var overflow = false;

    while (l1 != null || l2 != null) {
      var s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
      l1 = l1 == null ? null : l1.next;
      l2 = l2 == null ? null : l2.next;
      s = overflow ? s + 1 : s;
      overflow = s > 9;
      s = overflow ? s % 10 : s;

      var newNode = new ListNode(s);

      if (head == null) {
        head = newNode;
        cur = newNode;
      } else {
        cur.next = newNode;
        cur = cur.next;
      }
    }

    if (overflow) cur.next = new ListNode(1);
    return head;
  }
}