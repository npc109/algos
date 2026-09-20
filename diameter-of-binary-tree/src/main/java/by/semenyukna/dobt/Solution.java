package by.semenyukna.dobt;

public class Solution {

  int g = 0;

  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }
    var l = dfs(node.left);
    var r = dfs(node.right);
    g = Math.max(g, l + r);
    return 1 + Math.max(l, r);
  }

  public int diameterOfBinaryTree(TreeNode root) {
    g = 0;
    dfs(root);
    return g;
  }
}
