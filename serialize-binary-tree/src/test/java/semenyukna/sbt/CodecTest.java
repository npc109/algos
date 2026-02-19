package semenyukna.sbt;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodecTest {

  @Test
  public void testSer() {
    var res = "[1,2,3,null,null,4,5]";
    var root = new TreeNode(1, new TreeNode(2, null, null),
        new TreeNode(3, new TreeNode(4, null, null), new TreeNode(5, null, null)));
    Codec deser = new Codec();
    assertEquals(res, deser.serialize(root));
  }

  @Test
  public void testDeser() {
    var res = "[1,2,3,null,null,4,5]";
    var root = new TreeNode(1, new TreeNode(2, null, null),
        new TreeNode(3, new TreeNode(4, null, null), new TreeNode(5, null, null)));
    Codec deser = new Codec();
    assertEquals(root, deser.deserialize(res));
  }


  @Test
  public void testCodec() {
    var root = new TreeNode(1, new TreeNode(2, null, null),
        new TreeNode(3, new TreeNode(4, null, null), new TreeNode(5, null, null)));
    Codec ser = new Codec();
    Codec deser = new Codec();
    var ans = ser.serialize(root);
    assertEquals(root, deser.deserialize(ser.serialize(root)));
  }

}