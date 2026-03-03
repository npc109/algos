package by.semenyukna.rll;

class Solution {

  public ListNode reverseList(ListNode head) {
    ListNode node = null;
    while (head != null) {
      var tmp = head.next;
      head.next = node;
      node = head;
      head = tmp;

    }
    return node;
  }
}