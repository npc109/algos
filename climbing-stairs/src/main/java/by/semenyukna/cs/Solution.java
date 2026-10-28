package by.semenyukna.cs;

public class Solution {
    public int climbStairs(int n) {
         var cur = 1;
         var prev = 0;
         for(var i=0; i<n; i++){
            var tmp = cur;
            cur = tmp + prev;
            prev = tmp;
         }

         return cur;
    }
}
