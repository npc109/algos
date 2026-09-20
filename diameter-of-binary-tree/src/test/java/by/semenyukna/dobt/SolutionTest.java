package by.semenyukna.dobt;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1() {
    assertEquals(3, solution.diameterOfBinaryTree(tree(1, 2, 3, 4, 5)));
  }

  @Test
  void testExample2() {
    assertEquals(1, solution.diameterOfBinaryTree(tree(1, 2)));
  }

  @Test
  void testSingleNode() {
    assertEquals(0, solution.diameterOfBinaryTree(tree(1)));
  }

  @Test
  void testNullRoot() {
    assertEquals(0, solution.diameterOfBinaryTree(null));
  }

  @Test
  void testThreeNodesBalanced() {
    assertEquals(2, solution.diameterOfBinaryTree(tree(1, 2, 3)));
  }

  @Test
  void testRightChain() {
    // Бамбук вправо: диаметр = число рёбер = n - 1.
    assertEquals(4, solution.diameterOfBinaryTree(
        tree(1, null, 2, null, 3, null, 4, null, 5)));
  }

  @Test
  void testLeftChain() {
    assertEquals(3, solution.diameterOfBinaryTree(
        tree(1, 2, null, 3, null, 4)));
  }

  @Test
  void testDiameterDoesNotPassThroughRoot() {
    // Путь 6 -> 4 -> 2 -> 5 даёт 4, корень 1 в диаметр не входит.
    assertEquals(4, solution.diameterOfBinaryTree(
        tree(1, 2, 3, 4, 5, null, null, 6)));
  }

  @Test
  void testDeepLeftBranchBeatsRootPath() {
    assertEquals(4, solution.diameterOfBinaryTree(
        tree(1, 2, null, 3, null, 4, null, 5)));
  }

  @Test
  void testBothSidesEqualDepth() {
    assertEquals(4, solution.diameterOfBinaryTree(
        tree(1, 2, 3, 4, 5, 6, 7)));
  }

  @Test
  void testRootWithOnlyLeftChild() {
    assertEquals(1, solution.diameterOfBinaryTree(tree(1, 2, null)));
  }

  @Test
  void testRootWithOnlyRightChild() {
    assertEquals(1, solution.diameterOfBinaryTree(tree(1, null, 2)));
  }

  @Test
  void testZigZag() {
    // 1 -> 3 -> 5 и 1 -> 2 -> 4 дают по 2 ребра, но 5 и 4 — соседи через 3,
    // поэтому настоящий максимум — 3.
    assertEquals(3, solution.diameterOfBinaryTree(
        tree(1, 3, 2, 5, 4)));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    // Поле g должно сбрасываться при каждом вызове.
    assertEquals(3, solution.diameterOfBinaryTree(tree(1, 2, 3, 4, 5)));
    assertEquals(0, solution.diameterOfBinaryTree(tree(1)));
    assertEquals(1, solution.diameterOfBinaryTree(tree(1, 2)));
    assertEquals(2, solution.diameterOfBinaryTree(tree(1, 2, 3)));
  }

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
