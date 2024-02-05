import java.util.*;

class Solution {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    static class Position {
        int x, y, step;
        Position(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    
    public int solution(int[][] maps) {
        int maxX = maps.length;
        int maxY = maps[0].length;
        
        Position target = new Position(maxX - 1, maxY - 1, 0);
        boolean[][] visited = new boolean[maxX][maxY];
        Queue<Position> queue = new LinkedList<>();
        // init
        queue.offer(new Position(0, 0, 1));
        visited[0][0] = true;
        while(!queue.isEmpty()) {
            Position current = queue.poll();
            
            // 타겟 좌표면 해당 좌표까지의 step 반환
            if (current.x == target.x && current.y == target.y) {
                return current.step;
            }
            
            // 다음 좌표로 이동
            for(int d = 0 ; d < 4; d++) {
                int nX = current.x + dx[d];
                int nY = current.y + dy[d];

                // 유효한 좌표인지 확인
                if (nX < 0 || nX >= maxX || nY < 0 || nY >= maxY 
                   || visited[nX][nY] || maps[nX][nY] == 0) {
                    continue;
                }

                // 유효한 좌표라면 방문 체크
                visited[nX][nY] = true;
                queue.offer(new Position(nX, nY, current.step + 1));    
            }
        }
        return -1;
    }
}
