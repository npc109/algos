package by.semenyukna.keb;

public class Solution {

    private Long check(int[] piles, int k){
        var r = 0L;
        for(var n:piles){
           r = r+(n + k - 1) / k;
        }

        return r;
    }


    public int minEatingSpeed(int[] piles, int h) {

        var l = piles.length;
        var left = 1;
        var right = 0;
        for(var n: piles){
            right = Math.max(n,right);
        }

        if(l==h){
            return right;
        }
        while(left<=right){
            var mid = (right+left)/2;
            var c = check(piles, mid);
            if(c<=h){
               right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return right+1;
    }
}
