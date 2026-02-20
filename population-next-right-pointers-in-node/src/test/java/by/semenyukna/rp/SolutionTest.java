/**
 * <p>Этот класс служит для...</p>
 *
 * @author semenyukna
 * @since 20.02.2026
 */

package by.semenyukna.rp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  @Test
  public void connectTest() {
    var root = new Node(1, new Node(2, new Node(4), new Node(5), null), new Node(3, new Node(6), new Node(7), null), null);
    var s = new Solution();
    s.connect(root);
    assertEquals(root.left.next, root.right);
    assertEquals(root.left.right.next, root.right.left);
  }

}
