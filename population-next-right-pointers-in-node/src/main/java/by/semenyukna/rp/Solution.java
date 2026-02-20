/**
 * <p>Этот класс служит для...</p>
 *
 * @author semenyukna
 * @since 20.02.2026
 */

package by.semenyukna.rp;

public class Solution {

  public Node connect(Node root) {
    if (root == null) return null;
    Node mostLeft = root;
    while (mostLeft.left != null) {
      var current = mostLeft;
      while (current != null) {
        current.left.next = current.right;
        if (current.next != null) {
          current.right.next = current.next.left;
        }

        current = current.next;
      }
      mostLeft = mostLeft.left;
    }
    return root;
  }
}
