package by.semenyukna.vbst;

class Solution {

  private boolean dfs(TreeNode node, Integer min, Integer max) {
    if (node == null) {
      return true;
    }
    if ((min == null || min < node.val) && (max == null || node.val < max)) {
      return dfs(node.left, min, node.val) && dfs(node.right, node.val, max);

    }
    return false;
  }

  private boolean dfs(TreeNode root) {
    return dfs(root.left, null, root.val) && dfs(root.right, root.val, null);
  }

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    return dfs(root);
  }
}
