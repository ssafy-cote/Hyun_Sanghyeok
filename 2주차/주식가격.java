import java.util.*;

class Solution {
    static class Node {
        int index, value;
        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    
    public int[] solution(int[] prices) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.value).reversed());
        
        int[] answer = new int[prices.length];
        for(int i = 0 ; i < prices.length ;i++) {
            queue.offer(new Node(i, prices[i]));
            while(!queue.isEmpty() && queue.peek().value > prices[i]) {
                Node current = queue.poll();
                answer[current.index] = i - current.index;
            }
        }
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            answer[current.index] = prices.length - current.index - 1;
        }
        return answer;
    }
}
