package by.semenyukna.hrii;

public class Solution {
    public int rob(int[] nums) {

        var n=nums.length;
        if(n==1){
            return nums[0];
        }

        var c1 = 0;
        var p1 = 0;
        var c2=0;
        var p2=0;
        for(var i=0;i<n-1;i++){
            var tmp = c1;
            c1 = Math.max(p1+nums[i], c1);
            p1=tmp;
        }
        for(var i=1;i<n;i++){
            var tmp = c2;
            c2 = Math.max(p2+nums[i], c2);
            p2=tmp;
        }
        return Math.max(c1,c2);

    }
}
