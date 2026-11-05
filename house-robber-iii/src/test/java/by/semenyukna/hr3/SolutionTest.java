package by.semenyukna.hr3;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

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

  // Эталон: перебор всех подмножеств узлов с проверкой, что нет пары
  // «родитель — ребёнок». Годится только для деревьев до ~20 узлов.
  private int bruteForce(TreeNode root) {
    List<TreeNode> nodes = new ArrayList<>();
    List<int[]> edges = new ArrayList<>();
    collect(root, nodes, edges);
    int n = nodes.size();
    int best = 0;
    for (int mask = 0; mask < (1 << n); mask++) {
      boolean ok = true;
      for (int[] e : edges) {
        if ((mask & (1 << e[0])) != 0 && (mask & (1 << e[1])) != 0) {
          ok = false;
          break;
        }
      }
      if (ok) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
          if ((mask & (1 << i)) != 0) {
            sum += nodes.get(i).val;
          }
        }
        best = Math.max(best, sum);
      }
    }
    return best;
  }

  private void collect(TreeNode node, List<TreeNode> nodes, List<int[]> edges) {
    if (node == null) {
      return;
    }
    int idx = nodes.size();
    nodes.add(node);
    if (node.left != null) {
      edges.add(new int[]{idx, nodes.size()});
      collect(node.left, nodes, edges);
    }
    if (node.right != null) {
      edges.add(new int[]{idx, nodes.size()});
      collect(node.right, nodes, edges);
    }
  }

  private void check(TreeNode root) {
    assertEquals(bruteForce(root), new Solution().rob(root));
  }

  @Test
  void example1() {
    // 3 -> левая 2, правая 3; 2 -> правый ребёнок 3; 3 -> правый ребёнок 1
    TreeNode root = tree(3, 2, 3, null, 3, null, 1);
    assertEquals(7, new Solution().rob(root));
  }

  @Test
  void example2() {
    TreeNode root = tree(3, 4, 5, 1, 3, null, 1);
    assertEquals(9, new Solution().rob(root));
  }

  @Test
  void singleNode() {
    assertEquals(5, new Solution().rob(tree(5)));
    assertEquals(0, new Solution().rob(tree(0)));
  }

  @Test
  void twoLevels() {
    // Корень и children: 3 + 2 + 3 = 8 против 2 + 3 = 5... проверим эталоном
    check(tree(1, 2, 3));
  }

  @Test
  void rootLosesToChildren() {
    check(tree(1, 5, 5));
    check(tree(2, 10, 10));
  }

  @Test
  void leftChain() {
    check(tree(1, 2, null, 3, null, 4, null, 5));
  }

  @Test
  void rightChain() {
    check(tree(1, null, 2, null, 3, null, 4, null, 5));
  }

  @Test
  void allEqual() {
    check(tree(100, 50, 50, 25, 25, 25, 25));
  }

  @Test
  void levelSumWouldFail() {
    // Алгоритм «по уровням с формулой House Robber» даёт 3 + 4 = 7,
    // но 3 (корень) и 1, 3 (уровень 2) — не родитель и ребёнок: 3 + 1 + 3 = 7,
    // а лучше взять 4 + 5 = 9.
    TreeNode root = tree(3, 4, 5, 1, 3, null, 1);
    assertEquals(9, new Solution().rob(root));
  }

  @Test
  void grandchildSurpassesChild() {
    check(tree(4, 1, null, 2, null, 3));
  }

  @Test
  void allZeros() {
    check(tree(0, 0, 0, 0, 0, 0, 0));
  }

  @Test
  void deepTree() {
    // 2000 узлов влево — проверка, что рекурсия не падает по стеку и не теряет значения.
    int n = 2000;
    TreeNode root = new TreeNode(1);
    TreeNode cur = root;
    for (int v = 2; v <= n; v++) {
      cur.left = new TreeNode(v);
      cur = cur.left;
    }
    // Цепочка 1 - 2 - 3 - ... - 2000: соседние запрещены, это задача House Robber
    // на массиве. Выгоднее взять значения 2, 4, ..., 2000 (1000 * 1001 = 1001000),
    // чем 1, 3, ..., 1999 (1000^2 = 1000000).
    int[] dp = new int[n + 3];
    for (int v = n; v >= 1; v--) {
      dp[v] = Math.max(dp[v + 1], v + dp[v + 2]);
    }
    assertEquals(dp[1], new Solution().rob(root));
  }

  @Test
  void stressRandomTrees() {
    Random rnd = new Random(2024);
    for (int iter = 0; iter < 3000; iter++) {
      int n = 1 + rnd.nextInt(16);
      // Строим случайное дерево вставками в случайный свободный слот.
      TreeNode root = new TreeNode(rnd.nextInt(101));
      List<TreeNode> nodes = new ArrayList<>();
      nodes.add(root);
      for (int i = 1; i < n; i++) {
        TreeNode parent = nodes.get(rnd.nextInt(nodes.size()));
        TreeNode child = new TreeNode(rnd.nextInt(101));
        if (rnd.nextBoolean() && parent.left == null) {
          parent.left = child;
          nodes.add(child);
        } else if (parent.right == null) {
          parent.right = child;
          nodes.add(child);
        } else if (parent.left == null) {
          parent.left = child;
          nodes.add(child);
        } else {
          nodes.add(child);
          parent.left = child;
        }
      }
      check(root);
    }
  }
}
