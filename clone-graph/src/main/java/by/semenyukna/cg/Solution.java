package by.semenyukna.cg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {

  private HashMap<Integer, Node> visited = new HashMap<>();

  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }
    Node head = new Node(node.val);
    visited.put(head.val, head);
    List<Node> nodes = new ArrayList<>();
    head.neighbors = nodes;
    for (Node n : node.neighbors) {
      if (!visited.containsKey(n.val)) {
        nodes.add(cloneGraph(n));
      } else {
        nodes.add(visited.get(n.val));
      }
    }
    return head;
  }
}