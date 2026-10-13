package by.semenyukna.ksbst;

class Solution {

  private int dfs(TreeNode node, int k, int cur) {
    if (node == null) {
      return cur == 0 ? -1 : cur;
    }
    cur = dfs(node.left, k, cur);
    if (cur >= 0) {
      return cur;
    }
    if (k + cur == 0) {
      return node.val;
    } else {
      cur = dfs(node.right, k, cur - 1);
      return cur;
    }
  }

  public int kthSmallest(TreeNode root, int k) {
    return dfs(root, k, 0);
  }
}
