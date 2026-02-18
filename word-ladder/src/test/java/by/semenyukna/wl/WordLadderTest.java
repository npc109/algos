package by.semenyukna.wl;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordLadderTest {

  @Test
  void solveCheck0() {
    var result = WordLadder.ladderLength("hit","cog", List.of("hot","dot","dog","lot","log","cog"));
    var expected = 5;
    assertEquals(expected, result);
  }

  @Test
  void solveCheck1() {
    var result = WordLadder.ladderLength("hit","cog", List.of("hot","dot","dog","lot","log"));
    var expected = 0;
    assertEquals(expected, result);
  }

}