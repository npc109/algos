package by.semenyukna.pess;

public class Solution {
    public boolean canPartition(int[] nums) {
        var s =0;
        for(var n:nums){
            s = s+n;
        }
        if(s%2!=0){
            return false;
        }
        s= s/2;
        boolean[] dp = new boolean[s+1];
        dp[0] = true;
        for(var n:nums){
            for(var i = dp.length-1; i > n-1;i--){
                dp[i] = dp[i] || dp[i-n];
            }

        }
        return dp[s];
    }
}
