package by.semenyukna.sals;

public class Solution {
    public int splitArray(int[] nums, int k) {
        int l=0;
        int r =0;
        for(var n:nums){
            l=Math.max(l,n);
            r=r+n;
        }
        while(l<r){
            var mid = (l+r)/2;
            var cur = mid;
            var c=0;
            for(var n:nums){
                cur = cur -n;
                if(cur<0){
                    c++;
                    cur =mid-n;
                }
            }
            c++;
            if(c>k){
                l=mid+1;
            }
            else{
                r=mid;

            }
        }
        return r;
    }
}
