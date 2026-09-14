package by.semenyukna.btmps;


public class Solution {

  private int state = Integer.MIN_VALUE;

  public int dfs(TreeNode node){
    if(node == null){
      return 0;
    }


    var sl = dfs(node.left);
    var sr = dfs(node.right);

    state = Math.max(state, node.val + Math.max(0,sl) + Math.max(0,sr));
    return Math.max(node.val,Math.max(node.val + sl, node.val + sr));

  }

  public int maxPathSum(TreeNode root) {
    state = Integer.MIN_VALUE;
    dfs(root);
    return state;
  }
}
