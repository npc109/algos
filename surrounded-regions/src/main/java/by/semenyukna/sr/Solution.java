package by.semenyukna.sr;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

  private void push(int x, int y, boolean[][] state, char[][] board, Queue<int[]> q) {
    if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || state[x][y]
        || board[x][y] != 'O') {
      return;
    }
    state[x][y] = true;
    q.add(new int[]{x, y});
  }

  public void solve(char[][] board) {
    boolean[][] state = new boolean[board.length][board[0].length];
    Queue<int[]> q = new LinkedList<>();
    for (var i = 0; i < board.length; i++) {
      for (var j = 0; j < board[i].length; j++) {
        if (i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) {
          if (board[i][j] == 'O') {
            q.add(new int[]{i, j});
          }
        }
      }
    }
    while (!q.isEmpty()) {
      var cur = q.poll();
      state[cur[0]][cur[1]] = true;
      push(cur[0] + 1, cur[1], state, board, q);
      push(cur[0] - 1, cur[1], state, board, q);
      push(cur[0], cur[1] + 1, state, board, q);
      push(cur[0], cur[1] - 1, state, board, q);
    }
    for (var i = 0; i < board.length; i++) {
      for (var j = 0; j < board[i].length; j++) {
        if (!state[i][j]) {
          board[i][j] = 'X';
        }

      }
    }
  }
}
