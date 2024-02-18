import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 97,640kb / 776ms

public class Main {

    static int N;
    static int M;
    static int[][] ocean;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ocean = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ocean[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int years = 0;
        int 빙산개수 = 1;
        while (빙산개수 == 1) {
            빙산을녹여라();
            years++;

            빙산개수 = 빙산이몇개고빙산말이다();
        }

        System.out.println(빙산개수 == 0 ? 0 : years);
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static void 빙산을녹여라() {
        boolean[][] processed = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ocean[i][j] != 0 || processed[i][j]) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nX = i + dx[d];
                    int nY = j + dy[d];

                    if (isRangeInvalid(nX, nY) || ocean[nX][nY] == 0) {
                        continue;
                    }
                    processed[nX][nY] = true;
                    ocean[nX][nY]--;
                }
            }
        }
    }

    static int 빙산이몇개고빙산말이다() {
        int count = 0;
        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && ocean[i][j] != 0) {
                    bfs(i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    static void bfs(int x, int y, boolean[][] visited) {
        Deque<int[]> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nX = current[0] + dx[d];
                int nY = current[1] + dy[d];

                if (isRangeInvalid(nX, nY) || visited[nX][nY] || ocean[nX][nY] == 0) {
                    continue;
                }

                visited[nX][nY] = true;
                queue.offer(new int[]{nX, nY});
            }
        }
    }

    static boolean isRangeInvalid(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

}
