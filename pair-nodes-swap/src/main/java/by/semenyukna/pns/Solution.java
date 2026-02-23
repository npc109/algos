package by.semenyukna.pns;

class Solution {
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    var c = head;
    head = head.next;
    var n = head.next;
    head.next = c;
    c.next = swapPairs(n);
    return head;
  }
}