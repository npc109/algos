package by.semenyukna.lca;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SolutionTest {

  private final Solution solution = new Solution();

  //             3
  //           /   \
  //          5     1
  //         / \   / \
  //        6   2 0   8
  //           / \
  //          7   4
  private static TreeNode sample() {
    TreeNode n3 = new TreeNode(3);
    TreeNode n5 = new TreeNode(5);
    TreeNode n1 = new TreeNode(1);
    TreeNode n6 = new TreeNode(6);
    TreeNode n2 = new TreeNode(2);
    TreeNode n0 = new TreeNode(0);
    TreeNode n8 = new TreeNode(8);
    TreeNode n7 = new TreeNode(7);
    TreeNode n4 = new TreeNode(4);

    n3.left = n5;
    n3.right = n1;
    n5.left = n6;
    n5.right = n2;
    n1.left = n0;
    n1.right = n8;
    n2.left = n7;
    n2.right = n4;
    return n3;
  }

  private static TreeNode find(TreeNode root, int val) {
    if (root == null) {
      return null;
    }
    if (root.val == val) {
      return root;
    }
    TreeNode l = find(root.left, val);
    return l != null ? l : find(root.right, val);
  }

  @Test
  void testExample1DifferentSubtrees() {
    TreeNode root = sample();
    assertSame(find(root, 3), solution.lowestCommonAncestor(root, find(root, 5), find(root, 1)));
  }

  @Test
  void testExample2PIsAncestorOfQ() {
    // 5 — предок 4, значит LCA = 5 (узел является предком самого себя).
    TreeNode root = sample();
    assertSame(find(root, 5), solution.lowestCommonAncestor(root, find(root, 5), find(root, 4)));
  }

  @Test
  void testExample3SiblingsDeepDown() {
    TreeNode root = sample();
    assertSame(find(root, 5), solution.lowestCommonAncestor(root, find(root, 6), find(root, 4)));
  }

  @Test
  void testSiblingsImmediate() {
    // 7 и 4 — дети 2, значит LCA = 2.
    TreeNode root = sample();
    assertSame(find(root, 2), solution.lowestCommonAncestor(root, find(root, 7), find(root, 4)));
  }

  @Test
  void testRootIsNotLcaWhenNodesMeetLower() {
    // 0 и 8 — оба дети узла 1, значит LCA = 1, а не корень 3.
    TreeNode root = sample();
    assertSame(find(root, 1), solution.lowestCommonAncestor(root, find(root, 0), find(root, 8)));
  }

  @Test
  void testRootIsLcaWhenNodesInDifferentRootSubtrees() {
    // 6 в левом поддереве корня, 8 в правом → LCA = 3.
    TreeNode root = sample();
    assertSame(find(root, 3), solution.lowestCommonAncestor(root, find(root, 6), find(root, 8)));
  }

  @Test
  void testQIsAncestorOfP() {
    // Обратный порядок аргументов: 2 — предок 7.
    TreeNode root = sample();
    assertSame(find(root, 2), solution.lowestCommonAncestor(root, find(root, 7), find(root, 2)));
  }

  @Test
  void testRootIsP() {
    TreeNode root = sample();
    assertSame(find(root, 3), solution.lowestCommonAncestor(root, find(root, 3), find(root, 4)));
  }

  @Test
  void testRootIsQ() {
    TreeNode root = sample();
    assertSame(find(root, 3), solution.lowestCommonAncestor(root, find(root, 8), find(root, 3)));
  }

  @Test
  void testTwoNodes() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    assertSame(root, solution.lowestCommonAncestor(root, root, root.left));
  }

  @Test
  void testLeftSkewedTree() {
    // 1 -> 2 -> 3 -> 4 влево, LCA(4, 2) = 2.
    TreeNode n4 = new TreeNode(4);
    TreeNode n3 = new TreeNode(3, n4, null);
    TreeNode n2 = new TreeNode(2, n3, null);
    TreeNode n1 = new TreeNode(1, n2, null);
    assertSame(n2, solution.lowestCommonAncestor(n1, n4, n2));
    assertSame(n1, solution.lowestCommonAncestor(n1, n4, n1));
  }

  @Test
  void testRightSkewedTree() {
    // 1 -> 2 -> 3 -> 4 вправо.
    TreeNode n4 = new TreeNode(4);
    TreeNode n3 = new TreeNode(3, null, n4);
    TreeNode n2 = new TreeNode(2, null, n3);
    TreeNode n1 = new TreeNode(1, null, n2);
    // LCA(4, 3) = 3 (3 — родитель 4)
    assertSame(n3, solution.lowestCommonAncestor(n1, n4, n3));
    // LCA(2, 4) = 2 (2 — предок 4)
    assertSame(n2, solution.lowestCommonAncestor(n1, n2, n4));
    // LCA(1, 4) = 1 (корень — предок обоих)
    assertSame(n1, solution.lowestCommonAncestor(n1, n1, n4));
  }

  @Test
  void testDeepestPossiblySiblings() {
    // Дерево-цепочка с двумя листьями на самом дне.
    TreeNode root = new TreeNode(1);
    TreeNode cur = root;
    for (int i = 2; i <= 50; i++) {
      cur.left = new TreeNode(i);
      cur = cur.left;
    }
    cur.left = new TreeNode(100);
    cur.right = new TreeNode(101);
    assertSame(cur, solution.lowestCommonAncestor(root, cur.left, cur.right));
  }

  @Test
  void testNegativeValues() {
    TreeNode root = new TreeNode(0);
    root.left = new TreeNode(-5);
    root.right = new TreeNode(5);
    assertSame(root, solution.lowestCommonAncestor(root, root.left, root.right));
  }

  @Test
  void testDuplicateValuesAreNotConfused() {
    // LeetCode гарантирует уникальные значения, но проверяем устойчивость:
    // узлы ищутся по ссылке, а не по значению.
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(1);
    root.right = new TreeNode(2);
    // p = левый дубликат(1), q = 2 → LCA должен быть root.
    assertSame(root, solution.lowestCommonAncestor(root, root.left, root.right));
  }

  @Test
  void testWideTreeSiblingsAtDifferentDepths() {
    //          10
    //        /    \
    //       5      15
    //      / \    /  \
    //     3   7  12  20
    //    /
    //   1
    TreeNode root = new TreeNode(10,
        new TreeNode(5, new TreeNode(3, new TreeNode(1), null), new TreeNode(7)),
        new TreeNode(15, new TreeNode(12), new TreeNode(20)));

    assertSame(root, solution.lowestCommonAncestor(root,
        find(root, 1), find(root, 20)));
    assertSame(find(root, 5), solution.lowestCommonAncestor(root,
        find(root, 1), find(root, 7)));
    assertSame(find(root, 15), solution.lowestCommonAncestor(root,
        find(root, 12), find(root, 20)));
  }

  @Test
  void testAllPairsAgainstDefinitionReference() {
    // Эталон строится ПО ОПРЕДЕЛЕНИЮ: для каждого узла собираем список предков
    // (включая сам узел), затем ищем самого глубокого общего.
    // Ручные ожидания тут не используются — они ошибались 7 раз за сессию.
    TreeNode root = sample();
    java.util.List<TreeNode> nodes = new java.util.ArrayList<>();
    collect(root, nodes);

    for (TreeNode p : nodes) {
      for (TreeNode q : nodes) {
        if (p == q) {
          continue;
        }
        assertSame(
            referenceLca(root, p, q),
            solution.lowestCommonAncestor(root, p, q),
            "p=" + p.val + " q=" + q.val);
      }
    }
  }

  private static void collect(TreeNode node, java.util.List<TreeNode> out) {
    if (node == null) {
      return;
    }
    out.add(node);
    collect(node.left, out);
    collect(node.right, out);
  }

  /** Эталон: глубочайший общий предок через списки предков. */
  private static TreeNode referenceLca(TreeNode root, TreeNode p, TreeNode q) {
    java.util.List<TreeNode> ap = pathTo(root, p, new java.util.ArrayList<>());
    java.util.List<TreeNode> aq = pathTo(root, q, new java.util.ArrayList<>());
    TreeNode best = null;
    for (TreeNode a : ap) {
      if (aq.contains(a)) {
        best = a;   // идём от корня вниз, последний общий — самый глубокий
      }
    }
    return best;
  }

  private static java.util.List<TreeNode> pathTo(TreeNode node, TreeNode target,
      java.util.List<TreeNode> path) {
    if (node == null) {
      return null;
    }
    path.add(node);
    if (node == target) {
      return path;
    }
    java.util.List<TreeNode> left = pathTo(node.left, target, new java.util.ArrayList<>(path));
    if (left != null) {
      return left;
    }
    return pathTo(node.right, target, new java.util.ArrayList<>(path));
  }
}
