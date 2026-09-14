package by.semenyukna.btmps;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Кейсы подобраны так, чтобы ловить именно те ошибки, которые всплыли в диагностике:
 * <ul>
 *   <li>отрицательная ветка, прибавленная к узлу (пример {@code [2,-1]});</li>
 *   <li>все узлы отрицательные (ответ — один узел, а не 0);</li>
 *   <li>ответ не проходит через корень;</li>
 *   <li>утечка состояния между вызовами (см. {@link #testNoStateLeakBetweenCalls()}).</li>
 * </ul>
 *
 * <p>Один экземпляр {@code Solution} переиспользуется во всех тестах намеренно: если решение
 * хранит ответ в поле и не сбрасывает его, тесты это поймают.
 */
public class SolutionTest {

  private final Solution solution = new Solution();

  @Test
  void testExampleDiamond() {
    // 2 -> 1 -> 3
    assertEquals(6, solution.maxPathSum(tree(1, 2, 3)));
  }

  @Test
  void testExampleTwoLeaves() {
    // 15 -> 20 -> 7
    assertEquals(42, solution.maxPathSum(tree(-10, 9, 20, null, null, 15, 7)));
  }

  @Test
  void testNegativeChildIsNotAttached() {
    // Путь 2 -> -1 даёт 1, но лучший путь — сам узел 2.
    assertEquals(2, solution.maxPathSum(tree(2, -1)));
  }

  @Test
  void testNegativeChildOnTheRight() {
    assertEquals(2, solution.maxPathSum(tree(2, -1, -2)));
  }

  @Test
  void testSingleNegativeNode() {
    assertEquals(-3, solution.maxPathSum(tree(-3)));
  }

  @Test
  void testAllNegative() {
    // Любой путь из нескольких узлов хуже самого большого узла.
    assertEquals(-1, solution.maxPathSum(tree(-2, -1, -3)));
    assertEquals(-1, solution.maxPathSum(tree(-1, -2, -3)));
  }

  @Test
  void testAllNegativeButRootIsLargest() {
    // Ровно тот случай, который формулировался как «первое значение самое большое»:
    // -1 > -2 и -2, лишний минус прибавлять нельзя.
    assertEquals(-1, solution.maxPathSum(tree(-1, -2, -2)));
  }

  @Test
  void testRootCanBeTheAnswer() {
    // Оба ребёнка отрицательные: ответ - сам корень, а не корень минус дети.
    assertEquals(5, solution.maxPathSum(tree(5, -2, -2)));
  }

  @Test
  void testLongPathAcrossBothBranches() {
    // Путь 20 -> 2 -> 1 -> 10 -> 4 = 37: он ветвится в КОРНЕ и забирает оба рукава.
    // Ожидание подтверждено перебором всех путей (brute force), не на глаз.
    assertEquals(37, solution.maxPathSum(tree(1, 2, 10, 20, 1, 3, 4)));
  }

  @Test
  void testAnswerComesFromDeepSubtree() {
    // Путь через корень даёт -100 + 22 + 10 = -68, поэтому корень отбрасывается.
    // Лучший путь целиком внутри левого поддерева: 20 -> 2 = 22 (ветку -5 не берём,
    // она бы дала 20 + 2 - 5 = 17).
    // Значение 22 подтверждено перебором всех путей (brute force).
    assertEquals(22, solution.maxPathSum(tree(-100, 2, -50, 20, -5, 10)));
  }

  @Test
  void testNegativeBranchDoesNotSpoilTheAnswer() {
    // -25 не мешает: путь 20 -> 2 -> 10 -> -25 -> 3 = 10, но лучший — 42.
    // Значение подтверждено перебором всех путей (brute force).
    assertEquals(42, solution.maxPathSum(
        tree(10, 2, 10, 20, 1, null, -25, null, null, null, null, 3, 4)));
  }

  @Test
  void testTwoNodesPositive() {
    assertEquals(3, solution.maxPathSum(tree(1, 2)));
  }

  @Test
  void testZeroNode() {
    assertEquals(0, solution.maxPathSum(tree(0)));
  }

  @Test
  void testBranchingBeatsSingleSide() {
    // Наверх уходит 5 + 4 = 9, но глобальный ответ — 5 + 4 + 3 = 12.
    assertEquals(12, solution.maxPathSum(tree(5, 4, 3)));
  }

  @Test
  void testNoStateLeakBetweenCalls() {
    assertEquals(6, solution.maxPathSum(tree(1, 2, 3)));
    assertEquals(-3, solution.maxPathSum(tree(-3)));
    assertEquals(2, solution.maxPathSum(tree(2, -1)));
    assertEquals(-1, solution.maxPathSum(tree(-2, -1, -3)));
    assertEquals(6, solution.maxPathSum(tree(1, 2, 3)));
  }

  /**
   * Собирает дерево из массива в формате LeetCode: по уровням, {@code null} — отсутствие узла.
   */
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
