package by.semenyukna.lawrn;


import java.util.HashSet;

public class Solution {

  public int getLongest(int[] a) {
    int res = a.length > 0 ? 1 : 0;
    int cur = 1;
    int l = 0;
    int r = 1;
    var pool = new HashSet<Integer>();

    while (r < a.length) {
      pool.add(a[l]); // вот это конечно печально но на асимптотику не влияет
      if (!pool.contains(a[r])) {
        pool.add(a[r]);
        cur++;
        r++;
      } else {
        pool.clear();
        l++;
        r = l + 1;
        cur = 1;
      }
      res = Math.max(cur, res);
    }

    return res;
  }
}