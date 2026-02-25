package by.semenyukna.pp;


import java.util.HashSet;

class Solution {
  public int[] prevPermOpt1(int[] arr) {
    var pivot = -1;
    var n = arr.length;
    for (var i = n - 2; i >= 0; i--) {
      if (arr[i] > arr[i + 1]) {
        pivot = i;
        break;
      }
    }
    if (pivot == -1) {
      return arr;
    }

    var max = 0;
    for (var i = pivot + 1; i <= arr.length - 1; i++) {
      if (arr[i] < arr[pivot]) {
        if (max == 0) {
          max = i;
        } else if (arr[i] > arr[max]) {
          max = i;
        }

      }
    }
    var tmp = arr[pivot];
    arr[pivot] = arr[max];
    arr[max] = tmp;
    HashSet<Integer> set = new HashSet<>();
    var x = Math.max(1,2);
    return arr;
  }
}