package by.semenyukna.lca;

class Solution {

  private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null) {
      return null;
    }
    if ((node.val == p.val) || (node.val == q.val)) {
      return node;
    }
    var l = dfs(node.left, q, p);
    var r = dfs(node.right, q, p);
    if (l != null && r != null) {
      return node;
    }
    if (l != null) {
      return l;
    }
    if (r != null) {
      return r;
    }
    return null;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    var res = dfs(root, p, q);
    if (res == null) {
      return root;
    }
    return res;
  }
}
