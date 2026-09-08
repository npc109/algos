package by.semenyukna.ts2;


import java.util.Hashtable;

public class Solution {

  public int getLongest(int[] a) {
    int cur = 0;
    int res = 0;
    int r = 0;
    int l = 0;
    var pool = new Hashtable<Integer, Integer>();
    while (r < a.length) {
      if (pool.containsKey(a[r])) {
        var newL = pool.get(a[r]);
        if (newL > l) {
          l = newL;
        }
        pool.replace(a[r], r);
        cur = r - l;
      } else {
        pool.put(a[r], r);
        cur++;
      }
      r++;
      res = Math.max(res, cur);
    }

    return res;
  }
}