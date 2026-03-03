package by.semenyukna.rl;

class Solution {


  private ListNode reverse(ListNode head) {
    ListNode node = null;
    while (head != null) {
      var tmp = head.next;
      head.next = node;
      node = head;
      head = tmp;
    }
    return node;
  }


  public ListNode reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    var fast = head.next;
    var slow = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    fast = reverse(slow.next);
    slow.next = null;
    var h = head;
    while (h != null) {
      var tmp = h.next;
      h.next = fast;
      fast = fast.next;
      h.next.next = tmp != null ? tmp : fast;
      h = tmp;

    }
    return head;
  }
}