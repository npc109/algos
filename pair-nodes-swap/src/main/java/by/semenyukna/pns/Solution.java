package by.semenyukna.pns;

class Solution {
  public ListNode swapPairs(ListNode head) {
    if (head != null && head.next != null) {
      var c = head;
      head = head.next; // 2
      var n = head.next; // 3
      head.next = c;
      if (c != null) {
        c.next = swapPairs(n);
      }
    }
    return head;
  }
}