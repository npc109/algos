package by.semenyukna.cg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;


public class SolutionTest {

    @Test
    public void testEmptyGraph() {
        var solution = new Solution();
        var result = solution.cloneGraph(null);
        assertEquals(null, result);
    }

    @Test
    public void testSingleNode() {
        var solution = new Solution();
        var node = new Node(1);
        
        var cloned = solution.cloneGraph(node);
        
        assertEquals(1, cloned.val);
        assertEquals(0, cloned.neighbors.size());
        assertNotSame(node, cloned);
    }

    @Test
    public void testTwoNodes() {
        var solution = new Solution();
        var node1 = new Node(1);
        var node2 = new Node(2);
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        
        var cloned = solution.cloneGraph(node1);
        
        assertEquals(1, cloned.val);
        assertEquals(1, cloned.neighbors.size());
        assertEquals(2, cloned.neighbors.get(0).val);
        assertNotSame(node1, cloned);
        assertNotSame(node2, cloned.neighbors.get(0));
    }

    @Test
    public void testFourNodes() {
        var solution = new Solution();
        // Создаём граф: 1-2-3-4 (линейный)
        var node1 = new Node(1);
        var node2 = new Node(2);
        var node3 = new Node(3);
        var node4 = new Node(4);
        
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node3);
        
        var cloned = solution.cloneGraph(node1);
        
        assertEquals(1, cloned.val);
        assertEquals(1, cloned.neighbors.size());
        assertEquals(2, cloned.neighbors.get(0).val);
        
        var cloned2 = cloned.neighbors.get(0);
        assertEquals(2, cloned2.neighbors.size()); // 1 и 3
        
        var cloned3 = cloned2.neighbors.get(1);
        assertEquals(3, cloned3.val);
        assertEquals(2, cloned3.neighbors.size()); // 2 и 4
    }

    @Test
    public void testFullyConnectedGraph() {
        var solution = new Solution();
        // Создаём граф: все узлы соединены со всеми (1-2, 1-3, 1-4, 2-3, 2-4, 3-4)
        var node1 = new Node(1);
        var node2 = new Node(2);
        var node3 = new Node(3);
        var node4 = new Node(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node1.neighbors.add(node4);
        
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node2.neighbors.add(node4);
        
        node3.neighbors.add(node1);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        
        node4.neighbors.add(node1);
        node4.neighbors.add(node2);
        node4.neighbors.add(node3);
        
        var cloned = solution.cloneGraph(node1);
        
        assertEquals(1, cloned.val);
        assertEquals(3, cloned.neighbors.size());
        
        // Проверяем, что все соседи клонированы
        for (var neighbor : cloned.neighbors) {
            assertNotSame(node1, neighbor);
            assertNotSame(node2, neighbor);
            assertNotSame(node3, neighbor);
            assertNotSame(node4, neighbor);
        }
    }

    @Test
    public void testCycleGraph() {
        var solution = new Solution();
        // Создаём граф с циклом: 1-2-3-1
        var node1 = new Node(1);
        var node2 = new Node(2);
        var node3 = new Node(3);
        
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node1);
        
        var cloned = solution.cloneGraph(node1);
        
        assertEquals(1, cloned.val);
        assertEquals(1, cloned.neighbors.size());
        
        var cloned2 = cloned.neighbors.get(0);
        assertEquals(2, cloned2.val);
        assertEquals(1, cloned2.neighbors.size());
        
        var cloned3 = cloned2.neighbors.get(0);
        assertEquals(3, cloned3.val);
        assertEquals(1, cloned3.neighbors.size());
        
        // Проверяем, что цикл замкнулся на клон первого узла
        assertEquals(cloned, cloned3.neighbors.get(0));
    }

    @Test
    public void testStarGraph() {
        var solution = new Solution();
        // Создаём граф-звезду: центр 1, лучи 2, 3, 4, 5
        var center = new Node(1);
        var node2 = new Node(2);
        var node3 = new Node(3);
        var node4 = new Node(4);
        var node5 = new Node(5);
        
        center.neighbors.add(node2);
        center.neighbors.add(node3);
        center.neighbors.add(node4);
        center.neighbors.add(node5);
        
        node2.neighbors.add(center);
        node3.neighbors.add(center);
        node4.neighbors.add(center);
        node5.neighbors.add(center);
        
        var cloned = solution.cloneGraph(center);
        
        assertEquals(1, cloned.val);
        assertEquals(4, cloned.neighbors.size());
        
        // Проверяем, что все соседи соединены с центром
        for (var neighbor : cloned.neighbors) {
            assertEquals(1, neighbor.neighbors.size());
            assertEquals(cloned, neighbor.neighbors.get(0));
        }
    }
}
