import java.util.*;

class Solution {
    static int[] dx = {1, 0, -1};
    static int[] dy = {0, 1, -1};
    
    public int[] solution(int n) {
        int[][] arr = new int[n][];
        for(int i = 0 ; i < n ; i++) {
            arr[i] = new int[i + 1];
        }
        
        int value = 1;
        int nX = -1;
        int nY = 0;
        int dir = 0;
        for(int i = n ; i > 0 ; i--) {
            for(int j = 0 ; j < i ; j++) {
                nX += dx[dir];
                nY += dy[dir];
                
                arr[nX][nY] = value++;
            }
            dir = (dir + 1) % dx.length;
        }
        
        return Arrays.stream(arr)
            .flatMapToInt(Arrays::stream)
            .toArray();
    }
}
