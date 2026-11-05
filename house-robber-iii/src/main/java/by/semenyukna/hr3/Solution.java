package by.semenyukna.hr3;

public class Solution {

    private int[] dfs(TreeNode node){

        if(node==null){
          return new int[2];
        }

        var l = dfs(node.left);
        var r = dfs(node.right);
        return new int[] {
            Math.max(l[0], l[1]) + Math.max(r[0],r[1]),
            node.val+l[0]+r[0]
        };


    }


    public int rob(TreeNode root) {
        var result = dfs(root);
        return Math.max(result[0],result[1]);
    }
}
