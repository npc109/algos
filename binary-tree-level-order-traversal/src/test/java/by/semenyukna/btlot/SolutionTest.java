package by.semenyukna.btlot;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    //      3
    //     / \
    //    9   20
    //       /  \
    //      15   7
    assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)),
        solution.levelOrder(tree(3, 9, 20, null, null, 15, 7)));
  }

  @Test
  void testExample2() {
    assertEquals(List.of(List.of(1)), solution.levelOrder(tree(1)));
  }

  @Test
  void testExample3Empty() {
    assertEquals(List.of(), solution.levelOrder(tree()));
  }

  @Test
  void testNullRoot() {
    assertEquals(List.of(), solution.levelOrder(null));
  }

  @Test
  void testTwoLevels() {
    assertEquals(List.of(List.of(1), List.of(2, 3)),
        solution.levelOrder(tree(1, 2, 3)));
  }

  @Test
  void testLeftSkewed() {
    // 1 -> 2 -> 3 влево: по одному узлу на уровень
    assertEquals(List.of(List.of(1), List.of(2), List.of(3)),
        solution.levelOrder(tree(1, 2, null, 3)));
  }

  @Test
  void testRightSkewed() {
    assertEquals(List.of(List.of(1), List.of(2), List.of(3)),
        solution.levelOrder(tree(1, null, 2, null, 3)));
  }

  @Test
  void testCompleteTree() {
    //      1
    //    /   \
    //   2     3
    //  / \   / \
    // 4   5 6   7
    assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7)),
        solution.levelOrder(tree(1, 2, 3, 4, 5, 6, 7)));
  }

  @Test
  void testNegativeValues() {
    assertEquals(List.of(List.of(-1), List.of(-2, -3)),
        solution.levelOrder(tree(-1, -2, -3)));
  }

  @Test
  void testGapsInLevel() {
    //      1
    //     / \
    //    2   3
    //   /     \
    //  4       5
    assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5)),
        solution.levelOrder(tree(1, 2, 3, 4, null, null, 5)));
  }

  @Test
  void testOnlyLeftChildren() {
    //      1
    //     /
    //    2
    //   /
    //  3
    assertEquals(List.of(List.of(1), List.of(2), List.of(3)),
        solution.levelOrder(tree(1, 2, null, 3)));
  }

  @Test
  void testWideTree() {
    // tree(1,2,3,4,5,6) даёт ТРИ уровня:
    //       1
    //      / \
    //     2   3
    //    / \   \
    //   4   5   6
    assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6)),
        solution.levelOrder(tree(1, 2, 3, 4, 5, 6)));
  }

  @Test
  void testStarTree() {
    // Звезда: корень + дети на втором уровне.
    TreeNode root = new TreeNode(0);
    root.left = new TreeNode(1);
    root.right = new TreeNode(2);
    assertEquals(List.of(List.of(0), List.of(1, 2)), solution.levelOrder(root));
  }

  @Test
  void testLargeBalanced() {
    // 63 узла, 6 уровней полного дерева
    TreeNode root = buildComplete(6);
    List<List<Integer>> got = solution.levelOrder(root);
    assertEquals(6, got.size());
    for (int lvl = 0; lvl < 6; lvl++) {
      assertEquals(1 << lvl, got.get(lvl).size(), "уровень " + lvl);
    }
  }

  @Test
  void testDeepTree() {
    // 100 уровней влево
    TreeNode root = new TreeNode(0);
    TreeNode cur = root;
    for (int i = 1; i < 100; i++) {
      cur.left = new TreeNode(i);
      cur = cur.left;
    }
    List<List<Integer>> got = solution.levelOrder(root);
    assertEquals(100, got.size());
    for (int i = 0; i < 100; i++) {
      assertEquals(List.of(i), got.get(i));
    }
  }

  @Test
  void testResultIsIndependentBetweenCalls() {
    // Состояние не должно течь между вызовами.
    var first = solution.levelOrder(tree(1, 2, 3));
    var second = solution.levelOrder(tree(4, 5, 6));
    assertEquals(List.of(List.of(1), List.of(2, 3)), first);
    assertEquals(List.of(List.of(4), List.of(5, 6)), second);
  }

  @Test
  void testSparseRightBranch() {
    assertEquals(List.of(List.of(1), List.of(2), List.of(3), List.of(4)),
        solution.levelOrder(tree(1, null, 2, null, 3, null, 4)));
  }

  // --- помощники -----------------------------------------------------------

  /** Построение по level-order массиву (null = отсутствие узла). */
  private static TreeNode tree(Integer... levelOrder) {
    if (levelOrder.length == 0 || levelOrder[0] == null) {
      return null;
    }
    TreeNode root = new TreeNode(levelOrder[0]);
    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.add(root);
    int i = 1;
    while (!queue.isEmpty() && i < levelOrder.length) {
      TreeNode current = queue.poll();
      if (i < levelOrder.length && levelOrder[i] != null) {
        current.left = new TreeNode(levelOrder[i]);
        queue.add(current.left);
      }
      i++;
      if (i < levelOrder.length && levelOrder[i] != null) {
        current.right = new TreeNode(levelOrder[i]);
        queue.add(current.right);
      }
      i++;
    }
    return root;
  }

  /** Полное дерево высоты h (h=1 -> 1 узел, h=6 -> 63 узла). */
  private static TreeNode buildComplete(int h) {
    if (h == 0) {
      return null;
    }
    return new TreeNode(0, buildComplete(h - 1), buildComplete(h - 1));
  }
}
