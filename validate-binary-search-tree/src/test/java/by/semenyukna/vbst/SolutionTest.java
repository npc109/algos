package by.semenyukna.vbst;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExample1IsValid() {
    assertEquals(true, solution.isValidBST(tree(2, 1, 3)));
  }

  @Test
  void testExample2IsInvalid() {
    // Локальные проверки проходят: 1<5, 4>5, 3<4, 6>4.
    // Но 3 лежит в правом поддереве 5, а 3 < 5.
    assertEquals(false, solution.isValidBST(tree(5, 1, 4, null, null, 3, 6)));
  }

  @Test
  void testDeepViolationThroughAncestor() {
    // 0 в правом поддереве 2, но 0 < 2. Нарушение через дальнего предка.
    assertEquals(false, solution.isValidBST(tree(2, 1, 3, null, null, 0, 4)));
  }

  @Test
  void testNullRoot() {
    // Пустое дерево по определению валидный BST.
    assertEquals(true, solution.isValidBST(null));
  }

  @Test
  void testSingleNode() {
    assertEquals(true, solution.isValidBST(tree(1)));
  }

  @Test
  void testIntegerMinValueSingleNode() {
    // Наивная реализация с границами Integer.MIN_VALUE/MAX_VALUE вернёт false.
    assertEquals(true, solution.isValidBST(tree(Integer.MIN_VALUE)));
  }

  @Test
  void testIntegerMaxValueSingleNode() {
    assertEquals(true, solution.isValidBST(tree(Integer.MAX_VALUE)));
  }

  @Test
  void testIntegerMinValueAsRootWithRightChild() {
    assertEquals(true, solution.isValidBST(tree(Integer.MIN_VALUE, null, 0)));
  }

  @Test
  void testIntegerMaxValueAsRootWithLeftChild() {
    assertEquals(true, solution.isValidBST(tree(Integer.MAX_VALUE, 0, null)));
  }

  @Test
  void testGoodTreeWithIntegerMinValueInside() {
    // MIN_VALUE слева от -1 — корректно.
    assertEquals(true, solution.isValidBST(tree(-1, Integer.MIN_VALUE, 0)));
  }

  @Test
  void testBadTreeWithIntegerMinValueOnTheRight() {
    // MIN_VALUE справа от -1 — нарушение: он меньше -1.
    assertEquals(false, solution.isValidBST(tree(-1, null, Integer.MIN_VALUE)));
  }

  @Test
  void testEqualValuesAreInvalid() {
    // BST требует СТРОГОГО неравенства: дубликаты запрещены.
    assertEquals(false, solution.isValidBST(tree(2, 2, 2)));
  }

  @Test
  void testEqualValueOnTheLeft() {
    assertEquals(false, solution.isValidBST(tree(2, 2, null)));
  }

  @Test
  void testEqualValueOnTheRight() {
    assertEquals(false, solution.isValidBST(tree(2, null, 2)));
  }

  @Test
  void testLeftChainValid() {
    assertEquals(true, solution.isValidBST(tree(5, 4, null, 3, null, 2, null, 1)));
  }

  @Test
  void testRightChainValid() {
    assertEquals(true, solution.isValidBST(tree(1, null, 2, null, 3, null, 4, null, 5)));
  }

  @Test
  void testLeftChainInvalidAtTheEnd() {
    // 100 в самом низу левой цепочки: он больше 5, а лежит слева.
    assertEquals(false, solution.isValidBST(tree(5, 4, null, 3, null, 2, null, 100)));
  }

  @Test
  void testRightChainInvalidAtTheEnd() {
    assertEquals(false, solution.isValidBST(tree(1, null, 2, null, 3, null, 4, null, 0)));
  }

  @Test
  void testViolationOnlyAtTheDeepestLevel() {
    // Все локальные проверки проходят: 11 < 20 (родитель).
    // Но 11 лежит в правом поддереве 15, а 11 < 15 — нарушение через предка.
    assertEquals(false, solution.isValidBST(tree(10, 5, 15, 3, 7, 12, 20, null, null, null, null, null, null, null, 11)));
  }

  @Test
  void testDeepestLevelValidWhenGreaterThanAllAncestors() {
    // Та же форма, но 25 > 20, 25 > 15, 25 > 10 — валидный BST.
    assertEquals(true, solution.isValidBST(tree(10, 5, 15, 3, 7, 12, 20, null, null, null, null, null, null, null, 25)));
  }

  @Test
  void testDeepTreeIsValid() {
    assertEquals(true, solution.isValidBST(tree(10, 5, 15, 3, 7, 12, 20)));
  }

  @Test
  void testLeftSubtreeTooBig() {
    // 6 лежит в левом поддереве 5, но 6 > 5.
    assertEquals(false, solution.isValidBST(tree(5, 6, 7)));
  }

  @Test
  void testRightSubtreeTooSmall() {
    assertEquals(false, solution.isValidBST(tree(5, 3, 4)));
  }

  @Test
  void testOnlyRightChild() {
    assertEquals(true, solution.isValidBST(tree(1, null, 2)));
  }

  @Test
  void testOnlyLeftChild() {
    assertEquals(true, solution.isValidBST(tree(2, 1, null)));
  }

  @Test
  void testDegenerateLeftChainOfManyNodes() {
    // Вырожденное дерево: проверка на переполнение стека и на O(n) памяти.
    int n = 500;
    TreeNode root = new TreeNode(n);
    TreeNode cur = root;
    for (int v = n - 1; v >= 0; v--) {
      cur.left = new TreeNode(v);
      cur = cur.left;
    }
    assertEquals(true, solution.isValidBST(root));
  }

  @Test
  void testDegenerateRightChainOfManyNodes() {
    int n = 500;
    TreeNode root = new TreeNode(0);
    TreeNode cur = root;
    for (int v = 1; v <= n; v++) {
      cur.right = new TreeNode(v);
      cur = cur.right;
    }
    assertEquals(true, solution.isValidBST(root));
  }

  @Test
  void testDegenerateChainInvalidDeep() {
    int n = 500;
    TreeNode root = new TreeNode(n);
    TreeNode cur = root;
    for (int v = n - 1; v >= 1; v--) {
      cur.left = new TreeNode(v);
      cur = cur.left;
    }
    cur.left = new TreeNode(10000);   // нарушение в самом низу
    assertEquals(false, solution.isValidBST(root));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(true, solution.isValidBST(tree(2, 1, 3)));
    assertEquals(false, solution.isValidBST(tree(5, 1, 4, null, null, 3, 6)));
    assertEquals(true, solution.isValidBST(null));
    assertEquals(true, solution.isValidBST(tree(Integer.MIN_VALUE)));
    assertEquals(true, solution.isValidBST(tree(2, 1, 3)));
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
