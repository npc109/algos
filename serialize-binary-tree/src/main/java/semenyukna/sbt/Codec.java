package semenyukna.sbt;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec {

  public String serialize(TreeNode root) {
    if (root == null) return "[]";
    var q = new LinkedList<List<TreeNode>>();
    q.add(List.of(root));
    var res = "[";
    while (!q.isEmpty()) {
      var cur = q.poll();
      var curStr = "";
      var allNull = true;
      var CurNodes = new LinkedList<TreeNode>();
      for (TreeNode node : cur) {
        if (node != null) {
          allNull = false;
          CurNodes.add(node.left);
          CurNodes.add(node.right);
        }
        curStr = curStr + (node == null ? "null" : node.val) + ',';
      }
      if (!allNull) {
        res = res + curStr;
      }
      if (!CurNodes.isEmpty()) {
        q.add(CurNodes);
      }
    }
    if (res.length() > 1) {
      res = res.substring(0, res.length() - 1);
    }
    return res + "]";
  }

  public TreeNode deserialize(String data) {
    if (data.equals("[]") || data.equals("[null]")) return null;
    String[] tokens = data.substring(1, data.length() - 1).split(",");
    Queue<TreeNode> queue = new LinkedList<>();
    var root = new TreeNode(Integer.parseInt(tokens[0]), null, null);
    queue.add(root);
    int i = 1;
    while (!queue.isEmpty() && i < tokens.length) {
      var cur = queue.poll();
      if (!tokens[i].equals("null")) {
        cur.left = new TreeNode(Integer.parseInt(tokens[i]), null, null);
        queue.add(cur.left);
      }
      i++;
      if (i >= tokens.length) break;
      if (!tokens[i].equals("null")) {
        cur.right = new TreeNode(Integer.parseInt(tokens[i]), null, null);
        queue.add(cur.right);
      }
      i++;
    }
    return root;
  }
}
