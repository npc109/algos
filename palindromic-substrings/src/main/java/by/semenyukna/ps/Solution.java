package by.semenyukna.ps;

public class Solution {

  public int countSubstrings(String s) {
    var n = s.length();
    boolean[][] dp = new boolean[n][n];
    for(var i=0;i<n;i++){
      dp[i][i]=true;
    }
    var res = n;
    for(var length=2; length<=n; length++) {
      for (var l = 0; l <= n - length; l ++) {
        var r = l+length-1;
        if(s.charAt(l)==s.charAt(r)){
          if(length!=2){
            if(!dp[l + 1][r - 1]){
              dp[l][r]=false;
              continue;
            }
          }
          dp[l][r] =true;
          res++;
        }
      }
    }
    return res;
  }
}
