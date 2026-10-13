package by.semenyukna.ksbst;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    //      3
    //     / \
    //    1   4
    //     \
    //      2
    assertEquals(1, solution.kthSmallest(tree(3, 1, 4, null, 2), 1));
  }

  @Test
  void testExample2() {
    //          5
    //        /   \
    //       3     6
    //      / \
    //     2   4
    //    /
    //   1
    assertEquals(3, solution.kthSmallest(tree(5, 3, 6, 2, 4, null, null, 1), 3));
  }

  @Test
  void testSingleNodeK1() {
    assertEquals(7, solution.kthSmallest(tree(7), 1));
  }

  @Test
  void testKEqualsNIsMaximum() {
    assertEquals(4, solution.kthSmallest(tree(3, 1, 4, null, 2), 4));
  }

  @Test
  void testSmallestIsDeepestLeft() {
    // Минимум — самый левый узел, а не корень.
    assertEquals(1, solution.kthSmallest(tree(10, 5, 15, 3, 7, 12, 20, 1), 1));
  }

  @Test
  void testLargestIsDeepestRight() {
    assertEquals(20, solution.kthSmallest(tree(10, 5, 15, 3, 7, 12, 20), 7));
  }

  @Test
  void testLeftChain() {
    // Вырожденное влево: 1-2-3-4-5, k-й наименьший равен k.
    assertEquals(1, solution.kthSmallest(tree(5, 4, null, 3, null, 2, null, 1), 1));
    assertEquals(2, solution.kthSmallest(tree(5, 4, null, 3, null, 2, null, 1), 2));
    assertEquals(3, solution.kthSmallest(tree(5, 4, null, 3, null, 2, null, 1), 3));
    assertEquals(5, solution.kthSmallest(tree(5, 4, null, 3, null, 2, null, 1), 5));
  }

  @Test
  void testRightChain() {
    // 1-2-3-4-5 вправо.
    assertEquals(1, solution.kthSmallest(tree(1, null, 2, null, 3, null, 4, null, 5), 1));
    assertEquals(5, solution.kthSmallest(tree(1, null, 2, null, 3, null, 4, null, 5), 5));
  }

  @Test
  void testZeroIsSmallest() {
    // 0 — минимальное допустимое значение по ограничению 0 <= Node.val <= 10^4.
    // Проверяет, что sentinel «не найдено» не путается с настоящим ответом 0.
    assertEquals(0, solution.kthSmallest(tree(0, null, 5), 1));
    assertEquals(5, solution.kthSmallest(tree(0, null, 5), 2));
  }

  @Test
  void testZeroDeepInsideTree() {
    assertEquals(0, solution.kthSmallest(tree(10, 5, 15, 0, 7, 12, 20), 1));
  }

  @Test
  void testMaxAllowedValue() {
    assertEquals(10000, solution.kthSmallest(tree(10000, 0, null), 2));
  }

  @Test
  void testAllKValuesOnBalancedTree() {
    //      4
    //    /   \
    //   2     6
    //  / \   / \
    // 1   3 5   7
    TreeNode root = tree(4, 2, 6, 1, 3, 5, 7);
    for (int k = 1; k <= 7; k++) {
      assertEquals(k, solution.kthSmallest(root, k), "k=" + k);
    }
  }

  @Test
  void testRepeatedCallsDoNotLeakState() {
    TreeNode root = tree(4, 2, 6, 1, 3, 5, 7);
    for (int k = 1; k <= 7; k++) {
      assertEquals(k, solution.kthSmallest(root, k), "k=" + k);
    }
    // и в обратном порядке
    for (int k = 7; k >= 1; k--) {
      assertEquals(k, solution.kthSmallest(root, k), "k=" + k);
    }
  }

  @Test
  void testDeepLeftChainLargeK() {
    // 2000 узлов влево: проверка отсутствия переполнения стека и раннего выхода.
    int n = 2000;
    TreeNode root = new TreeNode(n);
    TreeNode cur = root;
    for (int v = n - 1; v >= 1; v--) {
      cur.left = new TreeNode(v);
      cur = cur.left;
    }
    assertEquals(1, solution.kthSmallest(root, 1));
    assertEquals(1000, solution.kthSmallest(root, 1000));
    assertEquals(2000, solution.kthSmallest(root, 2000));
  }

  @Test
  void testRightSkewedLargeK() {
    int n = 2000;
    TreeNode root = new TreeNode(1);
    TreeNode cur = root;
    for (int v = 2; v <= n; v++) {
      cur.right = new TreeNode(v);
      cur = cur.right;
    }
    assertEquals(1, solution.kthSmallest(root, 1));
    assertEquals(2, solution.kthSmallest(root, 2));
    assertEquals(2000, solution.kthSmallest(root, 2000));
  }

  @Test
  void testSentinelDoesNotLeakWhenAnswerIsZero() {
    // Ответ 0 — минимальное допустимое значение. Решение не должно
    // спутать его с sentinel «не найдено».
    assertEquals(0, solution.kthSmallest(tree(1, 0, 2), 1));
    assertEquals(1, solution.kthSmallest(tree(1, 0, 2), 2));
    assertEquals(2, solution.kthSmallest(tree(1, 0, 2), 3));
  }

  @Test
  void testTwoNodes() {
    assertEquals(1, solution.kthSmallest(tree(2, 1, null), 1));
    assertEquals(2, solution.kthSmallest(tree(2, 1, null), 2));
  }

  // --- построение дерева по level-order массиву (null = отсутствие узла) --------

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
}
