import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> counts = new HashMap<>();
        for(int t : tangerine) {
            counts.merge(t, 1, Integer::sum);
        }
        
        List<Integer> values = counts.values().stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        
        int answer = 0;
        for(int v : values) {
            answer++;
            k -= v;
            if (k <= 0) {
                break;
            }
        }
        
        return answer;
    }
}
