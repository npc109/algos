package by.semenyukna.btlot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      ArrayList<Integer> curRes = new ArrayList<>();
      var curLength = q.size();
      for (var i = 0; i < curLength; i++) {
        var cur = q.poll();
        curRes.add(cur.val);
        if (cur.left != null) q.add(cur.left);
        if (cur.right != null) q.add(cur.right);
      }
      res.add(curRes);
    }

    return res;

  }
}
