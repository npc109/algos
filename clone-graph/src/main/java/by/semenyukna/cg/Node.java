package by.semenyukna.cg;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
  public int val;
  public List<Node> neighbors;
  public Node() {
    val = 0;
    neighbors = new ArrayList<Node>();
  }
  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<Node>();
  }
  public Node(int _val, ArrayList<Node> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Node node = (Node) o;
    return val == node.val;
  }

  @Override
  public int hashCode() {
    return Objects.hash(val);
  }
}
