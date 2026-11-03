package by.semenyukna.lps;

public class Solution {
    public String longestPalindrome(String s) {

        var n = s.length();
        boolean dp[][] = new boolean[n][n];
        for(var i = 0; i<n; i++){
            dp[i][i]=true;
        }
        var res = s.substring(0,1);
        for(var length=2;length<=n;length++){
            for(var l=0;l<n-length+1;l++){
                var r = l+length-1;
                if(s.charAt(l)==s.charAt(r)){
                    if (length ==2){
                        dp[l][r] = true;
                    }
                    else{
                        if(dp[l+1][r-1]==false){
                            dp[l][r] =false;
                            continue;
                        }
                    }
                    res = l+length > res.length() ? s.substring(l,l+length) : res;
                    dp[l][r] = true;
                }
            }
        }

        return res;

    }
}
