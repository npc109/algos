package by.semenyukna.up;

public class Solution {

    private int dp(int m, int n, int[][] c) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (m == 0 || n == 0) {
            return 1;
        } else {

            if (c[m][n] != 0) {
                return c[m][n];
            } else {
                var cur = dp(m - 1, n, c) + dp(m, n - 1, c);
                c[m][n] = cur;
                return cur;
            }
        }
    }

    public int uniquePaths(int m, int n) {
        int[][] c = new int[m + 1][n + 1];
        return dp(m - 1, n - 1, c);
    }

}
