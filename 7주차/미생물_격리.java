import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 88,876kb / 417ms

public class Solution {
    static class Microbe {
        int direction, count;
        int mergeCount; // merge 용 count

        public Microbe(int count, int direction) {
            this.mergeCount = this.count = count;
            this.direction = direction;
        }

        Microbe merge(Microbe other) {
            if (other == null) {
                // 빈 칸으로 merge하면 그대로 유지
                return this;
            }

            // 미생물 개수가 더 많은 쪽의 direction을 사용한다
            if (this.mergeCount < other.mergeCount) {
                this.mergeCount = other.mergeCount;
                this.direction = other.direction;
            }

            // 미생물 개수 합치기
            this.count = this.count + other.count;
            return this;
        }

        // 모든 이동 완료 시 mergeCount를 count로 초기화
        void resetMergeCount() {
            mergeCount = count;
        }

        void edge() {
            // 미생물 반으로 줄이기
            mergeCount = count /= 2;

            // 방향 반대로 조정
            if (direction % 2 == 0) {
                direction += 1;
            } else {
                direction -= 1;
            }
        }
    }

    static int N, M, K;

    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 한 변의 셀의 개수
            M = Integer.parseInt(st.nextToken()); // 격리 시간
            K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수

            Microbe[][] map = new Microbe[N][N];
            for (int i = 0; i < K; i++) {
                // 미생물 입력
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
            }

            sb.append("#").append(tc).append(" ").append(move(map)).append("\n");
        }

        br.close();
        System.out.println(sb);
    }

    static int move(Microbe[][] map) {
        for (int time = 0; time < M; time++) {
            Microbe[][] newMap = new Microbe[N][N];

            // 미생물 이동 시키기
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] == null) {
                        continue;
                    }

                    Microbe current = map[x][y];
                    current.resetMergeCount();

                    int nX = x + dx[current.direction];
                    int nY = y + dy[current.direction];

                    // edge 처리
                    if (nX == 0 || nX == N - 1 || nY == 0 || nY == N - 1) {
                        current.edge();
                    }

                    // 이동 및 merge
                    newMap[nX][nY] = current.merge(newMap[nX][nY]);
                }
            }

            // 이동 완료 후 map 갱신
            map = newMap;
        }

        // 모든 이동 후 개수 세기
        int count = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] != null) {
                    count += map[x][y].count;
                }
            }
        }

        return count;
    }
}
