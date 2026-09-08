package by.semenyukna.fmrsa;


public class Solution {

  private int find(int[] a, int start, int stop) {
    if (a[start] <= a[stop]) {
      return a[start];
    }
    var mid = (int) Math.round((stop + start) / 2.0);
    if (a[mid] >= a[stop]) {
      return find(a, mid, stop);
    }
    if (a[mid] < a[start]) {
      return find(a, start, mid);
    }
    throw new RuntimeException();
  }

  public int find(int[] a) {

    return find(a, 0, a.length - 1);
  }

}