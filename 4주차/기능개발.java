// 7Xmb / 2Xms

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0 ; i < progresses.length ; i++) {
            queue.offer((int) Math.ceil((double) (100 - progresses[i]) / speeds[i]));
        }
        
        int[] answers = new int[progresses.length];
        int answerIndex = 0;
        int last = 0;
        while(!queue.isEmpty()) {
            last = queue.peek();
            
            // 출시할 수 있는 기능들 한번에 출시
            int count = 0;
            while(!queue.isEmpty() && queue.peek() <= last) {
                queue.poll();
                count++;
            }
            answers[answerIndex++] = count;
        }
        
        return Arrays.copyOf(answers, answerIndex);
    }
}
